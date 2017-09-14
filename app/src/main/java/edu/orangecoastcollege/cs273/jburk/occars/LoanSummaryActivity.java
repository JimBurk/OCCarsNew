package edu.orangecoastcollege.cs273.jburk.occars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoanSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_summary);

        // Receive Intent from
        // Populate TextView

        Intent intentFromPurchase = getIntent();
        String report = intentFromPurchase.getStringExtra("loanReport");

        // Fill TextViews with data from report
    }
}
