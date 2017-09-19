package edu.orangecoastcollege.cs273.jburk.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jamesburk on 9/15/17.
 */

public class LoanSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        TextView paymentView = (TextView) findViewById(R.id.monthlyPaymentTextView);
        TextView reportView = (TextView) findViewById(R.id.loanReportTextView);
        Intent intentFromPurchase = getIntent();

        String payment = intentFromPurchase.getStringExtra("payment");
        paymentView.setText(payment);

        String report = intentFromPurchase.getStringExtra("report");
        reportView.setText(report);
    }

    public void goBack(View v) {
        this.finish();
    }
}