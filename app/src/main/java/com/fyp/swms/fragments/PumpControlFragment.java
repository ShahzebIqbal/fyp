package com.fyp.swms.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fyp.swms.R;

public class PumpControlFragment extends Fragment {

    public PumpControlFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pump_control, container, false);
        final ConstraintLayout switchBg = root.findViewById(R.id.switchBg);
        TextView btnSwitchOn = root.findViewById(R.id.btnSwitchOn);
        TextView btnSwitchOff = root.findViewById(R.id.btnSwitchOff);

        btnSwitchOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchBg.setBackgroundResource(R.drawable.switch_off1);
            }
        });

        btnSwitchOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchBg.setBackgroundResource(R.drawable.switch_on1);
            }
        });

        return root;
    }
}