package search;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.yaal.project.hhapi.search.ISearch;
import ru.yaal.project.hhapi.search.ISearchParameter;
import ru.yaal.project.hhapi.search.SearchException;
import ru.yaal.project.hhapi.search.parameter.Text;
import ru.yaal.project.hhapi.vacancy.AbstractSearch;
import ru.yaal.project.hhapi.vacancy.Vacancy;
import ru.yaal.project.hhapi.vacancy.VacancyList;
import ru.yaal.project.hhapi.vacancy.VacancySearch;

import java.util.List;
import java.util.stream.Collectors;

public class RequestHandler {

    private ObservableList<ISearchParameter> parameters =
            FXCollections.observableArrayList();
    private int limit = 10;
    private Text text;
    public RequestHandler() {

    }

    public ObservableList<ISearchParameter> getParameters() {
        return parameters;
    }

    public void setText(Text reqText){
        text = reqText;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Worker> getResults() throws SearchException {
        AbstractSearch<VacancyList> vacancySearch = new VacancySearch(limit).
                addParameter(text);
        for (int i = 0; i < parameters.size(); i++) {
            ISearchParameter par = parameters.get(i);
            try{
                vacancySearch = vacancySearch.addParameter(par);
            } catch (SearchException e) {
                e.printStackTrace();
            }
        }
        return vacancySearch.search().
                stream().
                map(vacancy -> {
                            try {
                                return new Worker(vacancy);
                            } catch (SearchException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                ).
                collect(Collectors.toList());
    }

}
