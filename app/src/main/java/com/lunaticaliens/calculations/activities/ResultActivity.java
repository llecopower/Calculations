package com.lunaticaliens.calculations.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lunaticaliens.calculations.R;

/**
 * The type Result activity.
 */
public class ResultActivity extends AppCompatActivity {

    private TextView resultTextView; // textview to set the log of the results
    private Button goBackButton; // button that lets user to go to main screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initializeWidgets(); // initialize the widgets on the front end
        String log = getIntent().getStringExtra("LOG"); // get the log from the main activity using intent
        int correctAnswers = getIntent().getIntExtra("CorrectAnswers", 0); // get the correct number of answers
        int totalAnswers = getIntent().getIntExtra("TotalAnswers", 0); // get the total number of answers
        resultTextView.setText(log); // set the text to textview

        int correctPercentage = findPercentage(correctAnswers, totalAnswers); // find the percentage of the correct answers
        int wrongPercentage = findPercentage(totalAnswers - correctAnswers, totalAnswers); // find the percentage of the wrong answers

        resultTextView.append("\n\n\n\n" + correctPercentage + "% Correct Answer"); // set correct percentage
        resultTextView.append("\n" + wrongPercentage + "% Wrong Answer"); // set wrong percentage

    }

    /**
     * This method initializes the widgets given on the front end
     */
    private void initializeWidgets() {
        resultTextView = findViewById(R.id.resultTextView);

        goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * If the physical back button on the device is clicked
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * Find the percentage
     * @param count the number we want to find percentage for
     * @param total total from which percentage should be found
     * @return returns the percentage
     */
    private int findPercentage(int count, int total) {
        return (count * 100) / total;
    }
}
