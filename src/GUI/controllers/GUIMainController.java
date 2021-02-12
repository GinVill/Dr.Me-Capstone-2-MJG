package GUI.controllers;

import app.Commands;
import app.Game;
import app.XMLController;
import entities.Player;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class GUIMainController {

    public Label labelPlayer;
    public String playerInfo;
    public TextField inputBox;
    public TextArea storyBox;
    public Label playerLocation;
    Player player = new Player();
    Game game = new Game(player);


    public void onClickOne(MouseEvent mouseEvent) {
        System.out.println("button 1 pressed");
    }

    public void onClickTwo(MouseEvent mouseEvent) {
        labelPlayer.setText("hello");
    }

    public void onClickThree(MouseEvent mouseEvent) {
    }

    public void startGame(MouseEvent mouseEvent) {
        labelPlayer.setText("Game.getPlayer().toString()");
        // Create the game object, passing in one player with "normal"
        // difficulty represented as 50
        // game.playIntroduction(playerName);
        // Read and Load Word XML file
        Commands.loadWordXMLfile();
        // Read and load Cell XML file
        XMLController.readCellXML();
        game.play(100, 120, XMLController.readXML(), storyBox, inputBox, labelPlayer, playerLocation, player);
    }

    public void submitInputBox(ActionEvent keyEvent) {

        String text = inputBox.getText();
        game.checkAnswer(text, labelPlayer, player);
       // game.setUserAnswer(text);
        inputBox.clear();

//            inputBox.clear();

    }
}
