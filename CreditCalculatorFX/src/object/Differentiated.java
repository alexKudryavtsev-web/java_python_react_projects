package object;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Differentiated extends CreditCalculator {

    public Differentiated(int count_month, int sum, float precent, Calendar start) {
        super(count_month, sum, precent, start);
    }

    @Override
    public double monthlyPayment() {
        Map<Integer, Double> map = mapAmountPayment();
        System.out.println(map);
        double sum = 0;
        for (Double d : map.values()) {
            sum += d;
        }
        return sum / map.size();
    }

    public Map<Integer, Double> mapAmountPayment() {
        Map<Integer, Double> res = new HashMap<>();
        final double diffPaymentPart = sum / count_month;
        double payBal = (int) sum;
        double varPart;

        for (int i = 1; i <= count_month; i++) {
            varPart = payBal * precent / (100 * 12);
            res.put(i, diffPaymentPart + varPart);
            payBal -= diffPaymentPart;
        }
        return res;
    }
}
