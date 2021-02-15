package GUI.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OpeningSceneController {

    public Button startButton;

    @FXML
    public void enableStartButton(Event e){
        startButton.setDisable(false);
        System.out.println("Start button clicked!");
    }

}
