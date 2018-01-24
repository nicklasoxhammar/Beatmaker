package com.example.nick.beatmaker.listeners;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Nick on 2018-01-24.
 */

public class MySharedPreferences {

    private static String PrefMetronome = "prefMetronome";

    public static void setPrefMetronome(Context context, boolean isEnabled) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PrefMetronome, isEnabled);
        editor.apply();
    }

    public static boolean getPrefMetronome(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PrefMetronome, false);
    }
}
