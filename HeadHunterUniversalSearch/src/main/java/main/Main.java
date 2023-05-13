package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.yaal.project.hhapi.dictionary.Constants;
import ru.yaal.project.hhapi.dictionary.entry.entries.proffield.ProfField;
import ru.yaal.project.hhapi.search.ISearch;
import ru.yaal.project.hhapi.search.ISearchParameter;
import ru.yaal.project.hhapi.search.SearchException;
import ru.yaal.project.hhapi.search.parameter.Period;
import ru.yaal.project.hhapi.search.parameter.Text;
import ru.yaal.project.hhapi.vacancy.Salary;
import ru.yaal.project.hhapi.vacancy.Vacancy;
import ru.yaal.project.hhapi.vacancy.VacancyList;
import ru.yaal.project.hhapi.vacancy.VacancySearch;
import search.RequestHandler;
import search.Worker;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.lang.System.out;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.
                    load(getClass().getResource("../gui.fxml"));
            primaryStage.setTitle("HH Search");
            primaryStage.getIcons().add(
                    new Image(getClass().getResource("/hhIcon.png").toURI().toString())
            );
            primaryStage.setScene(new Scene(root, 773, 475));
            primaryStage.show();
        } catch (IOException exc) {
            exc.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SearchException {
        launch(args);
       // Добавить Constants.Gender
       // Добавить Constants.RelocationType
        /*
        RequestHandler handler = new RequestHandler();
        handler.getParameters().addAll(new Text("python",
                        Constants.VacancySearchFields.VACANCY_NAME),
        ProfField.PROF_FIELDS.getById("1.221"),
                new Salary(3000, 5000, Constants.Currency.USD),
                Constants.Experience.BETWEEN_3_AND_6,
                Constants.OnlyWithSalary.ON,
                Constants.Order.SALARY_DESC,
                Constants.Schedule.FULL_DAY,
                Constants.Employment.FULL
        );
        List<Worker> results =
                handler.getResults();

        for (Worker worker : results) {
            System.out.println(worker);
        }
        Vacancy v0 = vacancies.get(0);

        out.println("id => " + v0.getId());
        out.println("name => " + v0.getName());
        out.println("salary =>" + v0.getSalary());
        out.println("profField =>" + v0.getProfFields());
        out.println("experience => " + v0.getExperience());
        out.println(v0.getAcceptHandicapped());
        out.println("address =>" + v0.getAddress());
        out.println("area" + v0.getArea());
        out.println("shedule" + v0.getSchedule());
        out.println("url => " + v0.getUrl());
        out.println("archived => " + v0.getArchived());
        out.println("description => " + v0.getDescription());
        out.println("createAt => " + v0.getCreatedAt());
        out.println("employer => " + v0.getEmployer());
        out.println("type => " + v0.getType());
        out.println(v0.getResponseLetterRequired());
        out.println("relations => " + v0.getRelations());
        out.println("employment => " + v0.getEmployment());
        out.println("alternateURL => " + v0.getAlternateUrl());
        */
    }
}
