package search;

import javafx.scene.control.Hyperlink;
import ru.yaal.project.hhapi.search.SearchException;
import ru.yaal.project.hhapi.vacancy.Salary;
import ru.yaal.project.hhapi.vacancy.Vacancy;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Worker {
    private Hyperlink idUrl;
    private String spec;
    private String startVac;
    private String salary;
    private String htmlDescription;

    public Worker(Vacancy v) throws SearchException {
        startVac = new SimpleDateFormat("dd MMM yyyy").
                format(v.getCreatedAt());
        Salary s = v.getSalary();
        this.salary = s.getFrom() + "-" + s.getTo() + " " + s.getCurrency().getAbbr();
        spec = v.getName();
        htmlDescription = v.getDescription();
        idUrl = new Hyperlink(v.getId());
        idUrl.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(v.getUrl().toURI());
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public String getStartVac() {
        return startVac;
    }

    public Hyperlink getIdUrl() {
        return idUrl;
    }

    public String getSpec() {
        return spec;
    }

    public String getSalary() {
        return salary;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setIdUrl(Hyperlink idUrl) {
        this.idUrl = idUrl;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setStartVac(String startVac) {
        this.startVac = startVac;
    }

    @Override
    public String toString() {
        return idUrl.getText() + ", " + startVac + ", " + spec;
    }
}
