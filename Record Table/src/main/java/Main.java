import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.
                load(
                getClass().
                getResource("samples/gui.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Record Table");
        primaryStage.getIcons().add(new Image("http://www.poradki.ru/upload/iblock/620/620bae343f7dceb140f9c006306afb70.jpg"));
        primaryStage.setScene(new Scene(root, 749,  408));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}