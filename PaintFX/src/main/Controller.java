package main;

import element.Photo;
import element.Picture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import element.Cursor;
import memento.ListState;

import java.io.*;

public class Controller {
    public Canvas canvas;
    public Button brush_button;
    public Button eraser_button;
    public Pane input_node;
    public Button file_button;
    public VBox button_root;

    // ELEMENT
    private Cursor CURSOR = Cursor.CURSOR;
    private Picture PICTURE = Picture.PICTURE;
    private Photo PHOTO = Photo.PHOTO;
    private ListState STATES = ListState.LIST_STATE;

    private RadioMenuItem save = new RadioMenuItem("Save");

    private FileChooser chooser = new FileChooser();

    public void initialize() {
        PICTURE.init(canvas, input_node);
        CURSOR.setCanvas(canvas);
        ListState.LIST_STATE.add();

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"));

        MenuItem setting_brush = new MenuItem("Setting brush");
        setting_brush.setOnAction(event ->
                showModalWindows("Brush Setting",
                        "../samples/brush.fxml"));
        setMenuItemOnNode(brush_button, setting_brush);

        MenuItem setting_eraser = new MenuItem("Setting eraser");

        setting_eraser.setOnAction(event ->
                showModalWindows("Setting eraser",
                        "../samples/eraser.fxml"));
        MenuItem clear = new MenuItem("Clear all");

        clear.setOnAction(event -> PICTURE.clear());
        setMenuItemOnNode(eraser_button, setting_eraser,
                new SeparatorMenuItem(), clear);

        RadioMenuItem open = new RadioMenuItem("Open");
        ToggleGroup group = new ToggleGroup();

        open.setSelected(true);
        open.setToggleGroup(group);
        save.setToggleGroup(group);

        setMenuItemOnNode(file_button, open, save);
    }

    @FXML public void file(ActionEvent event) throws IOException {
        Window window = ((Node) event.getSource()).getScene().getWindow();
        if (save.isSelected()) {
            File image_file = chooser.showSaveDialog(window);
            PICTURE.saveCanvas(image_file);
        } else {
            File file = chooser.showOpenDialog(window);
            PICTURE.loadFile(file);
            save.setSelected(true);
        }
    }

    @FXML public void photo(ActionEvent event) {
        canvas.setDisable(true);
        button_root.setDisable(false);
        File file = chooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        PHOTO.loadImage(file);
    }

    @FXML public void brush(ActionEvent event) {
        CURSOR.addBrush();
    }

    @FXML public void eraser(ActionEvent event) {
        CURSOR.addEraser();
    }

    @FXML public void text(ActionEvent event) {
        canvas.setDisable(true);
        button_root.setDisable(false);
        showModalWindows("Text", "../samples/text.fxml");
    }

    @FXML public void next(ActionEvent event) {
        STATES.loadNext();
    }

    @FXML public void back(ActionEvent event) {
        STATES.loadLast();
    }

    private void setMenuItemOnNode(Node node, MenuItem... item) {
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(item);
        node.setOnContextMenuRequested(event ->
                contextMenu.show(node, event.getScreenX(), event.getScreenY())
        );
    }

    private void showModalWindows(String title, String path) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    getClass().
                            getResource(path));
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }

}