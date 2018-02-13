package com.example.nick.beatmaker;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.example.nick.beatmaker.activities.MainActivity;

import java.util.ArrayList;

/**
 * Created by Nick on 2018-01-24.
 */

public class MySharedPreferences {

    private static String PrefMetronome = "prefMetronome";

    private static String PrefTrapKit = "prefTrapKit";
    private static String PrefStandardDrumKit = "prefStandardDrumKit";

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


}
