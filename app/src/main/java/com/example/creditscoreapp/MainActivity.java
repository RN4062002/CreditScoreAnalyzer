package com.example.creditscoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.creditscoreapp.utils.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    private EditText incomeEditText, expensesEditText, debtsEditText, assetsEditText;
    private TextView creditScoreResult;
    Button logout;
    PreferenceManager preferenceManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incomeEditText = findViewById(R.id.incomeEditText);
        expensesEditText = findViewById(R.id.expensesEditText);
        debtsEditText = findViewById(R.id.debtsEditText);
        assetsEditText = findViewById(R.id.assetsEditText);
        creditScoreResult = findViewById(R.id.creditScoreResult);
        logout = findViewById(R.id.logout);
        preferenceManager = new PreferenceManager(this);

        Button calculateButton = findViewById(R.id.btn);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCreditScore();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferenceManager.setBoolean( preferenceManager.is_Login, false);
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void calculateCreditScore() {

        double income = Double.parseDouble(incomeEditText.getText().toString());
        double expenses = Double.parseDouble(expensesEditText.getText().toString());
        double debts = Double.parseDouble(debtsEditText.getText().toString());
        double assets = Double.parseDouble(assetsEditText.getText().toString());

        // Simplified credit score calculation (Replace this with an actual algorithm)
        double score = (income - expenses) - debts + assets;

        String creditRating;

        if (score >= 1000) {
            creditRating = "Good";
        } else if (score >= 500) {
            creditRating = "Average";
        } else {
            creditRating = "Poor";
        }
        Intent intent = new Intent(MainActivity.this, BarChart.class);
        intent.putExtra("creditScore", score);
        intent.putExtra("creditRating", creditRating);
        startActivity(intent);
    }


}