package controllers;

import diary.Diary;
import diary.Record;
import features.Statistics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RecordGuiController {
    @FXML
    public HTMLEditor editorField;
    @FXML
    public WebView readField;
    @FXML
    public CheckBox readingModeBox;
    @FXML
    public ToolBar toolbar;

    private Diary diary;
    private Record record;
    private Tab tab;

    public void initialize() {
        readingModeBox.setOnAction(event -> {
            if (readingModeBox.isSelected()) {
                editorField.setVisible(false);
                readField.setVisible(true);

                readField.getEngine().loadContent(getClearHTML(editorField.getHtmlText()));
            } else {
                editorField.setVisible(true);
                readField.setVisible(false);
            }
        });
    }

    public void setRecord(Diary diary, Record record, Tab tab) {
        this.diary = diary;
        this.record = record;
        this.tab = tab;
        editorField.setHtmlText(record.getContent());
        ContextMenu menu = makeContextMenu(editorField, toolbar);
        tab.setContextMenu(menu);
    }

    @FXML
    public void delete(ActionEvent event) {
        clear(event);
        TabPane tabs = tab.getTabPane();
        if (tabs.getTabs().size() != 1)
            tabs.getTabs().remove(tab);
    }

    @FXML
    public void clear(ActionEvent event) {
        editorField.setHtmlText("");
    }

    @FXML
    public void showStatistics(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(
                getClass().
                        getResource("../statisticsWindow.fxml")
        );

        Stage stage = new Stage(StageStyle.UNIFIED);
        stage.setTitle("Statistics");
        stage.setResizable(false);

        String URL = getClass().
                getResource("/fxmls/statisticsWindow.fxml").
                toString();
        stage.getIcons().add(new Image(URL));

        try {
            stage.setScene(
                    new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StatisticsController controller =
                loader.getController();
        controller.setDate(new Statistics(record));
        stage.showAndWait();
    }

    @FXML
    public void save(ActionEvent event) {
        diary.getDiary().remove(record);
        record.setContent(editorField.getHtmlText());
        diary.getDiary().add(record);

        Stage stage = (Stage) ((Node) event.getSource()).
                getScene().
                getWindow();
        addAlertClosing(stage);
    }

    @FXML
    public void cancel(ActionEvent event) {
        editorField.setHtmlText(record.getContent());
    }

    private ContextMenu makeContextMenu(HTMLEditor editor, ToolBar bar) {
        ContextMenu menu = new ContextMenu();

        String URL = getClass().getResource("/icons/deleteIcon.png").toString();
        ImageView icon = new ImageView(new Image(URL));
        icon.setFitWidth(25);
        icon.setFitHeight(25);
        MenuItem removeItem = new MenuItem("Remove control panel", icon);
        removeItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+R"));
        removeItem.setOnAction(event -> {
            bar.setVisible(false);
            editor.setPrefHeight(426 + 40);
            readField.setPrefHeight(426 + 40);
        });

        String URL1 = getClass().getResource("/icons/addIcon.png").toString();
        ImageView icon1 = new ImageView(new Image(URL1));
        icon1.setFitWidth(25);
        icon1.setFitHeight(25);

        MenuItem returnItem = new MenuItem("Return control panel", icon1);
        returnItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+T"));
        returnItem.setOnAction(event -> {
            bar.setVisible(true);
            editor.setPrefHeight(426);
            readField.setPrefHeight(426);
        });

        menu.getItems().addAll(
                removeItem,
                returnItem
        );
        return menu;
    }


    private void addAlertClosing(Stage stage) {
        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save");
            alert.setHeaderText("Save result:");
            alert.setContentText("Save changes to your personal diary?");
            ButtonType type = alert.showAndWait().get();
            if (type == ButtonType.OK)
                diary.saveDiary();
        });
    }

    private String getClearHTML(String html) {
        StringBuilder ret = new StringBuilder(html);
        ret.delete(0, 58);
        ret.delete(ret.length() - 14, ret.length());
        return ret.toString();
    }
}
