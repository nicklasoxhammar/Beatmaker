package com.oxhammar.nick.beatmaker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
