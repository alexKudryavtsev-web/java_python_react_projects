package object;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.
                load(getClass().getResource("../gui.fxml"));
        primaryStage.setTitle("Bool Calculator");
        String resource = getClass().getResource("/boolCalcIcon.png").toString();
        primaryStage.getIcons().add(new Image(resource));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 260, 180));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}