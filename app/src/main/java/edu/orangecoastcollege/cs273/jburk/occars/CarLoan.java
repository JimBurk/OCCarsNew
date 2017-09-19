package edu.orangecoastcollege.cs273.jburk.occars;

/**
 * The Car Loan class is the model in the MVC method. It provides the logic for the application.
 */

public class CarLoan {
    private static double STATE_TAX = 0.08;
    private double mPrice;
    private double mDownPayment;
    private int mTerm;

    private static double APR_3_YEARS = 0.0462;
    private static double APR_4_YEARS = 0.0419;
    private static double APR_5_YEARS = 0.0416;

    /***
     * The three values requird from the user are the price, the down payment and
     * the loan term. These three "setters" enter the data into the class variables.
     */

    public void setPrice(double price) {
        mPrice = price;
    }

    public void setDownPayment(double downPayment) {
        mDownPayment = downPayment;
    }

    public void setLoanTerm(int term) {
        mTerm = term;
    }

    /***
     *The values of the loan term can be obtained from the class. This avoids checking
     * the radio buttons twice.
     */

    public int getLoanTerm() {
        return mTerm;
    }

    /***
     *The tax amoungt, total amount of the sale, the borrowed amount, the interest amount and
     * the payment are made available to the controller with these "getters" and the calculate
     * Monthly Payment method.
     */

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