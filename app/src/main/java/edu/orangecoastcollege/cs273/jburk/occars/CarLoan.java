package edu.orangecoastcollege.cs273.jburk.occars;

/**
 * Created by jimburk on 9/14/17.
 */

public class CarLoan {
    private static double STATE_TAX = 0.08;
    private double mPrice;
    private double mDownPayment;
    private int mTerm;

    public void setPrice(double price) {
        mPrice = price;
    }

    public void setDownPayment(double downPayment) {
        mDownPayment = downPayment;
    }

    public void setLoanTerm(int term) {
        mTerm = term;
    }

    public double taxAmount() {
        return mPrice * STATE_TAX;
    }

    public double totalAmount() {
        return mPrice + taxAmount();
    }

    public double calculateBorrowedAmount() {
        return totalAmount() - mDownPayment;
    }

    public double calculateInterestAmount() {
        double interestRate;
        // 3 yr = 4.62
        // 4 yr = 4.19
        // 5 yr = 4.16

        switch (mTerm) {
            case 3:
                interestRate = 0.0462;
                break;
            case 4:
                interestRate = 0.0419;
                break;
            case 5:
                interestRate = 0.0416;
                break;
            default:
                interestRate = 0.10;
                break;
        }

        return calculateBorrowedAmount() * interestRate;
    }

    public double calculateMonthlyPayment() {
        return (calculateBorrowedAmount() + calculateInterestAmount()) / (mTerm * 12);
    }

}
