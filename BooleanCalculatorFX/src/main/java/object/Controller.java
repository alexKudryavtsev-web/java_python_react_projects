package object;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.regex.*;

public class Controller {
    @FXML
    public TextField text;

    @FXML public void calc(ActionEvent event) {
        String expr = text.getText();
        expr = expr.replace('0', 'F');
        expr = expr.replace('1', 'T');
        expr = expr.replace("¬", "F != ");

        text.setText(TreeBooleanEvaluator.calc(expr));
    }

    //Add total
    private void addOperand(boolean val){
        String newChar = val ? "1" : "0";
        String str = text.getText();

        if (str.length() == 0) {
            text.setText(newChar);
            return;
        }
        char last = str.charAt(str.length() - 1);
        if(last == '0' || last == '1'){
            text.setText(set(str, str.length() - 1, newChar));
        }else {
            text.setText(str + newChar);
        }
    }

    private void addOperation(char operation){
        String str = text.getText();
        if (str.length() == 0) {
            if(operation == '¬'){
                text.setText("¬");
            }
            return;
        }
        char last = str.charAt(str.length() - 1);
        if (operation == '¬') {
            if (last != '0' && last != '1' && last != ')') {
                text.setText(str +"¬");
            }
            return;
        }
        if(last == '0' || last == '1' || last == ')'){
            text.setText(str + operation);
        }
    }

    private void addBracket(boolean isRight){
        String str = text.getText();
        if(str.length() == 0){
            if(isRight)
                text.setText("(");
            return;
        }

        char last = str.charAt(str.length() - 1);
        if (isRight) {
            if ((last != '0' && last != '1' && last != ')'))
                text.setText(text.getText() + "(");
        }else {
            if(last == '0' || last == '1')
                text.setText(text.getText() + ")");
            if(last == ')'){
                int r = count(str, '(');
                int l = count(str, ')');

                if (r > l)
                    text.setText(text.getText() + ")");
            }
        }
    }

    //Innner methods
    private String set(String str, int index, String newChar){
        return new StringBuilder(str).deleteCharAt(index).insert(index, newChar).toString();
    }

    private int count(String str, char find){
        char newFind = '-';
        Matcher m = Pattern.compile("(?=("+newFind+"))").
                matcher(str.replace(find, newFind));
        int count = 0;
        while (m.find()) count++;
        return count;
    }

    //Add concret
    @FXML public void addImplic(ActionEvent event) {
        addOperation('⇒');
    }

    @FXML public void addXor(ActionEvent event) {
        addOperation('^');
    }

    @FXML public void addNot(ActionEvent event) {
        addOperation('¬');
    }

    @FXML public void addDisjunction(ActionEvent event) {
        addOperation('∨');
    }

    @FXML public void addConjunction(ActionEvent event) {
        addOperation('∧');
    }

    @FXML public void addTrue(ActionEvent event) {
        addOperand(true);
    }

    @FXML public void addFalse(ActionEvent event) {
        addOperand(false);
    }

    @FXML public void clear(ActionEvent event){
        text.clear();
    }

    @FXML public void addEquiv(ActionEvent event) {
        addOperation('≡');
    }

    @FXML public void addLeftBracket(ActionEvent event) {
        addBracket(false);
    }

    @FXML public void addRightBracket(ActionEvent event) {
        addBracket(true);
    }
}