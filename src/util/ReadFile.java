package util;

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

//    private void displaySnowmanImage() throws IOException {
//        //It will display the full snowman as soon as showBoard - Game
//        /* so our thought process is:
//        String snowmanImage = Files.readString(Path.of("resources","snowman-0.txt"));
//        should be in showBoard() in Game class
//        */
//        if (incorrectCount == 0) {
//            String snowmanImage = Files.readString(Path.of("resources", "snowman-0.txt"));
//            System.out.println(snowmanImage);
//        } else {
//            //Displaying the snowman Images when the user makes incorrect guesses
//            System.out.println("You made " + incorrectCount + " wrong guesses! You have " + (TOTAL_CHANCES - incorrectCount) + " guesses left!");
//            String snowmanImage = Files.readString(Path.of("resources", "snowman-" + incorrectCount + ".txt"));
//            System.out.println(snowmanImage);
//        }
//    }
}
