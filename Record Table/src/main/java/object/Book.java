package object;

import javafx.collections.*;
import org.apache.commons.csv.*;
import sort.Sort;
import sort.impls.None;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Book{
    public static final Book SINGLTONE = new Book();
    private int limit = -1;
    private Sort sorting = new None();
    private ObservableList<Person> list = FXCollections.observableArrayList();

    private Book(){
    }

    private void add(Person p){
        if(limit > list.size() || limit == -1)
            list.add(p);
        sorting.sort(list);
    }

    public void add(Person... p){
        for (Person val : p)
            add(val);
    }

    public void set(Person last_val, Person new_val){
        list.remove(last_val);
        list.add(new_val);
        sorting.sort(list);
    }

    public void delete(Person... people){
        list.removeAll(people);
        sorting.sort(list);
    }

    public void clear(){
        list.clear();
    }

    public void setLimit(int limit) {
        if(limit == 0 || limit < -1){
            throw new IllegalArgumentException("Negative or zero limit");
        }
        if(limit == -1){
            this.limit = -1;
            return;
        }
        this.limit = limit;
        if(limit < list.size()){
            list.remove(limit, list.size());
        }
    }

    public void setSort(Sort type_sort) {
        this.sorting = type_sort;
        sorting.sort(list);
    }


    public ObservableList<Person> getList() {
        return list;
    }

    public int size(){ return list.size(); }

    public void removeDuplicate(){
        List<Person> l = list.
                stream().
                distinct().
                collect(Collectors.toList());
        list.clear();
        list.addAll(l);
    }

    public int getLimit() {
        return limit;
    }

    public Sort getSorting() {
        return sorting;
    }

    public void write(String filename){
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(
                        filename));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Name", "Email", "Phone", "About"))) {
            for (Person p : list) {
                csvPrinter.printRecord(p.getName(), p.getEmail(), p.getPhone(), p.getAbout());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open(String filename) throws IOException {
        list.clear();
        FileReader reader = new FileReader(filename);
        for (CSVRecord next : CSVFormat.DEFAULT.
                withHeader("Name", "Email", "Phone", "About").
                withFirstRecordAsHeader().
                parse(reader)) {
            list.add(new Person(next.get("Name"), next.get("Email"), next.get("Phone"), next.get("About")));
        }
        sorting.sort(list);
    }

    @Override
    public String toString() {
        return list.
                stream().
                map(Person::getName).
                collect(Collectors.joining(", "));
    }
}