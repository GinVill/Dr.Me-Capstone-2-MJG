package GUI.controllers;

import app.Commands;
import app.Game;
import app.XMLController;
import entities.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import util.MusicPlayer;

/**
 * This class loads the game scene for play
 * It includes methods:
 * adjustVolume()
 * onClickOne()
 * onClickTwo()
 * onClickThree()
 * initialize()
 * updatePlayerStatus()
 */
public class GameSceneController {

    public Label labelPlayer;
    public TextArea storyBox;
    public TextArea feedbackTextArea;
    public Slider volumeSlider;
    public ImageView bodyMap;

    private final MusicPlayer mpTheme = new MusicPlayer("resources/Away - Patrick Patrikios.wav");
    public static Player player = new Player();
    public static Game game = new Game(player);

    /**
     * Allows player to adjust music volume in game
     */

    @FXML
    void adjustVolume() {
        // volumeSlider.setValue(mpTheme.getVolume() * 100);
        volumeSlider.valueProperty().addListener(observable -> mpTheme.setVolume((float) volumeSlider.getValue()));
    }

    /**
     * Captures player input on click
     */
    @FXML
    void onClickOne(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("A", labelPlayer, player, storyBox, feedbackTextArea);
    }
    /**
     * Captures player input on click
     */
    @FXML
    void onClickTwo(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("B", labelPlayer, player, storyBox, feedbackTextArea);
    }
    /**
     * Captures player input on click
     */
    @FXML
    public void onClickThree(MouseEvent mouseEvent) {
        game.checkAnswer("C", labelPlayer, player, storyBox, feedbackTextArea);
    }

    /**
     * Initializes GameSceneController for game play
     */
    @FXML
    public void initialize() { // initialize scene when windows loaded.
        Commands.loadWordXMLfile();
        // Read and load Cell XML file
        XMLController.readCellXML();
        //Calls play() to load the game screen
        game.play(100, 120, XMLController.readPathogenXML(), MenuSceneController.getCurrentOrgan(), storyBox, labelPlayer, player, mpTheme, bodyMap);
    }

    public void updatePlayerStatus(MouseEvent mouseEvent) {
        labelPlayer.setText(player.toString());
    }
}
