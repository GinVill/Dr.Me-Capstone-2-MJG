package app;

import entities.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Colors;
import util.Output;

import java.util.Scanner;


public class DoctorMeApp extends Application{

    // Game constants per instance
    private final int WINNING_POINTS_REQUIRED = 100;
    private final int HEALTHVALUE = 100;
    private final int DIFFICULTY = 50;

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        DoctorMeApp app = new DoctorMeApp();
        launch(args);
       // app.playDrMe();

    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/resources/GUIMain.fxml"));
        primaryStage.setTitle("Doctor Me");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void playDrMe() throws InterruptedException {
        Output.printColor("Hello! What is your name? \n>> ", Colors.ANSI_YELLOW, false);
        String playerName = sc.nextLine().strip();
        // initialize player
        Player player = new Player(playerName);

        // Create the game object, passing in one player with "normal"
        // difficulty represented as 50
        Game game = new Game(player, DIFFICULTY);

        // game.playIntroduction(playerName);
        // Read and Load Word XML file
        Commands.loadWordXMLfile();

        // Read and load Cell XML file
        XMLController.readCellXML();


        System.out.println();
        game.play(WINNING_POINTS_REQUIRED, HEALTHVALUE, XMLController.readXML());

    }

}