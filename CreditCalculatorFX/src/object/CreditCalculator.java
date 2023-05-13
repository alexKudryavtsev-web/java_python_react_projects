package object;

import java.util.Calendar;

public abstract class CreditCalculator {
    double count_month, sum, precent;
    private Calendar start;
    public CreditCalculator(int count_month, int sum, float precent, Calendar start){
        this.count_month = count_month;
        this.sum = sum;
        this.precent = precent;
        this.start = start;
    }

    public abstract double monthlyPayment();

    public Calendar endPayout(){
        Calendar tmp = start;
        tmp.add(Calendar.MONTH, (int) count_month);
        return tmp;
    }

    public double loanOverpayment(){
        return count_month * monthlyPayment() - sum;
    }

    public double totalAmount(){
        return sum + loanOverpayment();
    }

    public double overpayPercent(){
        return  loanOverpayment() / sum * 100;
    }
}