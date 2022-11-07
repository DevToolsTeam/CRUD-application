package devtools.crud.controller;

import devtools.crud.model.NovellLine;
import devtools.crud.service.NovellLineService;
import devtools.crud.service.StorageService;
import devtools.crud.tools.CSVHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller responsible for loading csv files
 * and returning novel line
 */
@RestController
public class VisualNovellController {
    private StorageService storageService;
    private CSVHandler csvHandler;
    private NovellLineService novellLineService;

    /**
     *
     * @param storageService store csv file with data
     * @param csvHandler
     * @param novellLineService
     */
    @Autowired
    public VisualNovellController(StorageService storageService, CSVHandler csvHandler, NovellLineService novellLineService) {
        this.storageService = storageService;
        this.csvHandler = csvHandler;
        this.novellLineService = novellLineService;
    }

    /**
     *
     * @param novell file with novell
     */
    @PostMapping("/load")
    public void loadNovell(@RequestParam("novell") MultipartFile novell) {
        storageService.save(novell);
        csvHandler.handle();
    }

    /**
     *
     * @param id line id
     * @return line of novell
     */
    @GetMapping("/get/{id}")
    public NovellLine getLine(@PathVariable int id) {
        return novellLineService.findById(Long.valueOf(id));
    }
}