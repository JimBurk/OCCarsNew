package edu.orangecoastcollege.cs273.jburk.occars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.text.DecimalFormat;

/***
 * Main Activity for the OC Cars application. The user enters a selling price, the down payment,
 * and the loan term. The application then derives the interest, payment and several values, and
 * presents it to the user.
 *
 * Authored by Jim Burk
 *
 * September 19, 2017
 */

public class PurchaseActivity extends AppCompatActivity {
    private EditText mPriceEditText;
    private EditText mDownPaymentEditText;
    private RadioButton mThreeYearRadioButton;
    private RadioButton mFourYearRadioButton;
    private RadioButton mFiveYearRadioButton;

    private CarLoan mCarLoan = new CarLoan();

    public final DecimalFormat df = new DecimalFormat("$##,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        mPriceEditText = (EditText) findViewById(R.id.car_priceEditText);
        mDownPaymentEditText = (EditText) findViewById(R.id.down_paymentEditText);
        mThreeYearRadioButton = (RadioButton) findViewById(R.id.three_yearRadioButton);
        mFourYearRadioButton = (RadioButton) findViewById(R.id.four_yearRadioButton);
        mFiveYearRadioButton = (RadioButton) findViewById(R.id.five_yearRadioButton);
    }

    private void collectCarLoanData() {
        double price = Double.parseDouble(mPriceEditText.getText().toString());
        mCarLoan.setPrice(price);
        double down = Double.parseDouble(mDownPaymentEditText.getText().toString());
        mCarLoan.setDownPayment(down);

        int term = 5;
        if (mThreeYearRadioButton.isChecked())
            term = 3;
        else if (mFourYearRadioButton.isChecked())
            term = 4;

        mCarLoan.setLoanTerm(term);
    }

    /***
     * The reportSummary method collects the car loan data from the Purchase Activity view,
     * derives the loan information, and passes it to the loan summary view.
     *
     */

    public void reportSummary(View v) {
        collectCarLoanData();

        double payment = mCarLoan.calculateMonthlyPayment();
        double taxes = mCarLoan.taxAmount();
        double cost = mCarLoan.totalAmount();
        double borrow = mCarLoan.calculateBorrowedAmount();
        double interest = mCarLoan.calculateInterestAmount();

        String paymentString = " Monthly Payment:\t\t" + df.format(payment);

        String priceString = "Car Sales Price:\t\t\t\t" + df.format(Double.parseDouble(mPriceEditText.getText().toString()));
        String downString = "\nDown Payment:\t\t\t\t" + df.format(Double.parseDouble(mDownPaymentEditText.getText().toString()));
        String taxString = "\nSalesTax Amount:\t\t  " + df.format(taxes);
        String costString = "\nYour Total Cost:\t\t\t\t" + df.format(cost);
        String borrowedString = "\nBorrowed Amount:\t\t" + df.format(borrow);
        String interestString = "\nInterest Amount:\t\t\t  " + df.format(interest);
        String termString = "\n\nLoan Term is " + mCarLoan.getLoanTerm() + " years.";
        String noteString = "\n\nNOTE:\n\n1. Loan information is made available by OC Cars.\n\n2. A sales tax rate of 8% is required in Costa Mesa.";

        String reportString =  priceString + downString + taxString + costString + borrowedString + interestString + termString + noteString;

        // Intent starts a new activity and can share data with it
        Intent launchLoanReport = new Intent(this, LoanSummaryActivity.class);

        // Put data into the Intent
        launchLoanReport.putExtra("payment", paymentString);
        launchLoanReport.putExtra("report", reportString);

        startActivity(launchLoanReport);
    }
}
