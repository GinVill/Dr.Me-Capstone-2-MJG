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
import javafx.stage.Stage;

import java.io.IOException;
/**
 * This class loads the menu scene controller. It is launched by
 * the opening scene controller. This controller launches the
 * GameScene controller on action.
 * It contains:
 * menuItemSelected()
 * setCurrentOrgan()
 * getCurrentOrgan()
 * changeToGameSceneButtonPushed()
 * initialize()
 */

public class MenuSceneController {
    private static String currentOrgan;
    public Label playerName ;
    private Button btn;

    /**
     * This method captures user input and sets current organ on click
     */
    @FXML
    public void menuItemSelected(Event e) throws IOException {
        btn = (Button) e.getSource(); // get an instance of the button clicked on
        btn.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");// set button border color to red.
        setCurrentOrgan(btn.getId()); // set variable currentOrgan to the value of current button ID e.g. "brain".
    }
    /**
     * This method sets the current organ
     * This method is called by menuItemSelected()
     */
    @FXML
    public static void setCurrentOrgan(String organ){
        currentOrgan = organ;
    }
    /**
     * This method gets the current organ
     */
    @FXML
    public static String getCurrentOrgan(){
        return currentOrgan;
    }

    /**
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

    /**
     * This method initializes the opening scene controller with the
     * player's name
     */
    @FXML
    public void initialize() { // initialize scene when windows loaded.
       playerName.setText(OpeningSceneController.playerName);
    }

}
