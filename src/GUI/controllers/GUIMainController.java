package GUI.controllers;

import app.Commands;
import app.Game;
import app.XMLController;
import entities.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GUIMainController {

    public Label labelPlayer;
    public String playerInfo;
    public TextField inputBox;
    public TextArea storyBox;
    public Label playerLocation;
    Player player = new Player();
    Game game = new Game(player);


    public void onClickOne(MouseEvent mouseEvent) {
        game.checkAnswer("A", labelPlayer, player, storyBox, playerLocation);
    }

    public void onClickTwo(MouseEvent mouseEvent) {
        game.checkAnswer("B", labelPlayer, player, storyBox, playerLocation);
    }

    public void onClickThree(MouseEvent mouseEvent) {
        game.checkAnswer("C", labelPlayer, player, storyBox, playerLocation);
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
        game.play(100, 120, XMLController.readPathogenXML(), storyBox, inputBox, labelPlayer, playerLocation, player);
    }

    public void submitInputBox(ActionEvent keyEvent) {

        String text = inputBox.getText();
        game.checkAnswer(text, labelPlayer, player, storyBox, playerLocation);
       // game.setUserAnswer(text);
        inputBox.clear();

//            inputBox.clear();

    }


    @FXML
    public ImageView bodyMap = new ImageView();

    @FXML
    private void handleLocation(ActionEvent event) throws FileNotFoundException {
        String organ = ((MenuItem) event.getSource()).getText();
        System.out.println(organ); // helped me to test whether the action event was capturing data

        try {
            InputStream stream = new FileInputStream("src/GUI/resources/" + organ + ".png");
            Image image = new Image(stream);
            //Setting image to the image view
            bodyMap.setImage(image);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
