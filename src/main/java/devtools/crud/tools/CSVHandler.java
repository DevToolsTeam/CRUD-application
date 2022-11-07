package devtools.crud.tools;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import devtools.crud.model.NovellLine;
import devtools.crud.repository.NovellLineRepository;
import devtools.crud.service.NovellLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Responsible to parse csv
 */
@Component
public class CSVHandler {
    NovellLineService novellLineService;

    @Autowired
    public CSVHandler(NovellLineService novellLineService) {
        this.novellLineService = novellLineService;
    }

    /**
     * put csv file to database
     */
    public void handle() {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/novell.csv"))) {
            List<String[]> r = reader.readAll();
            Set<String> stringSet = new HashSet<>();
            stringSet.add("speaker");
            stringSet.add("light");
            stringSet.add("emotion");
            stringSet.add("text");
            stringSet.add("background");
            stringSet.add("character1");
            stringSet.add("character2");
            stringSet.add("character3");
            stringSet.add("music");

            Set<String> boolSet = new HashSet<>();
            boolSet.add("autoskip");
            boolSet.add("choice");

            Set<String> intSet = new HashSet<>();
            intSet.add("noskipif");
            intSet.add("nextScene1");
            intSet.add("nextScene2");
            intSet.add("nextScene3");
            for(int i = 1;i < 1250;i++) {
                NovellLine novellLine = new NovellLine();
                try {
                    novellLine.setId((long) Integer.parseInt(r.get(i)[0]));
                } catch (Exception e) {
                    novellLine.setId(0l);
                }
                for(int j = 1;j < r.get(i).length;j++) {
                    if(r.get(0)[j].equals("NO Skip If")) {
                        r.get(0)[j] = ("noskipif");
                    }
                    if(intSet.contains(r.get(0)[j])) {
                        int val = 0;
                        try {
                            val = Integer.parseInt(r.get(i)[j]);
                        } catch (Exception e) {
                            val = 0;
                        }
                        novellLine.getClass().getMethod("set"+
                                        (r.get(0)[j]).substring(0, 1).toUpperCase()
                                        + (r.get(0)[j]).substring(1), int.class)
                                .invoke(novellLine, val);

                    }
                    if(boolSet.contains(r.get(0)[j])) {
                        boolean val = false;
                        try {
                            val = Boolean.parseBoolean(r.get(i)[j]);
                        } catch (Exception e) {
                            val = false;
                        }
                        novellLine.getClass().getMethod("set"+
                                        (r.get(0)[j]).substring(0, 1).toUpperCase()
                                        + (r.get(0)[j]).substring(1), boolean.class)
                                .invoke(novellLine, val);

                    }
                    if(stringSet.contains(r.get(0)[j])) {
                        novellLine.getClass().getMethod("set" +
                                        (r.get(0)[j]).substring(0, 1).toUpperCase()
                                        + (r.get(0)[j]).substring(1), (r.get(i)[j].equals("TRUE")
                                        || r.get(i)[j].equals("FALSE")) ? boolean.class : r.get(i)[j].getClass())
                                .invoke(novellLine,
                                        (r.get(i)[j].equals("TRUE")
                                                || r.get(i)[j].equals("FALSE")) ? ((r.get(i)[j].equals("TRUE") ? true : false)) : r.get(i)[j]);
                    }
                }
                novellLineService.save(novellLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
