package sample.Calculator;

public class Context {
    int x;
    int y;
    char operation;
    State state;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public State getState() {
        return state;
    }

    public char getOperation() {
        return operation;
    }

    public Context(){
        state = new StateX();
        state.clear(this);
    }

    public void press (char key){
        switch (key){
            case 'c':
            case 'C': state.clear(this); break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': state.digit(this, key); break;
            case '-':
            case '+':
            case '/':
            case '%':
            case 'p':
            case '*': state.arifm(this, key); break;
            case '=': state.equal(this); break;
            case '<': state.back(this);
        }
    }

    @Override
    public String toString(){
       return "x = "+x+
               " y = "+y+
               " op = "+operation+
               " state = "+state.getClass().getName();
    }
}
