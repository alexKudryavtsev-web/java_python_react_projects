package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("../gui.fxml"));
            primaryStage.setTitle("TheWeather");
            primaryStage.setScene(new Scene(root, 624, 334));
            primaryStage.show();
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        Predi
    }

    public static void main(String[] args) {
        launch(args);
    }
}
