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
        primaryStage.setTitle("Калькулятор");
        primaryStage.setScene(new Scene(root, 288, 249));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("https://www.komus.ru/medias/" +
                "sys_master/root/h4b/h5c/9125835243550.jpg"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
