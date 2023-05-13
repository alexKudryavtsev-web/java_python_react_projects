package controller;

import element.Word;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;
import java.io.IOException;

public class TextTuner {
    public TextField field_text;
    public ColorPicker color;
    public CheckBox isUnderline;
    public CheckBox isStrikethrough;
    public Text text;

    private Word word = Word.WORD;

    public void initialize(){
        color.setValue(Color.BLACK);
        field_text.textProperty().addListener((observable, oldValue, newValue)
                -> {
            text.setText(newValue);
            word.setText(newValue);
        });

        isStrikethrough.setOnAction(event -> {
            boolean sel = isStrikethrough.isSelected();
            text.setStrikethrough(sel);
            word.setStrikethrough(sel);
        });

        isUnderline.setOnAction(event -> {
            boolean sel = isUnderline.isSelected();
            text.setUnderline(sel);
            word.setUnderline(sel);
        });
    }

    @FXML public void setColor(ActionEvent event) {
        Color c = color.getValue();
        text.setFill(c);
        word.setColor(c);
    }

    @FXML public void setFont(ActionEvent event){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    getClass().
                            getResource("../samples/font.fxml"));
            stage.setTitle("Font");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (IOException exc){
            System.out.println(exc);
        }
        text.setFont(word.getFont());
    }

    @FXML public void save(ActionEvent event){
        word.setText(text.getText());
        word.addCanvas();
        exit(event);
    }

    @FXML public void exit(ActionEvent event){
        ((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
    }
}