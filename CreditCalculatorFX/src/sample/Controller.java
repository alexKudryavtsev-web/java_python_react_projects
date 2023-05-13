package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import object.Annuity;
import object.CreditCalculator;
import object.Differentiated;

import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Controller {
    public GridPane input_pane;
    public TextField sum_field;
    public TextField precent_field;
    public TextField month_field;
    public RadioButton annuity;
    public RadioButton differ;
    public GridPane res_pane;
    public TextField loan;
    public TextField overpay;
    public TextField total;
    public TextField monthly_pay;
    public ComboBox<Month> start_month;
    public ComboBox<Integer> start_year;
    public TextField end_year;
    public TextField end_month;
    public Button perform_clear;
    public Button show;

    private CreditCalculator CALC;
    private boolean hasCalled = false;

    public void initialize() {
        Calendar now = Calendar.getInstance();
        int st_year = now.get(Calendar.YEAR);
        List<Integer> year_list = IntStream.range(st_year, st_year + 10).
                boxed().
                collect(Collectors.toList());
        start_year.getItems().addAll(year_list);
        for (Month m : Month.values())
            start_month.getItems().add(m);
        start_month.setValue(Month.of(now.get(Calendar.MONTH)));
        start_year.setValue(now.get(Calendar.YEAR));
    }

    public void perform(ActionEvent event) {
        if (!hasCalled) {
            Calendar start = Calendar.getInstance();
            start.set(
                    start_year.getValue(),
                    start_month.getValue().getValue(),
                    start.get(Calendar.DAY_OF_MONTH));

            hasCalled = true;
            int sum = Integer.parseInt(sum_field.getText());
            int month = Integer.parseInt(month_field.getText());
            float precent = Integer.parseInt(precent_field.getText());

            if (annuity.isSelected()) {
                CALC = new Annuity(month, sum, precent, start);
            } else
                CALC = new Differentiated(month, sum, precent, start);

            input_pane.setVisible(false);
            res_pane.setVisible(true);
            show.setVisible(true);
            monthly_pay.setText(String.valueOf(
                    round(CALC.monthlyPayment())));
            loan.setText(String.valueOf(
                    round(CALC.loanOverpayment())));
            overpay.setText(String.valueOf(
                    round(CALC.overpayPercent())));
            total.setText(String.valueOf(
                    round(CALC.totalAmount())));

            perform_clear.setText("Clear");
            Calendar end = CALC.endPayout();
            System.out.println(end.getTime());
            int i = end.get(Calendar.MONTH);
            i++;
            end_month.setText(String.valueOf(
                    Month.of(i)));
            end_year.setText(String.valueOf(end.get(Calendar.YEAR)));
        } else {
            perform_clear.setText("Perform");
            show.setVisible(false);
            hasCalled = false;
            CALC = null;
            input_pane.setVisible(true);
            res_pane.setVisible(false);

            sum_field.clear();
            precent_field.clear();
            month_field.clear();
        }
    }

    public void show(ActionEvent event) {
        Node obj;
        String text;
        if(annuity.isSelected()) {
            PieChart pieChart = new PieChart();
            pieChart.getData().addAll(
                    new PieChart.Data("Overpay Percent", CALC.overpayPercent()),
                    new PieChart.Data("All credit", 100 - CALC.overpayPercent()));
            obj = pieChart;
            text = "Overpay Percent";
        }else {
            Differentiated c = (Differentiated) CALC;
            Map<Integer, Double> map = c.mapAmountPayment();

            BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            ObservableList<XYChart.Data<String, Number>> date = FXCollections.observableArrayList();
            for (Integer i : map.keySet())
                date.add(new XYChart.Data<>(
                        String.valueOf(i),
                        map.get(i)));
            series.setData(date);
            chart.getData().add(series);
            text = "Monthly payments";
            series.setName(text);
            obj = chart;
        }
        newWindows(text,  obj);
    }

    private void newWindows(String title, Node element){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Pane pane = new Pane();
        pane.getChildren().addAll(element);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(pane));
        stage.showAndWait();
    }

    private double round(double num) {
        if (!String.valueOf(num).contains("."))
            return num;
        return (double) Math.round(num * 100) / 100;
    }
}