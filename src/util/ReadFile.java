package util;

import java.io.IOException;
import java.nio.file.Files;
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
}
