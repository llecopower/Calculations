package com.lunaticaliens.calculations.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaticaliens.calculations.R;
import com.lunaticaliens.calculations.utils.EvaluationClass;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEditText; // input field where the user will input the number
    private TextView operationTextView; // the textview to show expression
    /* all the buttons on the front end*/
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button zeroButton;
    private Button pointButton;
    private Button minusButton;
    private Button clearButton;
    private Button generateButton;
    private Button quitButton;
    private Button isEqualToButton;
    private Button showAllButton;

    private StringBuilder logStringBuilder; // string that will hold the log
    private int totalCounter = 0; // total answers
    private int correctAnswersCounter = 0; // correct answers

    /**
     * This method is called when the activity is created
     * @param savedInstanceState if anyt
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWidgets();
    }

    private void initializeWidgets() {

        logStringBuilder = new StringBuilder();

        inputEditText = findViewById(R.id.inputEditText);
        operationTextView = findViewById(R.id.operationTextView);

        sevenButton = findViewById(R.id.sevenButton);
        sevenButton.setOnClickListener(this);

        eightButton = findViewById(R.id.eightButton);
        eightButton.setOnClickListener(this);

        nineButton = findViewById(R.id.nineButton);
        nineButton.setOnClickListener(this);

        fourButton = findViewById(R.id.fourButton);
        fourButton.setOnClickListener(this);

        fiveButton = findViewById(R.id.fiveButton);
        fiveButton.setOnClickListener(this);

        sixButton = findViewById(R.id.sixButton);
        sixButton.setOnClickListener(this);

        oneButton = findViewById(R.id.oneButton);
        oneButton.setOnClickListener(this);

        twoButton = findViewById(R.id.twoButton);
        twoButton.setOnClickListener(this);

        threeButton = findViewById(R.id.threeButton);
        threeButton.setOnClickListener(this);

        zeroButton = findViewById(R.id.zeroButton);
        zeroButton.setOnClickListener(this);

        pointButton = findViewById(R.id.pointButton);
        pointButton.setOnClickListener(this);

        minusButton = findViewById(R.id.minusButton);
        minusButton.setOnClickListener(this);

        clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);

        generateButton = findViewById(R.id.generateButton);
        generateButton.setOnClickListener(this);

        quitButton = findViewById(R.id.quitButton);
        quitButton.setOnClickListener(this);

        isEqualToButton = findViewById(R.id.isEqualToButton);
        isEqualToButton.setOnClickListener(this);

        showAllButton = findViewById(R.id.showAllButton);
        showAllButton.setOnClickListener(this);
    }

    /**
     * This method is called when any of the component on the front end is clicked
     * any button.
     * @param v the button whihc is clicked
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oneButton: {
                inputEditText.append("1");
                break;
            }
            case R.id.twoButton: {
                inputEditText.append("2");
                break;
            }
            case R.id.threeButton: {
                inputEditText.append("3");
                break;
            }
            case R.id.fourButton: {
                inputEditText.append("4");
                break;
            }
            case R.id.fiveButton: {
                inputEditText.append("5");
                break;
            }
            case R.id.sixButton: {
                inputEditText.append("6");
                break;
            }
            case R.id.sevenButton: {
                inputEditText.append("7");
                break;
            }
            case R.id.eightButton: {
                inputEditText.append("8");
                break;
            }
            case R.id.nineButton: {
                inputEditText.append("9");
                break;
            }
            case R.id.zeroButton: {
                inputEditText.append("0");
                break;
            }
            case R.id.pointButton: {
                inputEditText.append(".");
                break;
            }
            case R.id.minusButton: {
                inputEditText.append("-");
                break;
            }
            case R.id.clearButton: {
                inputEditText.setText("");
                operationTextView.setText("");
                break;
            }
            case R.id.generateButton: {
                EvaluationClass evaluationClass1 = new EvaluationClass(getApplicationContext());
                operationTextView.setText(evaluationClass1.generateExpression());
                break;
            }
            case R.id.quitButton: {
                finish();
                Toast.makeText(this, "Good Bye", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.isEqualToButton: {
                String expression = operationTextView.getText().toString().trim();
                String typedAnswer = inputEditText.getText().toString().trim();


                if (!TextUtils.isEmpty(expression) && !TextUtils.isEmpty(typedAnswer)) {
                    logStringBuilder.append("\n");

                    // make a new object of evaluation class
                    EvaluationClass evaluationClass1 = new EvaluationClass(getApplicationContext());
                    // get the answer from the expression
                    Double getAnswer = evaluationClass1.evaluateExpression(expression);
                    //round the answer to 2 decimal points
                    getAnswer = round(getAnswer, 2);

                    // check if the answer is correct or not
                    if (getAnswer == round(Double.parseDouble(typedAnswer), 2)) {
                        // append to log
                        logStringBuilder.append(expression).append(" = ").append(getAnswer);
                        logStringBuilder.append("\nYour Answer is Correct\n-------------------------------------\n");
                        correctAnswersCounter++;
                    } else {
                        // append to log
                        logStringBuilder.append(expression).append(" = ").append(round(Double.parseDouble(typedAnswer), 2));
                        logStringBuilder.append("\nYour Answer is Wrong!!\n-------------------------------------\n");
                    }
                    totalCounter++;
                } else {
                    Toast.makeText(this, "Please enter an expression", Toast.LENGTH_SHORT).show();
                }
                inputEditText.setText("");
                operationTextView.setText("");
                break;
            }
            case R.id.showAllButton: {
                // pass the log and counters to next activity
                Intent myintent = new Intent(MainActivity.this, ResultActivity.class);
                myintent.putExtra("LOG", logStringBuilder.toString());
                myintent.putExtra("CorrectAnswers", correctAnswersCounter);
                myintent.putExtra("TotalAnswers", totalCounter);
                startActivity(myintent);
                break;
            }
        }
    }

    /**
     * Round double.
     *
     * @param value  the value
     * @param places the places
     * @return the double
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
