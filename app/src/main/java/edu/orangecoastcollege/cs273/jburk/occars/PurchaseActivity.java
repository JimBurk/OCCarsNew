package edu.orangecoastcollege.cs273.jburk.occars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

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

        mPriceEditText = (EditText) findViewById(R.id.car_price);
        mDownPaymentEditText = (EditText) findViewById(R.id.down_Payment);
        mThreeYearRadioButton = (RadioButton) findViewById()
        mFourYearRadioButton = (RadioButton) findViewById()
        mFiveYearRadioButton = (RadioButton) findViewById()
    }

    private void collectCarLoanData() {
        mCarLoan.setPrice(Double.parseDouble(mPriceEditText.getText().toString()));
        mCarLoan.setDownPayment(Double.parseDouble(mDownPaymentEditText.getText().toString()));

        if (mThreeYearRadioButton.isChecked())
            mCarLoan.setTerm(3);
        else if (mFourYearRadioButton.isChecked())
            mCarLoan.setTerm(4);
        else (mFiveYearRadioButton.isChecked())
            mCarLoan.setTerm(5);
    }

    protected void reportSummary(View v) {
        collectCarLoanData();
        String report ="Monthly Payment: $";

        // Intent starts a new activity and can share data with them
        Intent launchLoanReport = new Intent(this, LoanSummaryActivity.class);
        // Put data into the Intent
        launchLoanReport.putExtra("loanReport", report);

        startActivity(Intent intent)


    }
}