package sample.Calculator;

public class StateX extends State{
    private boolean temping = false;

    @Override
    void clear(Context obj) {
        obj.x = 0;
        obj.y = 0;
        obj.operation = '+';
    }

    @Override
    void digit(Context obj, char key) {
        if(!temping){
            obj.x = 0;
            temping = true;
        }
        obj.x = obj.x * 10 + Character.getNumericValue(key);
    }

    @Override
    void arifm(Context obj, char key) {
        obj.state = new StateAction();
        obj.state.arifm(obj, key);
    }

    @Override
    void equal(Context obj) {
        obj.state = new StateAnswer();
        obj.state.equal(obj);
    }

    @Override
    void back(Context obj) {
        obj.state.clear(obj);
    }

    @Override
    public String toString() {
        return "StateX";
    }
}