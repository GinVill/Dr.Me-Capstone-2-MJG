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

public class MenuSceneController {

    private Button menuItemButton = new Button();

    /*
    *
    * */
    @FXML
    public void menuItemSelected(Event e){
        menuItemButton.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
        System.out.println("menu button clicked!");
    }


    /*
     * this method switch menu scene to the next questioning scene
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
}
