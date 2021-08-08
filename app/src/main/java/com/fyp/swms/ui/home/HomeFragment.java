package com.fyp.swms.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fyp.swms.R;
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    int progress = 10;
    TextView tvFillProgress, tvPumpStatus, tvLastFill, tvTextMotor;
    CircularFillableLoaders circularFillableLoaders;

    boolean isMotorOn =false;
    boolean isNulOn=false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        tvFillProgress = root.findViewById(R.id.tvFillProgress);
        tvPumpStatus = root.findViewById(R.id.tvPumpStatus);
        tvLastFill = root.findViewById(R.id.tvLastFill);
        tvTextMotor = root.findViewById(R.id.tvTextMotor);
        circularFillableLoaders = root.findViewById(R.id.circularFillableLoaders);

        tvFillProgress.setText((progress + "%"));

        circularFillableLoaders.setAmplitudeRatio((float) 20 / 1000);
        circularFillableLoaders.setBorderWidth(10 * getResources().getDisplayMetrics().density);
        circularFillableLoaders.setProgress(100-progress);

        tvPumpStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvPumpStatus.setText(("On"));
                if (progress<100) {
                    if (!isMotorOn) {
                        updateProgress();
                        isMotorOn = true;
                    } else {
                        tvPumpStatus.setText(("Off"));
                        isMotorOn = false;
                    }
                }
            }
        });

        tvTextMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress>0) {
                    if (!isNulOn){
                        reduceProgress();
                        isNulOn=true;
                    }else {
                        isNulOn=false;
                    }

                }


            }
        });

        return root;
    }


    void updateProgress() {
        progress++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvFillProgress.setText((progress + "%"));
                circularFillableLoaders.setProgress(100 - progress);
                if (progress < 100 && isMotorOn ) updateProgress();
                else if (progress==100){
                    tvLastFill.setText(new Date(System.currentTimeMillis()).toString());
                    tvPumpStatus.setText(("Off"));
                    isMotorOn=false;
                }
            }
        },  600);
    }



    void reduceProgress() {
        progress--;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("", "==============================>>>>> " + progress);
                tvFillProgress.setText((progress + "%"));
                circularFillableLoaders.setProgress(100 - progress);
                if (progress > 0 && isNulOn) reduceProgress();
                else if (progress==0){
                    tvLastFill.setText(new Date(System.currentTimeMillis()).toString());
                    tvPumpStatus.setText(("Off"));
                }
            }
        },  600);
    }


    static class MyThread extends AsyncTask<Void, Void, Void>{

        MyThread(int progress){

        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}