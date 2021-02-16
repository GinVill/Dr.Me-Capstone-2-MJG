package app;

import entities.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

public class PopupBox {

    public static void popUp(String title, String message, Player player){
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);


        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(type -> {
            if (type == yesButton){
                System.out.println("Yes I want to play");
                player.setHealth(120);
                player.setPlayerPoints(0);
            }else if (type == noButton){
                System.out.println("I dont want to play anymore");
                System.exit(0);
            }
        });
    }
}
