package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OpeningSceneController {

    public static String playerName;
    public Button startGameButton;
    public TextField nameInput ;
    //public MenuSceneController menuctr;
    private static Stage menuScene;

    @FXML
    public void enableStartGameButton(Event e){
        startGameButton.setDisable(false);
        //TODO: get player name.
    }

    /*
    * this method switch opening scene to the menu scene
    * */
    @FXML
    public void changeToMenuSceneButtonPushed(ActionEvent e) throws IOException {
        playerName = nameInput.getText();
        //System.out.println("player is: " + playerName);
        try {
            Parent openingSceneFXML = FXMLLoader.load(getClass().getResource("/GUI/views/menuScene.fxml")); // transition to menu scene.
            Scene questioningScene = new Scene(openingSceneFXML);

            // gets the stage information.
            Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
            window.setScene(questioningScene);
            window.show();
        } catch (Exception event) {
            System.out.println(event.getMessage());
        }

    }

    public static void changeBackToMenu(String fxml){
        try {
            Parent pane = FXMLLoader.load(OpeningSceneController.class.getResource("/GUI/views/menuScene.fxml"));
            Scene scene = new Scene(pane);
            menuScene.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
