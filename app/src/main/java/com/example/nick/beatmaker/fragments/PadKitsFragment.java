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
                if (key.equals("trapKit")) {
                    //Reset other items
                    CheckBoxPreference p = (CheckBoxPreference)findPreference("standardDrumKit");
                    p.setChecked(false);
                }
                else if (key.equals("standardDrumKit")) {
                    //Reset other items
                    CheckBoxPreference p = (CheckBoxPreference)findPreference("trapKit");
                    p.setChecked(false);
                }

                //Force the current focused checkbox to always stay checked when pressed
                //i.e confirms value when newValue is checked (true) and discards newValue
                //when newValue is unchecked (false)

                if(preference == standardDrumKitPref) {
                    MySharedPreferences.setPrefStandardDrumKit(getActivity(), (boolean) newValue);
                    MySharedPreferences.setPrefTrapKit(getActivity(), false);
                }else if (preference == trapKitPref) {
                    MySharedPreferences.setPrefTrapKit(getActivity(), (boolean) newValue);
                    MySharedPreferences.setPrefStandardDrumKit(getActivity(), false);
                }

                return (boolean) newValue;
            }
        };

        trapKitPref.setOnPreferenceChangeListener(onPreferenceChangeListener);

        standardDrumKitPref.setOnPreferenceChangeListener(onPreferenceChangeListener);

    }

    public static ArrayList<CheckBoxPreference> getPadKits() {
        return padKits;
    }
}
