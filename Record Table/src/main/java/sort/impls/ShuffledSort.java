package sort.impls;

import object.Person;
import sort.Sort;

import java.util.Collections;
import java.util.List;

public class ShuffledSort implements Sort{
    @Override
    public void sort(List<Person> list) {
        Collections.shuffle(list);
    }

    @Override
    public String toString() {
        return "Shuffled";
    }
}