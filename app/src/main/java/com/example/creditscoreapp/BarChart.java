package com.example.creditscoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class BarChart extends AppCompatActivity {
    PieChart pieChart;
    TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        // Retrieve credit score from the intent
        String creditScore = String.valueOf(getIntent().getDoubleExtra("creditScore", 0.0));
        String reating = getIntent().getStringExtra("creditRating");
        Log.d("data","data is ===>>>"+creditScore+reating);
        pieChart=(PieChart)findViewById(R.id.piechart);
        textView = findViewById(R.id.creditScoreResult);
        ArrayList<PieEntry> records = new ArrayList<>();
        records.add(new PieEntry(10000,"Good"));
        records.add(new PieEntry(4000,"Average"));
        records.add(new PieEntry(2000,"Poor"));

        PieDataSet dataSet = new PieDataSet(records,"pie chart report");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(20f);

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(true);
        pieChart.setCenterText("Credit Score Anylizer");
        pieChart.animate();

        textView.setText("Credit Score is:= "+creditScore+"\n"+"Credit Rating ="+reating);
    }
}