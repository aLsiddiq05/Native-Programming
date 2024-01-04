package com.example.mindsharpener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView4, textView5, textView6, textView8;
    private EditText editText;
    private Button button;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3;

    private int level; //Choose difficulty level
    private int points = 0; //Keep track of the score

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //Display the AppBar
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            setSupportActionBar(toolbar);

        //Initialize
        textView4 = findViewById(R.id.textview4);
        textView5 = findViewById(R.id.textview5);
        textView6 = findViewById(R.id.textview6);
        textView8 = findViewById(R.id.textview8);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radiobtn1);
        radioButton2 = findViewById(R.id.radiobtn2);
        radioButton3 = findViewById(R.id.radiobtn3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
                displayQuestion();
            }
        });

        //Update difficulty based on selected radio button
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radiobtn1) {
                    level = 3;
                } else if (checkedId == R.id.radiobtn2) {
                    level = 5;
                } else if (checkedId == R.id.radiobtn3) {
                    level = 7;
                }

                //display new question
                displayQuestion();
            }
        });
    }

    //Generate new question based on difficulty
    private void displayQuestion() {
        Random random = new Random();

        int firstOperand;
        int secondOperand;
        int operator;

        switch (level) {
            case 3:
                firstOperand = random.nextInt(10);
                secondOperand = random.nextInt(10);
                operator = random.nextInt(4);
                break;
            case 5:
                firstOperand = random.nextInt(100);
                secondOperand = random.nextInt(10);
                operator = random.nextInt(4);
                break;
            case 7:
                firstOperand = random.nextInt(1000);
                secondOperand = random.nextInt(10);
                operator = random.nextInt(4);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + level);
        }

        textView4.setText(String.valueOf(firstOperand));
        textView5.setText(getOperatorSymbol(operator));
        textView6.setText(String.valueOf(secondOperand));

        // Clear previous answer
        editText.setText("");
    }

    //Checks answer, update score, and display new question.
    private void checkAnswer() {
        int userAnswer = Integer.parseInt(editText.getText().toString());
        int firstNumber = Integer.parseInt(textView4.getText().toString());
        int secondNumber = Integer.parseInt(textView6.getText().toString());
        int operator = getOperatorIndex(textView5.getText().toString());

        int correctAnswer = calculateAnswer(firstNumber, secondNumber, operator);

        if (userAnswer == correctAnswer) {
            points++;
        } else {
            points--;
        }
        //Display updated score
        textView8.setText(String.valueOf(points));
    }

    //Calculate answer based on operands and operator
    private int calculateAnswer(int firstOperand, int secondOperand, int operator) {
        switch (operator) {
            case 0:
                return firstOperand + secondOperand;
            case 1:
                return firstOperand - secondOperand;
            case 2:
                return firstOperand * secondOperand;
            case 3:
                // Avoid division by zero
                if (secondOperand != 0) {
                    return firstOperand / secondOperand;
                } else {
                    // Handle division by zero case
                    return 0;
                }
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    //Retrieve operator symbol based on index
    private String getOperatorSymbol(int operator) {
        switch (operator) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    //Retrieve operator index based on symbol
    private int getOperatorIndex(String symbol) {
        switch (symbol) {
            case "+":
                return 0;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 3;
            default:
                throw new IllegalArgumentException("Invalid operator symbol: " + symbol);
        }
    }
}
