package controllers;

import controllers.RecordGuiController;
import diary.*;
import javafx.beans.InvalidationListener;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.*;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GuiController {
    @FXML
    public TabPane tabs;
    @FXML
    public DatePicker picker;

    private Diary diary = new Diary();

    @FXML
    public void initialize() {
        StringConverter<LocalDate> converter =
                new StringConverter<LocalDate>() {
                    DateTimeFormatter dateFormatter =
                            DateTimeFormatter.ofPattern(Diary.FORMAT);

                    @Override
                    public String toString(LocalDate date) {
                        if (date != null) {
                            return dateFormatter.format(date);
                        } else {
                            return "";
                        }
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        if (string != null && !string.isEmpty()) {
                            return LocalDate.parse(string, dateFormatter);
                        } else {
                            return null;
                        }
                    }
                };
        picker.setConverter(converter);
        picker.setValue(LocalDate.now());
        Record search = diary.search(getSelectedCalendar());
        addTab(search);
        tabs.getTabs().addListener((InvalidationListener) event -> {
            if (tabs.getTabs().size() == 1)
                tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
            else
                tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        });
    }

    @FXML
    public void search(ActionEvent event) {
        Record search = diary.search(getSelectedCalendar());
        addTab(search);
    }

    private void addTab(Record record) {
        Tab tab = new Tab(record.getDate());

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "../fxmls/recordGui.fxml"
                )
        );
        tabs.getTabs().add(tab);
        try {
            tab.setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        RecordGuiController controller =
                loader.getController();
        controller.setRecord(diary, record, tab);

        tabs.getSelectionModel().select(tab);
    }

    private ContextMenu makeContextMenu(HTMLEditor editor, ToolBar bar) {
        ContextMenu menu = new ContextMenu();

        MenuItem removeItem = new MenuItem("Remove control panel");
        removeItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+R"));
        removeItem.setOnAction(event -> {
            bar.setVisible(false);
            editor.setLayoutX(16);
            editor.setLayoutY(3);

            editor.setPrefSize(673, 423);
        });

        MenuItem returnItem = new MenuItem("Return control panel");
        returnItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+T"));
        returnItem.setOnAction(event -> {
            bar.setVisible(true);
            bar.setLayoutY(15);
            bar.setPrefSize(83, 385);

            editor.setLayoutX(90);
            editor.setPrefSize(604, 423);
        });

        menu.getItems().addAll(
                removeItem,
                returnItem
        );
        return menu;
    }


    private Calendar getSelectedCalendar() {
        LocalDate localDate = picker.getValue();
        return GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    }
}
