package com.fyp.swms;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.sql.Time;
import java.util.Objects;

public class SettingsPreferences {

    private Context context;

    private static final String TANK_PROGRESS = "tnk";
    private static final String MOTOR_STATUS = "mtr";

    public SettingsPreferences(Context context){
        this.context= context;
    }


    public void setTankProgress(int progress) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putInt(TANK_PROGRESS, progress);
        prefEditor.apply();
    }public int getTankProgress() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(TANK_PROGRESS,0);
    }

    public void setMotorStatus(boolean isOn) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putBoolean(MOTOR_STATUS, isOn);
        prefEditor.apply();
    }public boolean getMotorStatus() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(MOTOR_STATUS,false);
    }


    public boolean isUserLoggedIn(){

        return true;
    }

    public void logout(){
//        FirebaseAuth.getInstance().signOut();
    }

}
