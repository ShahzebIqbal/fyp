package com.fyp.swms;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

public class CircularBarActivity extends AppCompatActivity {

    private CircularFillableLoaders circularFillableLoaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_bar);

        circularFillableLoaders = findViewById(R.id.circularFillableLoaders);

//        circularFillableLoaders.setColor(Color.GREEN);

        circularFillableLoaders.setAmplitudeRatio((float) 20 / 1000);
        circularFillableLoaders.setBorderWidth(10 * getResources().getDisplayMetrics().density);
        circularFillableLoaders.setProgress(50);


        // PROGRESS
//        ((SeekBar) findViewById(R.id.seekBarProgress)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                circularFillableLoaders.setProgress(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

        // BORDER
//        ((SeekBar) findViewById(R.id.seekBarBorderWidth)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                circularFillableLoaders.setBorderWidth(progress * getResources().getDisplayMetrics()
//                        .density);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

        // AMPLITUDE
//        ((SeekBar) findViewById(R.id.seekBarAmplitude)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                circularFillableLoaders.setAmplitudeRatio((float) progress / 1000);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//
    }
}