package app;

import entities.Player;
import util.Colors;
import util.Output;

import java.util.Scanner;


public class DoctorMeApp {

    // Game constants per instance
    private final int WINNING_POINTS_REQUIRED = 100;
    private final int HEALTHVALUE = 100;
    private final int DIFFICULTY = 50;

    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        DoctorMeApp app = new DoctorMeApp();
        app.playDrMe();
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