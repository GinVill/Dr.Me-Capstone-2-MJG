package GUI.controllers;

import app.Commands;
import app.Game;
import app.XMLController;
import entities.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class GameSceneController {

    public Label labelPlayer;
    public String playerInfo;
    public TextField inputBox;
    public TextArea storyBox;
    public Label playerLocation;
    Player player = new Player();
    Game game = new Game(player);

    @FXML
    void onClickOne(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("A", labelPlayer, player, storyBox, playerLocation);
    }

    @FXML
    void onClickTwo(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("B", labelPlayer, player, storyBox, playerLocation);
    }

    @FXML
    void onClickThree(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("C", labelPlayer, player, storyBox, playerLocation);
    }

    @FXML
    void startGame(MouseEvent mouseEvent) {
        Commands.loadWordXMLfile();
        // Read and load Cell XML file
        XMLController.readCellXML();
        //Calls play() to load the game screen
        game.play(100, 120, XMLController.readPathogenXML(), storyBox, inputBox, labelPlayer, playerLocation, player);
    }

    @FXML
    void submitInputBox(ActionEvent keyEvent) {

        String text = inputBox.getText();
        game.checkAnswer(text, labelPlayer, player, storyBox, playerLocation);
        // game.setUserAnswer(text);
        inputBox.clear();

    }
}
