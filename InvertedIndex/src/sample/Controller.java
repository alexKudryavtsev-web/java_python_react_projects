package sample;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import structure.InvertedIndex;

import java.io.File;
import java.util.List;

public class Controller {
    public TableColumn<InvertedIndex.Result, String> col_index;
    public TableColumn<InvertedIndex.Result, List<Integer>> col_files;
    public TableColumn<InvertedIndex.Result, String> col_word;
    public TableView table;
    public TreeView<String> file_tree;
    public TextField request;
    public CheckBox isShow;

    private ObservableList<File> list_files =
            FXCollections.observableArrayList();
    private TreeItem<String> root =
            new TreeItem<>("files");
    private ObservableList<InvertedIndex.Result> data =
            FXCollections.observableArrayList();

    public void initialize(){
        col_files.setCellValueFactory(
                new PropertyValueFactory<>("files"));
        col_index.setCellValueFactory(
                new PropertyValueFactory<>("index"));
        col_word.setCellValueFactory(
                new PropertyValueFactory<>("word"));

        file_tree.setRoot(root);
        isShow.setOnAction(event ->
            col_index.setVisible(isShow.isSelected())
        );
        table.setItems(data);
    }

    public void add(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(
                "TXT", "*.txt"
        ));
        File file = chooser.showOpenDialog(((Node) event.getSource()).
                getScene().
                getWindow());
        list_files.add(file);
        root.getChildren().add(new TreeItem<>(file.getName()));
    }

    public void remove(ActionEvent event) {
        TreeItem<String> sel = file_tree.getSelectionModel().getSelectedItem();
        if(sel.getValue().equals("files")) return;
        int i = sel.getParent().getChildren().indexOf(sel);
        list_files.remove(i);
        sel.getParent().getChildren().remove(sel);
    }

    public void complete_request(ActionEvent event) {
        InvertedIndex obj = new InvertedIndex(request.getText(), list_files.stream().toArray(File[]::new));
        ObservableList<InvertedIndex.Result> val = FXCollections.observableArrayList(obj.result());
        data.addAll(val);
    }

    public void clear(ActionEvent event) {
        data.clear();
    }
}