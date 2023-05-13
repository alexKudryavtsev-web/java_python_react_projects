package controller;

import element.Photo;

import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class FitImage {
    public TextField width_field;
    public TextField height_field;

    public void exit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent event) {
        Photo.PHOTO.setHeight(Integer.parseInt(height_field.getText()));
        Photo.PHOTO.setWidth(Integer.parseInt(width_field.getText()));
        exit(event);
    }
}