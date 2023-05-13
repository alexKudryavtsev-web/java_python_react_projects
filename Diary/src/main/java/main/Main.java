package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("../fxmls/gui.fxml"));
            primaryStage.setTitle("Person Diary");
            String url = getClass().getResource("/icons/iconDiary.png").toString();
            primaryStage.getIcons().add(new Image(url));
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(root, 632, 537));
            primaryStage.show();
        }catch (IOException exc){
            exc.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
