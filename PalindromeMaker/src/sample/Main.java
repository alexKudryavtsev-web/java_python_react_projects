package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("To palindrome");
        primaryStage.getIcons().add(new Image(
                "https://upload.wikimedia.org/wikipedia/" +
                "commons/thumb/1/1f/Palindrom_TENET.svg/" +
                "206px-Palindrom_TENET.svg.png"));
        primaryStage.setScene(new Scene(root, 246, 134));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
