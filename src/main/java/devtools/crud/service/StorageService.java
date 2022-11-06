package devtools.crud.service;

import com.google.common.io.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class StorageService {
    public void save(MultipartFile novell) {
        File tmp = new File("src/main/resources/static");
        InputStream initialStream = null;
        try {
            initialStream = novell.getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);
            File targetFile = new File("src/main/resources/novell.csv");
            Files.write(buffer, targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
