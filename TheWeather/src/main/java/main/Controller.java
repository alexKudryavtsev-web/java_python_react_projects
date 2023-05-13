package main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Controller {
    public Pane container;

    @FXML
    public void initialize() throws URISyntaxException, MalformedURLException {
        String URL = getClass().getResource("../weatherImage/rainIcon.jpg").toURI().toURL().toString();

        BackgroundImage myBI = new BackgroundImage(new Image(URL, 624, 329, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        container.setBackground(new Background(myBI));
    }

    @FXML
    public void roll(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.toBack();
    }

    @FXML
    public void close(ActionEvent event) {
        Platform.exit();
    }
}
