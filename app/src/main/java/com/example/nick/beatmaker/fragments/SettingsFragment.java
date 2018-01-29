package com.example.nick.beatmaker.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.nick.beatmaker.R;
import com.example.nick.beatmaker.MySharedPreferences;


/**
 * Created by Nick on 2018-01-24.
 */

public class SettingsFragment extends PreferenceFragment {

    public static CheckBoxPreference metronomePref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        metronomePref = (CheckBoxPreference) findPreference("metronomeKey");
        metronomePref.setChecked(MySharedPreferences.getPrefMetronome(getActivity()));
        metronomePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                MySharedPreferences.setPrefMetronome(getActivity(), (boolean) newValue);
                return true;

            }
        });


    }




}
