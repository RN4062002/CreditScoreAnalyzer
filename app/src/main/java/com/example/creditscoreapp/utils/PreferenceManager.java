package com.example.creditscoreapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    public static final String is_Login = "is_Login";
    SharedPreferences sharedPreferences;

    public PreferenceManager(Context mcontext){
        this.sharedPreferences = mcontext.getSharedPreferences("login",Context.MODE_PRIVATE);
    }

    public void setBoolean(String key,Boolean value){
        sharedPreferences.edit()
                .putBoolean(key,value)
                .apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key,false);
    }
}
