package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import object.Book;
import sort.impls.*;

public class Setting {
    public TextField field_count;
    public ComboBox<String> box_sort;
    private Book book = Book.SINGLTONE;

    public void initialize(){
        field_count.setText(String.valueOf(book.getLimit()));
        box_sort.setValue(book.getSorting().toString());
        box_sort.getItems().addAll(
                "Alphabet", "Length",
                "None", "Shuffled", "Stack");
        field_count.setTooltip(new Tooltip("To remove the limit, enter -1"));
        book.setLimit(Integer.parseInt(field_count.getText()));
        box_sort.setOnAction(event -> {
            switch (box_sort.getValue()){
                case "Alphabet":
                    book.setSort(new AlphabetSort());
                    break;
                case "Length":
                    book.setSort(new LengthSort());
                    break;
                case "None":
                    book.setSort(new None());
                    break;
                case "Shuffled":
                    book.setSort(new ShuffledSort());
                    break;
                case "Stack":
                    book.setSort(new StackSort());
                    break;
            }
        });
    }

    public void setLength(ActionEvent event) {
        book.setLimit(Integer.parseInt(field_count.getText()));
    }
}