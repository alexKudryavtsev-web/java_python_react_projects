package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import object.*;
import object.Reader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Controller {
    public TextField path;
    public Pagination data;
    public Text errors_field;
    public MenuItem save_button;
    public ContextMenu menu_bar;
    private List<Node> nodes = new ArrayList<>();
    private String parse;

    public void initialize(){
    }

    public void clear(ActionEvent event) {
        path.clear();
        errors_field.setVisible(true);
        data.setPageFactory(HBox::new);
        errors_field.setText("No data");
    }

    public void search(ActionEvent event)  {
        try {
            parse = new Parser(path.getText()).parse();
            Reader reader = new Reader(parse);
            nodes.clear();
            nodes.add(toTable(reader.table()));
            nodes.add(toTable(reader.domaimStatus()));
            nodes.add(toList(reader.servers()));
            nodes.add(toText(reader.notice()));
            errors_field.setVisible(false);
            data.setPageFactory(this::create);
        }catch (Exception exc){
            errors_field.setText("Errors");
        }
    }

    public void save(ActionEvent event) {
        if (errors_field.getText().equals("Errors") ||
                errors_field.getText().equals("No data"))
            return;
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("TXT", "*.txt")
        );
        File f = chooser.showSaveDialog(menu_bar.getOwnerWindow());
        Path path = Paths.get(f.getPath());
        try {
            Files.write(path, parse.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Node create(int index){
        return nodes.get(index);
    }

    private ListView<String> toList(List<String> list){
        ListView<String> view = new ListView<>();
        view.getItems().addAll(list);
        return view;
    }

    private Text toText(String str){
        Text text = new Text(str);
        text.setFont(Font.font("System", 14));
        text.setFill(Color.GREY);
        return text;
    }

    private TableView<Data> toTable(List<Data> list){
        TableView<Data> view = new TableView<>();
        TableColumn<Data, String> c1 = new TableColumn<>("name");
        TableColumn<Data, String> c2 = new TableColumn<>("value");

        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setCellValueFactory(new PropertyValueFactory<>("value"));
        view.getColumns().addAll(c1, c2);

        view.setItems(FXCollections.observableArrayList(list));
        return view;
    }
}