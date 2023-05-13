package controller;

import element.Cursor;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.util.*;
import java.util.stream.*;

import static javafx.collections.FXCollections.observableArrayList;

public class EraserTuner {
    @FXML public ComboBox<Integer> lineBox;

    private int line_width;
    private Cursor cursor = Cursor.CURSOR;

    public void initialize(){
        List<Integer> collect = IntStream.range(2, 24).boxed().collect(Collectors.toList());
        lineBox.setItems(observableArrayList(collect));
        lineBox.setValue(2);
    }

    @FXML public void setLine(ActionEvent event) {
        line_width = lineBox.getValue();
    }

    @FXML public void save(ActionEvent event){
        cursor.eraserLine(line_width);
        close(event);
    }

    @FXML public void close(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}