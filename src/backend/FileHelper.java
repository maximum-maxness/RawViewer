package backend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper {

    public static List<String> getFilesFromFolder(String pathToFolder){
        List<String> result;
        try (Stream<Path> walk = Files.walk(Paths.get(pathToFolder))) {
            result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".CR2")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public static String getFolderName(String pathToFolder){
        String[] subsections = pathToFolder.split("\\\\");
        return subsections[subsections.length - 1];
    }

    public static void createCacheFolder(String folderName){
        new File(".\\cache\\"+folderName).mkdirs();
    }
}
