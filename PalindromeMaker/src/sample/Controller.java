package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Controller {
    public TextField number_field;
    public TextField result;
    public TextField count;

    private Palindrome p = new Palindrome();

    public void operation(ActionEvent event) {
        long[] couple = p.create(Long.valueOf(number_field.getText()));
        result.setText(String.valueOf(couple[0]));
        count.setText(String.valueOf(couple[1]));
    }

    public void clear(ActionEvent event) {
        number_field.clear();
        result.clear();
        count.clear();
    }
}
