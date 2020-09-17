package edu.sjsu.android.mortgagecalculator;

public class MortgageCalc {

    public static String calcMortgage(double principal, double interest, int term, double taxes) {
        double payment = 0;
        double interestRateInDecimal =  (interest / 1200);
        if (interest > 0) {
            payment = (principal * (interestRateInDecimal / (1 - (Math.pow(1 + interestRateInDecimal, -term)))));
            payment = payment + taxes;
        }
        if(interest == 0) {
            payment = (principal / term) + taxes;
        }
        String paymentRounded = String.format("%.2f", payment);
        return paymentRounded;
    }
}
