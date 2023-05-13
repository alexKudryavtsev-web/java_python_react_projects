package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import object.Annuity;
import object.Differentiated;

import java.util.Calendar;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Credit Calculator");
        primaryStage.setScene(new Scene(root, 295, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);

        Differentiated a = new Differentiated(12, 500000, 4, Calendar.getInstance());
        double res = a.monthlyPayment();
        System.out.println(res);
    }
}