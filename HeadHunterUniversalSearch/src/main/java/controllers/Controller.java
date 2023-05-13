package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.ToggleSwitch;
import ru.yaal.project.hhapi.dictionary.Constants;
import ru.yaal.project.hhapi.dictionary.entry.entries.small.Currency;
import ru.yaal.project.hhapi.search.SearchException;
import ru.yaal.project.hhapi.search.parameter.Text;
import ru.yaal.project.hhapi.vacancy.Salary;
import search.RequestHandler;
import search.Worker;

import java.util.Date;
import java.util.List;

public class Controller {
    @FXML
    public TextField type;
    @FXML
    public ComboBox<String> employmentBox;
    @FXML
    public ComboBox<String> orderBox;
    @FXML
    public ComboBox<String> scheduleBox;
    @FXML
    public ComboBox<String> experienceBox;
    @FXML
    public TextField addressField;
    @FXML
    public RangeSlider salarySlider;
    @FXML
    public TextField lowSalary;
    @FXML
    public ToggleSwitch isOnlySalary;
    @FXML
    public TextField hightSalary;

    @FXML
    public TableColumn<Worker, Hyperlink> idColumn;
    @FXML
    public TableColumn<Worker, String> specColumn;
    @FXML
    public TableColumn<Worker, Date> dateStart;
    @FXML
    public TableColumn<Worker, String> salaryColmn;
    @FXML
    public TableView table;
    @FXML
    public TextField limit;
    @FXML
    public ComboBox<String> searchTypeBox;

    private RequestHandler handler =
            new RequestHandler();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("idUrl"));
        specColumn.setCellValueFactory(
                new PropertyValueFactory<>("spec"));
        dateStart.setCellValueFactory(
                new PropertyValueFactory<>("startVac"));
        salaryColmn.setCellValueFactory(
                new PropertyValueFactory<>("salary"));

        isOnlySalary.selectedProperty().addListener(observable -> {
            if (!isOnlySalary.isSelected()) {
                lowSalary.setVisible(false);
                hightSalary.setVisible(false);
                salarySlider.setVisible(false);
            } else {
                lowSalary.setVisible(true);
                hightSalary.setVisible(true);
                salarySlider.setVisible(true);
            }
        });

        salarySlider.setMax(100);
        salarySlider.setMin(0);
        salarySlider.setShowTickLabels(true);
        salarySlider.highValueProperty().addListener(observable -> {
            hightSalary.setText(((int) salarySlider.getHighValue()) + "k");
        });
        salarySlider.highValueProperty().addListener(observable -> {
            lowSalary.setText(((int) salarySlider.getLowValue()) + "k");
        });

        employmentBox.getItems().addAll(
                "Полный день",
                "Волонтер",
                "Стажер",
                "Проектная работа",
                "Частичная занятость" // emploement
        );

        searchTypeBox.getItems().addAll(
                "По вакансии",
                "По компании",
                "По описанию"
        );
        searchTypeBox.setValue("По вакансии");

        orderBox.getItems().addAll(
                "Актуальность", // Relevance
                "Время публикации", //Publication time
                "SALARY ASC",
                "SALARY DESC"
        );

        scheduleBox.getItems().addAll(
                "Полный день",
                "Гибкий", //Flex
                "Удаленная",
                "Сменный график"
        );

        experienceBox.getItems().addAll(
                "От 6 лет",
                "От 3 до 6 лет",
                "От 1 до 3 лет",
                "Без опыта"
        );
    }

    @FXML
    public void selectEmployment(ActionEvent event) {
        switch (employmentBox.getValue()) {
            case "Полный день":
                handler.getParameters().add(Constants.Employment.FULL);
                break;
            case "Волонтер":
                handler.getParameters().add(Constants.Employment.VOLUNTEER);
                break;
            case "Стажер":
                handler.getParameters().add(Constants.Employment.PROBATION);
                break;
            case "Проектная работа":
                handler.getParameters().add(Constants.Employment.PROJECT);
                break;
            case "Частичная занятость":
                handler.getParameters().add(Constants.Employment.PART);
                break;
        }
    }

    @FXML
    public void selectSearchType(ActionEvent event) {
        switch (searchTypeBox.getValue()) {
            case "По вакансии":
                handler.setText(new Text(type.getText(), Constants.VacancySearchFields.VACANCY_NAME));
                break;
            case "По компании":
                handler.setText(new Text(type.getText(), Constants.VacancySearchFields.COMPANY_NAME));
                break;
            case "По описанию":
                handler.setText(new Text(type.getText(), Constants.VacancySearchFields.DESCRIPTION));
                break;
        }
    }


    @FXML
    public void selectOrder(ActionEvent event) {
        switch (orderBox.getValue()) {
            case "Актуальность":
                handler.getParameters().add(Constants.Order.RELEVANCE);
                break;
            case "Время публикации":
                handler.getParameters().add(Constants.Order.PUBLICATION_TIME);
                break;
            case "SALARY ASC":
                handler.getParameters().add(Constants.Order.SALARY_ASC);
                break;
            case "SALARY DESC":
                handler.getParameters().add(Constants.Order.SALARY_DESC);
                break;
        }
    }

    @FXML
    public void selectSchedule(ActionEvent event) {
        switch (scheduleBox.getValue()) {
            case "Полный день":
                handler.getParameters().add(Constants.Schedule.FULL_DAY);
                break;
            case "Гибкий":
                handler.getParameters().add(Constants.Schedule.FLEXIBLE);
                break;
            case "Удаленная":
                handler.getParameters().add(Constants.Schedule.REMOTE);
                break;
            case "Сменный график":
                handler.getParameters().add(Constants.Schedule.SHIFT);
                break;
        }
    }

    @FXML
    public void selectExperience(ActionEvent event) {
        switch (experienceBox.getValue()) {
            case "От 6 лет":
                handler.getParameters().add(Constants.Experience.MORE_THAN_6);
                break;
            case "От 3 до 6 лет":
                handler.getParameters().add(Constants.Experience.BETWEEN_3_AND_6);
                break;
            case "От 1 до 3 лет":
                handler.getParameters().add(Constants.Experience.BETWEEN_1_AND_3);
                break;
            case "Без опыта":
                handler.getParameters().add(Constants.Experience.NO_EXPERIENCE);
                break;
        }
    }

    @FXML
    public void search(ActionEvent event) throws SearchException {
        Text t = new Text(type.getText(), Constants.VacancySearchFields.VACANCY_NAME);
        handler.setText(t);
        handler.setLimit(Integer.parseInt(limit.getText()));

        String low = lowSalary.getText();
        String hight = hightSalary.getText();
        int st = 0, en = 0;
        if (low.equals(""))
            st = Integer.parseInt(lowSalary.getText(0, low.length() - 2)) * 1000;
        if (hight.equals(""))
            en = Integer.parseInt(hightSalary.getText(0, hight.length() - 2)) * 1000;

        Salary salary = new Salary(st, en, Constants.Currency.RUR);
        handler.getParameters().add(salary);
        List<Worker> results = handler.getResults();
        table.setItems(FXCollections.observableList(results));
    }

    public void showMap(ActionEvent event) {
    }
}
