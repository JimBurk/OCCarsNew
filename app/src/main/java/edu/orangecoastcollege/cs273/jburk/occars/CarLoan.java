package edu.orangecoastcollege.cs273.jburk.occars;

/**
 * Created by jimburk on 9/14/17.
 */

public class CarLoan {
    private static double STATE_TAX = 0.08;
    private double mPrice;
    private double mDownPayment;
    private int mTerm;

    private static double APR_3_YEARS = 0.0462;
    private static double APR_4_YEARS = 0.0419;
    private static double APR_5_YEARS = 0.0416;

    public void setPrice(double price) {
        mPrice = price;
    }

    public void setDownPayment(double downPayment) {
        mDownPayment = downPayment;
    }

    public void setLoanTerm(int term) {
        mTerm = term;
    }

    public int getLoanTerm() {
        return mTerm;
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
                interestRate = APR_3_YEARS;
                break;
            case 4:
                interestRate = APR_4_YEARS;
                break;
            case 5:
                interestRate = APR_5_YEARS;
                break;
            default:
                interestRate = 0.10;
                break;
        }

        return calculateBorrowedAmount() * interestRate;
    }

    public double calculateMonthlyPayment() {
        return (calculateBorrowedAmount() + calculateInterestAmount()) / (mTerm * 12.0);
    }

}