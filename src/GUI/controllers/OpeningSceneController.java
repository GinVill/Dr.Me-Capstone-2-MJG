package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OpeningSceneController {

    public Button startButton;

    @FXML
    public void enableStartButton(Event e){
        startButton.setDisable(false);
        System.out.println("Start button clicked!");
    }


    /*
    * this method switch opening scene to the next scene
    * */
    @FXML
    public void changeScreenButtonPushed(ActionEvent e) throws IOException {
        try {
            Parent openingSceneFXML = FXMLLoader.load(getClass().getResource("../resources/GUIMain.fxml"));
        Scene questioningScene = new Scene(openingSceneFXML);

        // gets the stage information.
        Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        window.setScene(questioningScene);
        window.show();
        } catch (Exception event) {
            System.out.println(event.getMessage());
        }
    }
}
