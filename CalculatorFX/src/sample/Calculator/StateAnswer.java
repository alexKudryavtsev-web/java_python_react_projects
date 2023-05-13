package sample.Calculator;

public class StateAnswer extends State{
    @Override
    void clear(Context obj) {
        obj.state = new StateX();
        obj.state.clear(obj);
    }

    @Override
    void digit(Context obj, char key) {
        obj.state = new StateX();
        obj.state.digit(obj, key);
    }

    @Override
    void arifm(Context obj, char key) {
        obj.state = new StateAction();
        obj.state.arifm(obj, key);
    }

    @Override
    void equal(Context obj) {
        int answer = 0;
        switch (obj.operation){
            case '-':
                answer = obj.x - obj.y; break;
            case '+':
                answer = obj.x + obj.y; break;
            case '/':
                answer = obj.x / obj.y; break;
            case '*':
                answer = obj.x * obj.y; break;
            case '%':
                answer = obj.x % obj.y; break;
            case 'p':
                answer = (int) Math.pow(obj.x, obj.y);
        }

        obj.x = answer;
        System.out.println(answer);
    }

    @Override
    void back(Context obj) {
        obj.state = new StateY();
        obj.state.back(obj);
    }

    @Override
    public String toString() {
        return "StateAnswer";
    }
}