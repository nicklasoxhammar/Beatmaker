package com.example.nick.beatmaker.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.nick.beatmaker.R;
import com.example.nick.beatmaker.MySharedPreferences;

import java.util.ArrayList;

/**
 * Created by Nick on 2018-01-26.
 */

public class PadKitsFragment extends PreferenceFragment {


    public static CheckBoxPreference standardDrumKitPref;
    public static CheckBoxPreference trapKitPref;

    static ArrayList<CheckBoxPreference> padKits;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.padkits);

        trapKitPref = ((CheckBoxPreference) findPreference("trapKit"));
        standardDrumKitPref = ((CheckBoxPreference) findPreference("standardDrumKit"));

        padKits = new ArrayList<CheckBoxPreference>();
        padKits.add(trapKitPref);
        padKits.add(standardDrumKitPref);


        Preference.OnPreferenceChangeListener onPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String key = preference.getKey();

                if (preference == standardDrumKitPref) {
                    MySharedPreferences.setPrefStandardDrumKit(getActivity(), true);

                    MySharedPreferences.setPrefTrapKit(getActivity(), false);
                    //Reset other items
                    for(CheckBoxPreference padKit : padKits) {
                        if (padKit != standardDrumKitPref)
                            padKit.setChecked(false);
                    }

                }

                else  if (preference == trapKitPref) {
                    MySharedPreferences.setPrefTrapKit(getActivity(), true);

                    MySharedPreferences.setPrefStandardDrumKit(getActivity(), false);
                    //Reset other items
                    for (CheckBoxPreference padKit : padKits) {
                        if (padKit != trapKitPref) {
                            padKit.setChecked(false);
                        }
                    }
                }


                return (boolean) newValue;
            }
        };


        for (CheckBoxPreference padKitPref : padKits){

                padKitPref.setOnPreferenceChangeListener(onPreferenceChangeListener);
        }


    }

}
