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


public class GameSceneController {


    public Label labelPlayer;
    public String playerInfo;
    public TextField inputBox;
    public TextArea storyBox;
<<<<<<< HEAD:src/GUI/controllers/GameSceneController.java
    public Label playerLocation;
    public String currentOrgan;
=======

>>>>>>> 22bf12221bf4494781aac3d03a2307f973991337:src/GUI/controllers/GUIMainController.java
    Player player = new Player();
    Game game = new Game(player);

    @FXML
    void onClickOne(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("A", labelPlayer, player, storyBox);
    }

    @FXML
    void onClickTwo(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("B", labelPlayer, player, storyBox);
    }

    @FXML
    void onClickThree(MouseEvent mouseEvent) {
        //Sends click event to Game logic to check answer
        game.checkAnswer("C", labelPlayer, player, storyBox);
    }

    @FXML
    void startGame(MouseEvent mouseEvent) {
        Commands.loadWordXMLfile();
        // Read and load Cell XML file
        XMLController.readCellXML();
        //Calls play() to load the game screen
        game.play(100, 120, XMLController.readPathogenXML(), storyBox, inputBox, labelPlayer,  player);
    }

    public void setCurrentOrgan(String organ){
        currentOrgan = organ;
    }

    @FXML
    void submitInputBox(ActionEvent keyEvent) {

        String text = inputBox.getText();
        game.checkAnswer(text, labelPlayer, player, storyBox);
        // game.setUserAnswer(text);
        inputBox.clear();

    }


    @FXML
    public ImageView bodyMap = new ImageView();

    @FXML
    private void handleLocation(ActionEvent event) throws FileNotFoundException {
        String organ = ((MenuItem) event.getSource()).getText();
        //System.out.println(organ); // helped me to test whether the action event was capturing data

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
