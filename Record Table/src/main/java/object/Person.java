package object;

import java.util.Objects;

public class Person {
    private String name,  email, phone, about;

    public Person(String name, String email, String phone, String description){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.about = description;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void set(String name, String email, String phone, String about){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.about = about;
    }

    @Override
    public String toString() {
        return name + "," + email ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getPhone(), getAbout());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return name.equals(person.name) &&
                email.equals(person.email) &&
                phone.equals(person.phone) &&
                about.equals(person.about);
    }
}