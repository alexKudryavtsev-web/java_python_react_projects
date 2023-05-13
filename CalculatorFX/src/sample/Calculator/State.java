package sample.Calculator;

public abstract class State {
    abstract void clear(Context obj);
    abstract void digit(Context obj, char key);
    abstract void arifm(Context obj, char key);
    abstract void equal(Context obj);
    abstract void back(Context obj);
}
