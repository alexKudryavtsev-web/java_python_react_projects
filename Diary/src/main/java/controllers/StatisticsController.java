package controllers;

import features.Statistics;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;

import java.util.Map;

public class StatisticsController {
    @FXML
    public TextField symbolsField;
    @FXML
    public TextField wordsField;
    @FXML
    public PieChart chart;
    @FXML
    public TextField sizeField;
    @FXML
    public TextField digitsField;
    @FXML
    public TextField spacesField;

    public void setDate(Statistics obj) {
        symbolsField.setText(String.valueOf(obj.countSymbol()));
        wordsField.setText(String.valueOf(obj.countWord()));
        sizeField.setText(String.valueOf(obj.getSize()));
        digitsField.setText(String.valueOf(obj.countDigits()));
        spacesField.setText(String.valueOf(obj.countSpaces()));

        Map<Character, Float> sym_percent =
                obj.frequencyTable();

        chart.setTitle("Graph with letter frequencies");
        chart.setStyle("-fx-font-size: 12px;");
        for (Character ch : sym_percent.keySet()) {
            chart.getData().add(new PieChart.Data(String.valueOf(ch), sym_percent.get(ch)));
        }

        chart.setLegendSide(Side.LEFT);
        chart.setStartAngle(30);
    }


}
