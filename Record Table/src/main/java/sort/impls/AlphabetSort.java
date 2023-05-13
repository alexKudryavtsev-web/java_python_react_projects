package sort.impls;

import object.Person;
import sort.Sort;

import java.util.List;

public class AlphabetSort implements Sort{
    @Override
    public void sort(List<Person> list) {
        list.sort(((o1, o2) -> {
            int f1 = o1.getName().charAt(0);
            int f2 = o2.getName().charAt(0);
            return f1 - f2;
        }));
    }

    @Override
    public String toString() {
        return "Alphabet";
    }
}