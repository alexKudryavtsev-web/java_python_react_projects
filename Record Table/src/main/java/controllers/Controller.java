package controllers;

import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.*;
import object.Book;
import object.Person;

import java.io.File;
import java.io.IOException;

public class Controller {
    public MenuItem open;
    public MenuItem save;
    public MenuItem adder;
    public MenuItem deleted;
    public MenuItem clear;
    public MenuItem replaced;
    public MenuItem duplicate;
    public MenuItem settings;
    public Menu added;
    public MenuBar bar;
    private Book book = Book.SINGLTONE;
    private FileChooser chooser = new FileChooser();
    public TableView<Person> table;
    public TableColumn<Person, String> name_column;
    public TableColumn<Person, String> email_column;
    public TableColumn<Person, String> phone_column;
    public TableColumn<Person, String> comment_column;
    public Label size;

    public void initialize() {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv"));

        adder.setAccelerator(
                KeyCombination.keyCombination("SHORTCUT+V")
        );
        deleted.setAccelerator(
                KeyCombination.keyCombination("SHORTCUT+R")
        );
        clear.setAccelerator(
                KeyCombination.keyCombination("SHORTCUT+P")
        );
        replaced.setAccelerator(
                KeyCombination.keyCombination("SHORTCUT+F")
        );
        duplicate.setAccelerator(
                KeyCombination.keyCombination("SHORTCUT+D")
        );

        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        email_column.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone_column.setCellValueFactory(new PropertyValueFactory<>("phone"));
        comment_column.setCellValueFactory(new PropertyValueFactory<>("about"));
        table.setItems(book.getList());
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        book.getList().
                addListener(((Observable observable) ->
            size.setText("Size: " + book.size())));

        settings.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(
                        getClass().
                                getResource("../samples/setting.fxml"));

                stage.setTitle("Setting");
                stage.getIcons().add(new Image(
                        "https://cdn.icon-icons.com/icons2/390/PNG/512/auto-repair_39384.png"));
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }catch (IOException exc){
                System.out.println(exc);
            }
        });
        adder.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(
                        getClass().
                                getResource("../samples/add.fxml"));

                stage.setTitle("Add record");
                stage.getIcons().add(new Image("http://www.graycell.ru/picture/big/plyus.jpg"));
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }catch (IOException exc){
                System.out.println(exc);
            }
        });
        clear.setOnAction(event -> book.clear());
        duplicate.setOnAction(event -> book.removeDuplicate());
        deleted.setOnAction(event -> {
            book.delete(table.getSelectionModel().
                    getSelectedItems().
                    stream().
                    toArray(Person[]::new));
        });
        replaced.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(
                        getClass().
                                getResource("../samples/setter.fxml"));

                stage.setTitle("Replaced");
                stage.getIcons().add(new Image(
                        "https://encrypted-tbn0.gstatic.com/" +
                                "images?q=tbn:ANd9GcT_0G-iuirfU1_Y2jvz9JEQ1U2q_" +
                                "icvobDGo3Vklrjemk9PXFMe"));
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }catch (IOException exc){
                System.out.println(exc);
            }
        });

        open.setOnAction(event -> {
            File f = chooser.showOpenDialog(bar.getScene().getWindow());
            try {
                book.open(f.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        save.setOnAction(event -> {
            File f = chooser.showSaveDialog(bar.getScene().getWindow());
            book.write(f.getPath());
        });
    }
}