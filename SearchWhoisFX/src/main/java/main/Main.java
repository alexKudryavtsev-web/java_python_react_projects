package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import object.Parser;
import object.Reader;

import java.io.IOException;
import java.util.Arrays;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.
                load(getClass().getResource("../gui.fxml"));
        primaryStage.setTitle("Whois Search");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("https://web4africa.com/wp-content/uploads/whois-1.png"));
        primaryStage.setScene(new Scene(root, 562, 320));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}