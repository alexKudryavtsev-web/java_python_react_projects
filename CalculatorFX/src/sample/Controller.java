package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Calculator.Context;
import sample.Calculator.State;

public class Controller {
    @FXML
    public TextField field;
    private Context context = new Context();

    public void addition(ActionEvent event) {
        context.press('+');
        field.setText(field.getText()+" + ");
    }

    public void subtraction(ActionEvent event) {
        context.press('-');
        field.setText(field.getText()+" - ");
    }

    public void multiplication(ActionEvent event) {
        context.press('*');
        field.setText(field.getText()+" * ");
    }

    public void division(ActionEvent event) {
        context.press('/');
        field.setText(field.getText()+" / ");
    }

    public void div(ActionEvent event) {
        context.press('%');
        field.setText(field.getText()+" div ");
    }

    public void clear(ActionEvent event) {
        context.press('C');
        field.setText("");
    }

    public void equals(ActionEvent event) {
        context.press('=');
        System.out.println(context);
        field.setText(context.getX()+"");
    }

    public void add_0(ActionEvent event) {
        context.press('0');
        field.setText(field.getText()+0);
    }

    public void add_7(ActionEvent event) {
        context.press('7');
        field.setText(field.getText() + 7);
    }

    public void add_8(ActionEvent event) {
        context.press('8');
        field.setText(field.getText()+8);
    }

    public void add_9(ActionEvent event) {
        context.press('9');
        field.setText(field.getText()+9);
    }

    public void add_4(ActionEvent event) {
        context.press('4');
        field.setText(field.getText()+4);
    }

    public void add_5(ActionEvent event) {
        context.press('5');
        field.setText(field.getText()+5);
    }

    public void add_6(ActionEvent event) {
        context.press('6');
        field.setText(field.getText()+6);
    }

    public void add_1(ActionEvent event) {
        context.press('1');
        field.setText(field.getText()+1);
    }

    public void add_2(ActionEvent event) {
        context.press('2');
        field.setText(field.getText()+2);
    }

    public void add_3(ActionEvent event) {
        context.press('3');
        field.setText(field.getText()+3);
    }

    public void back(ActionEvent event) {
        context.press('<');
        State thisState = context.getState();
        System.out.println(thisState.getClass().getName());
        if(equalsX(thisState))
            field.clear();
        else if(equalsAction(thisState))
            field.setText(context.getX()+" "+context.getOperation()+" ");
        else if(equalsY(thisState))
            field.setText(context.getX()+" ");
        }

    private boolean equalsX(State s){
        return s.getClass().getName().equals("sample.Calculator.StateX");
    }

    private boolean equalsY(State s){
        return s.getClass().getName().equals("sample.Calculator.StateY");
    }

    private boolean equalsAction(State s){
        return s.getClass().getName().equals("sample.Calculator.StateAction");
    }

    public void power(ActionEvent event) {
        context.press('p');
        field.setText(field.getText()+" в степени ");
    }
}