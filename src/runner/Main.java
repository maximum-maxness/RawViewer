package runner;

import controller.CurrentSession;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage firstStage;
    public static CurrentSession cs;

    @Override
    public void start(Stage primaryStage) throws Exception{
        firstStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/res/fxml/sample.fxml"));
        firstStage.setTitle("Hello World");
        firstStage.setScene(new Scene(root, 1280, 720));
        firstStage.show();
        cs = new CurrentSession();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
