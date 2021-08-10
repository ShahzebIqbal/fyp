package com.fyp.swms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        YoYo.with(Techniques.ZoomIn).duration(1200).playOn(findViewById(R.id.splash_image));

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent nextActivity = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(nextActivity);
                        finish();
                    }
                },3000);

    }//onCreate


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
