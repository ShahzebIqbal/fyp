package com.fyp.swms;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

public class GraphActivity extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    ArrayList<BarEntry> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        barChart = findViewById(R.id.barChart);

        initData();

        barDataSet = new BarDataSet(data, "Motor Pump Record");
        barData  = new BarData(barDataSet);

        barChart.setData(barData);

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);

    }


    void initData(){
        data = new ArrayList<>();

//        float row = 1;
        for (float row = 1;row<50;row++)
        data.add(new BarEntry(row,(float) (new Random().nextInt(10)+1)/2));
    }

}