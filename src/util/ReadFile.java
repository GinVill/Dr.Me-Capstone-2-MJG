package util;
/**
 * legacy code for console/terminal IO.
 * .txt reader.
 * */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFile {


    public static void read(String filename){
        try {
            String text = new String(Files.readAllBytes(Paths.get(filename)));
            System.out.printf("%40s", text);

        } catch (IOException e) {
            System.out.println("Sorry, that file does not exist. Try again.");
        }
    }

    public static void displayBodyMap(String chosenOrgan){
        try {
            String bodyMap = Files.readString(Path.of("resources", "bodyMap-" + chosenOrgan + ".txt"));
            System.out.println(bodyMap);

        } catch (IOException e) {
            System.out.println("Sorry, that file does not exist. Try again.");
        }
    }


}
