package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import structure.InvertedIndex;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Inverted Index");
        primaryStage.getIcons().add(new Image(
                "https://st.depositphotos.com/1361798/4389/v/950/" +
                "depositphotos_43897491-stock-illustration-magnifying-glass.jpg"));
        primaryStage.setScene(new Scene(root, 760, 495));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}