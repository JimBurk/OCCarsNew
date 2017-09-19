package edu.orangecoastcollege.cs273.jburk.occars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.text.DecimalFormat;

public class PurchaseActivity extends AppCompatActivity {
    private EditText mPriceEditText;
    private EditText mDownPaymentEditText;
    private RadioButton mThreeYearRadioButton;
    private RadioButton mFourYearRadioButton;
    private RadioButton mFiveYearRadioButton;

    private CarLoan mCarLoan = new CarLoan();

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

    public void reportSummary(View v) {
        collectCarLoanData();

        double payment = mCarLoan.calculateMonthlyPayment();
        double taxes = mCarLoan.taxAmount();
        double cost = mCarLoan.totalAmount();
        double borrow = mCarLoan.calculateBorrowedAmount();
        double interest = mCarLoan.calculateInterestAmount();

        String paymentString = " Monthly Payment:\t\t$" + payment;

        String priceString = "Car Sales Price:\t\t\t\t$ " + Double.parseDouble(mPriceEditText.getText().toString());
        String downString = "\nDown Payment:\t\t\t\t$ " + Double.parseDouble(mDownPaymentEditText.getText().toString());
        String taxString = "\nSalesTax Amount:\t\t$   " + taxes;
        String costString = "\nYour Total Cost:\t\t\t\t$ " + cost;
        String borrowedString = "\nBorrowed Amount:\t\t$ " + borrow;
        String interestString = "\nInterest Amount:\t\t\t$   " + interest;
        String termString = "\n\nLoan Term is " + mCarLoan.getLoanTerm() + " years.";
        String noteString = "\n\nNOTE:\n\n1. Loan information is made available by OC Cars.\n\n2. A sales tax rate of 8% is required in Costa Mesa.";

        String reportString =  priceString + downString + taxString + costString + borrowedString + interestString + termString + noteString;

        // Intent starts a new activity and can share data with them
        Intent launchLoanReport = new Intent(this, LoanSummaryActivity.class);
        // Put data into the Intent

        launchLoanReport.putExtra("payment", paymentString);

        launchLoanReport.putExtra("report", reportString);

        startActivity(launchLoanReport);
    }
}
