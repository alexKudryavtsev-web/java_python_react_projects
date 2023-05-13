package sample.Calculator;

public class StateY extends State{
    boolean temping = false;
    @Override
    void clear(Context obj) {
        obj.state = new StateX();
        obj.state.clear(obj);
    }

    @Override
    void digit(Context obj, char key) {
        if(!temping){
            obj.y = 0;
            temping = true;
        }
        obj.y = obj.y * 10 + Character.getNumericValue(key);
    }

    @Override
    void arifm(Context obj, char key) {
        equal(obj);
        obj.state.arifm(obj, key);
    }

    @Override
    void equal(Context obj) {
        obj.state = new StateAnswer();
        obj.state.equal(obj);
    }

    @Override
    void back(Context obj) {
        obj.state = new StateAction();
        obj.y = 0;
    }

    @Override
    public String toString() {
        return "StateY";
    }
}