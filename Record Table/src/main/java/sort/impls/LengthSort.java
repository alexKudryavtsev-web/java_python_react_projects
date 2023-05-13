package sort.impls;

import object.Person;
import sort.Sort;

import java.util.Comparator;
import java.util.List;

public class LengthSort implements Sort {

    @Override
    public void sort(List<Person> list) {
        list.sort((Comparator.comparingInt(o -> o.getName().length())));
    }

    @Override
    public String toString() {
        return "Length";
    }
}
