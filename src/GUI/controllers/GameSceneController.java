package GUI.controllers;

import app.Commands;
import app.Game;
import app.XMLController;
import entities.Player;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import util.MusicPlayer;


public class GameSceneController {


    public Label labelPlayer;
    public String playerInfo;
    public TextField inputBox;
    public TextArea storyBox;
    public TextArea feedbackTextArea;
    public Slider volumeSlider;
    public ImageView bodyMap;
    private final MusicPlayer mpTheme = new MusicPlayer("resources/Away - Patrick Patrikios.wav");

    public static Player player = new Player();

    public static Game game = new Game(player);


    @FXML
    void adjustVolume() {
        // volumeSlider.setValue(mpTheme.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mpTheme.setVolume((float) volumeSlider.getValue());
            }
        });
    }

    @FXML
    void onClickOne(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("A", labelPlayer, player, storyBox, feedbackTextArea);
    }

    @FXML
    void onClickTwo(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("B", labelPlayer, player, storyBox, feedbackTextArea);
    }

    @FXML
    void onClickThree(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("C", labelPlayer, player, storyBox, feedbackTextArea);
    }


    @FXML
    void submitInputBox(ActionEvent keyEvent) {

        String text = inputBox.getText();
        game.checkAnswer(text, labelPlayer, player, storyBox, feedbackTextArea);
        // game.setUserAnswer(text);
        inputBox.clear();

    }
    /*
     * This method takes the location from the menuButton items and sets the filepath to
     * various map images.
     */
    @FXML
    private void handleLocation(ActionEvent event) {
        String organ = ((MenuItem) event.getSource()).getText(); //gets the text from the MenuItem selected on MenuButton
        bodyMap.setImage(new Image(getClass().getResource("/GUI/views/" + organ + ".png").toExternalForm()));
        MenuSceneController.setCurrentOrgan(organ);
    }

    @FXML
    public void initialize() { // initialize scene when windows loaded.
        Commands.loadWordXMLfile();
        // Read and load Cell XML file
        XMLController.readCellXML();
        //Calls play() to load the game screen
        game.play(100, 120, XMLController.readPathogenXML(), MenuSceneController.getCurrentOrgan(), storyBox, inputBox, labelPlayer, player, mpTheme);
    }

    public void updatePlayerStatus(MouseEvent mouseEvent) {
        labelPlayer.setText(player.toString());
    }
}
