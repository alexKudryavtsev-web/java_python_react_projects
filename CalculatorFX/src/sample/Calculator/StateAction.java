package sample.Calculator;

public class StateAction extends State{
    @Override
    void clear(Context obj) {
        obj.state = new StateX();
        obj.state.clear(obj);
    }

    @Override
    void digit(Context obj, char key) {
        obj.state = new StateY();
        obj.state.digit(obj, key);
    }

    @Override
    void arifm(Context obj, char key) {
        obj.operation = key;
    }

    @Override
    void equal(Context obj) {
        obj.y = obj.x;
        obj.state = new StateAnswer();
        obj.state.equal(obj);
    }

    @Override
    void back(Context obj) {
        obj.state = new StateX();
        obj.operation = '+';
    }

    @Override
    public String toString() {
        return "StateAction";
    }
}