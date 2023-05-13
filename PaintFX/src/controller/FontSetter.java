package controller;

import element.Word;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.*;

import static javafx.collections.FXCollections.*;

public class FontSetter {
    public ChoiceBox<String> style_box;
    public ComboBox<String> family_box;
    public TextField size;
    public Slider size_slider;

    public void initialize(){
        Font oldFont = Word.WORD.getFont();

        family_box.setItems(observableArrayList(Font.getFamilies()));
        family_box.setValue(oldFont.getFamily());
        style_box.setItems(observableArrayList(
                "Regular", "Bold",
                "Bold Italic", "Italic")
        );

        style_box.setValue(oldFont.getStyle());
        size_slider.valueProperty().addListener((observable, oldValue, newValue) ->
                size.setText(String.valueOf(newValue.intValue())));
        size_slider.setValue(oldFont.getSize());
    }

    @FXML public void save(ActionEvent event) {
        Font font;
        String family = family_box.getValue();
        double val = size_slider.getValue();
        switch (style_box.getValue()) {
            case "Regular": font = Font.font(family, val); break;
            case "Bold": font = Font.font(family, FontWeight.BOLD, FontPosture.REGULAR, val); break;
            case "Italic": font = Font.font(family, FontPosture.ITALIC, val); break;
            case "Bold Italic": font = Font.font(family, FontWeight.BOLD, FontPosture.ITALIC, val); break;
            default:
                throw new NullPointerException();
        }
        Word.WORD.setFont(font);
        exit(event);
    }

    @FXML public void exit(ActionEvent event) {
        Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}