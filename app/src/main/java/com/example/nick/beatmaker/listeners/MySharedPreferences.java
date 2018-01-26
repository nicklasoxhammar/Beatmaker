package com.example.nick.beatmaker.listeners;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by Nick on 2018-01-24.
 */

public class MySharedPreferences {

    private static String PrefMetronome = "prefMetronome";

    private static String PrefStandardDrumkit = "prefStandardDrumkit";
    private static String PrefAnotherSet = "prefAnotherSet";


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

    public static void setPrefStandardDrumkit(Context context, boolean isEnabled) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PrefStandardDrumkit, isEnabled);
        editor.apply();
    }

    public static boolean getPrefStandardDrumkit(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PrefStandardDrumkit, false);
    }

    public static void setPrefAnotherSet(Context context, boolean isEnabled) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PrefAnotherSet, isEnabled);
        editor.apply();
    }

    public static boolean getPrefAnotherSet(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PrefAnotherSet, false);
    }
}
