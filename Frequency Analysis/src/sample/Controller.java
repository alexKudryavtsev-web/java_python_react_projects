package sample;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import object.*;

import java.io.*;
import java.util.Map;

public class Controller {
    public TreeView<String> tree_file;
    public TextArea input_text;
    public MenuBar bar;

    private ObservableList<File> list_files =
            FXCollections.observableArrayList();
    private TreeItem<String> root =
            new TreeItem<>("files");

    private FileChooser chooser = new FileChooser();
    private final Decryptor DECRYPTOR = new Decryptor();

    public void initialize() {
        tree_file.setRoot(root);
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(
                "TXT", "*.txt"
        ));
    }

    @FXML
    public void add(ActionEvent event) {
        File file = chooser.showOpenDialog(((Node) event.getSource()).
                getScene().
                getWindow());

        list_files.add(file);
        root.getChildren().add(new TreeItem<>(file.getName()));
    }

    @FXML
    public void remove(ActionEvent event) {
        TreeItem<String> sel = tree_file.getSelectionModel().getSelectedItem();
        if (sel.getValue().equals("files")) return;
        int i = sel.getParent().getChildren().indexOf(sel);
        list_files.remove(i);

        sel.getParent().getChildren().remove(sel);
    }

    @FXML
    public void show_result(ActionEvent event) {
        input_text.setText(decipher());
    }

    @FXML
    public void set(ActionEvent event) {
        remove(event);
        add(event);
    }

    @FXML
    public void clear(ActionEvent event) {
        root.getChildren().clear();
        list_files.clear();
    }

    @FXML
    public void write_file(ActionEvent event) {
        String res = DECRYPTOR.decipher();
        File file = chooser.showSaveDialog(bar.getScene().getWindow());
        try (FileOutputStream writer = new FileOutputStream(file)) {
            writer.write(res.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void file_frequency(ActionEvent event) {
        generateWindowsWithPieChart(new FrequencyTable(list_files).frequencyTable());
    }

    @FXML
    public void text_frequency(ActionEvent event) {
        generateWindowsWithPieChart(new FrequencyTable(DECRYPTOR.getOriginal()).frequencyTable());
    }

    private String decipher() {
        String text = input_text.getText();
        DECRYPTOR.setFiles(list_files);
        DECRYPTOR.setText(text);
        return DECRYPTOR.decipher();
    }

    private void generateWindowsWithPieChart(Map<Character, Float> sym_percent) {
        PieChart pieChart = new PieChart();
        for (Character ch : sym_percent.keySet()) {
            pieChart.getData().add(new PieChart.Data(String.valueOf(ch), sym_percent.get(ch)));
        }
        pieChart.setPrefSize(400, 300);

        pieChart.setLegendSide(Side.LEFT);
        pieChart.setStartAngle(30);

        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 12 arial;");
        for (final PieChart.Data val : pieChart.getData()) {
            val.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());

                caption.setText(String.valueOf(val.getPieValue()));
            });
        }

        Stage stage = new Stage();
        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(pieChart, caption);
        stage.setScene(new Scene(pane, 400, 300));
        stage.setResizable(false);
        stage.showAndWait();
    }
}