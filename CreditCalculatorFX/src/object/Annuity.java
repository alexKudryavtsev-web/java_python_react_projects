package object;

import java.util.Calendar;

public class Annuity extends CreditCalculator {

    public Annuity(int count_month, int sum, float precent, Calendar start) {
        super(count_month, sum, precent, start);
    }

    @Override
    public double monthlyPayment() {
        double i = precent / 12 / 100;
        System.out.println(i);
        double k = (
                i * Math.pow(1 +i, count_month)) /
                (Math.pow(1 + i, count_month) - 1
                );
        return k * sum;
    }

    @Override
    public String toString() {
        return "Annuity{" +
                "count_month=" + count_month +
                "sum =" + sum +
                "precent =" + precent +
                '}';
    }
}
