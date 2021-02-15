package GUI;

import app.DoctorMeApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIMain extends Application {

    public static void main(String[] args) throws InterruptedException {
        DoctorMeApp app = new DoctorMeApp();
        launch(args);
        // app.playDrMe();

    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/resources/GUIMain.fxml"));
        primaryStage.setTitle("Dr. Me");

//        InputStream stream = new FileInputStream("src/GUI/resources/whole.png");
//        Image image = new Image(stream);
//        ImageView bodyMap = new ImageView(image);


        primaryStage.setScene(new Scene(root, 750, 700));
        primaryStage.show();


    }
}
