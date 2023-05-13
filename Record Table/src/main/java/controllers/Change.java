package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import object.Book;
import object.Person;

public class Change {
    public TextField name;
    public TextField email;
    public TextField phone;
    public TextField about;
    public ChoiceBox<Person> box;
    private Book book = Book.SINGLTONE;

    public void initialize(){
        ObservableList<Person> list = book.getList();
        for (Person p : list) {
            box.getItems().add(p);
        }
        box.setValue(list.get(0));
    }

    public void save(ActionEvent event) {
        Person val = box.getValue();
        Person person = new Person(name.getText(), email.getText(), phone.getText(), about.getText());
        book.set(val, person);
    }

    public void cancel(ActionEvent event) {
        setBox(event);
    }

    public void setBox(ActionEvent event) {
        Person val = box.getValue();
        name.setText(val.getName());
        email.setText(val.getEmail());
        phone.setText(val.getPhone());
        about.setText(val.getAbout());
    }
}