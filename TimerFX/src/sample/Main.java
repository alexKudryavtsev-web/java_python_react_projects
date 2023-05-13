package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Timer");
        primaryStage.getIcons().add(new Image("http://wintobe.ru/templates/my2019win/imgpromo/timer-na-komputer.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 403, 120));
        primaryStage.show();
    }

    public static void main(String[] args)  {
        launch(args);
    }
}