package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import object.Book;
import object.Person;

public class AddController {

    public TextField email;
    public TextField about;
    public TextField phone;
    public TextField name;

    public void add(ActionEvent event) {
        Book.SINGLTONE.add(new Person(
                name.getText(), email.getText(),
                phone.getText(), about.getText()));

    }

    public void clear(ActionEvent event) {
        email.clear();
        about.clear();
        phone.clear();
        name.clear();
    }
}
