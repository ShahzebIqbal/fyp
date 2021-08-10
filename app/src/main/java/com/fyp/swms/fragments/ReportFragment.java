package com.fyp.swms.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.fyp.swms.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

public class ReportFragment extends Fragment {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    ArrayList<BarEntry> data;


    public ReportFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report, container, false);

        barChart = view.findViewById(R.id.barChart);

        initData();

        barDataSet = new BarDataSet(data, "Motor Pump Usage Report");
        barData  = new BarData(barDataSet);

        barChart.setData(barData);

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);



        return view;
    }

    void initData(){
        data = new ArrayList<>();
        for (float row = 1;row<50;row++)
            data.add(new BarEntry(row,(float) (new Random().nextInt(10)+1)/2));
    }

}