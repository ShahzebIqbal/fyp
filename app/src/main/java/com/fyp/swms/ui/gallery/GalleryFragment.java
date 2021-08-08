package com.fyp.swms.ui.gallery;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fyp.swms.R;
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


                CircularFillableLoaders circularFillableLoaders = (CircularFillableLoaders)root.findViewById(R.id.circularFillableLoaders);
                // Set Progress
                circularFillableLoaders.setProgress(60);
                // Set Wave and Border Color
                circularFillableLoaders.setColor(Color.RED);
                // Set Border Width
                circularFillableLoaders.setBorderWidth(10 /* * getResources().getDisplayMetrics().density*/);
                // Set Wave Amplitude (between 0.00f and 0.10f)
                circularFillableLoaders.setAmplitudeRatio(0.08f);

                textView.setText(s);
            }
        });
        return root;
    }
}