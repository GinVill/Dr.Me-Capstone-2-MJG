package util;

import GUI.controllers.GameSceneController;
import entities.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

public class PopupBox {


    public static void makeASelection(String title, String message){
        ButtonType brain = new ButtonType("Brain", ButtonBar.ButtonData.OTHER);
        ButtonType mouth = new ButtonType("Mouth", ButtonBar.ButtonData.OTHER);
        ButtonType heart = new ButtonType("Heart", ButtonBar.ButtonData.OTHER);
        ButtonType throat = new ButtonType("Throat", ButtonBar.ButtonData.OTHER);
        ButtonType lungs = new ButtonType("Lungs", ButtonBar.ButtonData.OTHER);
        ButtonType colon = new ButtonType("Colon", ButtonBar.ButtonData.OTHER);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(brain,mouth,heart,throat,lungs,colon);

        alert.showAndWait().ifPresent(type -> {
            if (type == brain){
                GameSceneController.game.organChange("brain");
            }else if (type == mouth){
                GameSceneController.game.organChange("mouth");
            }else if (type == heart){
                GameSceneController.game.organChange("heart");
            }else if (type == throat){
                GameSceneController.game.organChange("throat");
            }else if (type == lungs){
                GameSceneController.game.organChange("lungs");
            }else if (type == colon){
                GameSceneController.game.organChange("colon");
            }
        });
    }
    public static void popUp(String title, String message, Player p){
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);


        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(type -> {
            if (type == yesButton){
                makeASelection("Welcome Back", "Make a selection of where to start");
                p.setHealth(120);
                p.setPlayerPoints(0);

            }else if (type == noButton){

                System.exit(0);
            }
        });
    }


}
