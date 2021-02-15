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
    GameSceneController gameCtr = new GameSceneController();

    /*
    * //TODO: button border change to color red after being ever selected.
    * */
    @FXML
    public void menuItemSelected(Event e){
       // menuItemButton.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
       // System.out.println(e.getSource()); // return current node info
        Button btn = (Button) e.getSource(); // get an instance of the button clicked on
        //System.out.println(btn.getId());
        btn.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");// set button border color to red.
        gameCtr.setCurrentOrgan(btn.getId());
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
}
