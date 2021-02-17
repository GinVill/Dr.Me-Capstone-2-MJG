package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuSceneController {
    private static String currentOrgan;
    public Label playerName ;
    public String playerInfo;
    public TextField inputBox;
    public TextArea storyBox;
    Button btn;


    @FXML
    public void menuItemSelected(Event e) throws IOException {
        btn = (Button) e.getSource(); // get an instance of the button clicked on
        btn.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");// set button border color to red.
        setCurrentOrgan(btn.getId()); // set variable currentOrgan to the value of current button ID e.g. "brain".
    }

    @FXML
    public static void setCurrentOrgan(String organ){
        currentOrgan = organ;
    }

    @FXML
    public static String getCurrentOrgan(){
        return currentOrgan;
    }

    /*
     * this method switch from menu scene to the next questioning scene
     * */
    @FXML
    public void changeToGameSceneButtonPushed(ActionEvent e) throws IOException {
        try {
            Parent openingSceneFXML = FXMLLoader.load(getClass().getResource("/GUI/views/gameScene.fxml")); // transition to game scene.
            Scene questioningScene = new Scene(openingSceneFXML);
            // gets the stage information.
            Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
            window.setScene(questioningScene);
            window.show();
        } catch (Exception event) {
            System.out.println(event.getMessage());
        }
    }

    @FXML
    public void initialize() { // initialize scene when windows loaded.
       playerName.setText(OpeningSceneController.playerName);
    }

//    public void setPlayerName(String playerName) {
//        this.playerName.setText(playerName);
//    }

}
