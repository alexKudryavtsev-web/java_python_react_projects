package sort.impls;

import object.Person;
import sort.Sort;

import java.util.Collections;
import java.util.List;

public class StackSort implements Sort{
    private boolean has = false;
    @Override
    public void sort(List<Person> list) {
        if(!has) {
            Collections.reverse(list);
            has = true;
        }

    }

    @Override
    public String toString() {
        return "Stack";
    }
}
