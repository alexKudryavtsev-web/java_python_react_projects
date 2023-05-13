package controller;

import element.Cursor;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.*;

import static javafx.collections.FXCollections.*;

public class BrushTuner {
    public ComboBox<Integer> lineBox;
    public ColorPicker picker;

    private Color color;
    private int line_width;
    private Cursor cursor = Cursor.CURSOR;

    public void initialize(){
        List<Integer> collect = IntStream.range(2, 24).boxed().collect(Collectors.toList());
        lineBox.setItems(observableArrayList(collect));
        lineBox.setValue(2);
        color = Color.BLACK;
        picker.setValue(color);
    }

    @FXML public void setLine(ActionEvent event) {
        line_width = lineBox.getValue();
    }

    @FXML public void save(ActionEvent event) {
        cursor.brushColor(color);
        cursor.brushLine(line_width);
        close(event);
    }

    @FXML public void setColor(ActionEvent event) {
        color = picker.getValue();
    }

    @FXML public void close(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}