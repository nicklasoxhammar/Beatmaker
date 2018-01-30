package com.example.nick.beatmaker;

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

    private static String PrefStandardDrumKit = "prefStandardDrumKit";
    private static String PrefTrapKit = "prefTrapKit";


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

    public static void setPrefStandardDrumKit(Context context, boolean isEnabled) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PrefStandardDrumKit, isEnabled);
        editor.apply();
    }

    public static boolean getPrefStandardDrumKit(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PrefStandardDrumKit, false);
    }

    public static void setPrefTrapKit(Context context, boolean isEnabled) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PrefTrapKit, isEnabled);
        editor.apply();
    }

    public static boolean getPrefTrapKit(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PrefTrapKit, false);
    }
}
