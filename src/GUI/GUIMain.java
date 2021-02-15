package GUI;

import app.DoctorMeApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
* The names for "stage" and "scene" are inspired by a theater.
* A stage can display multiple scenes, just like in a theater play.
* Similarly, a computer game could have a menu scene, a game scene,
* a game over scene, a high score scene etc.
* */

public class GUIMain extends Application {

    // A JavaFX application needs a primary launch class.
    public static void main(String[] args) throws InterruptedException {
        DoctorMeApp app = new DoctorMeApp();
        launch(args);// this method launches the JavaFX runtime and your JavaFX application.
        // app.playDrMe();
    }

    // All subclasses of the JavaFXApplication class must implement the abstract start() method of the Application class.
    public void start(Stage primaryStage) throws Exception{
        // set the initial scene to opening scene

        //Parent root = FXMLLoader.load(getClass().getResource("../GUI/resources/openingScene.fxml"));

        //TODO changed filepath and now jar opens
        Parent root = FXMLLoader.load(getClass().getResource("resources/openingScene.fxml"));

        primaryStage.setTitle("Doctor Me");
        primaryStage.setScene(new Scene(root));
        primaryStage.show(); // make primaryStage visible.
    }

}
