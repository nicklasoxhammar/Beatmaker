package com.example.nick.beatmaker.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.nick.beatmaker.R;
import com.example.nick.beatmaker.listeners.MySharedPreferences;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Nick on 2018-01-26.
 */

public class PadKitsFragment extends PreferenceFragment {


    public static CheckBoxPreference standardDrumkitPref;
    public static CheckBoxPreference trapSetPref;

    static ArrayList<CheckBoxPreference> padKits;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.padkits);

        trapSetPref = ((CheckBoxPreference) findPreference("trapSetKey"));
        standardDrumkitPref = ((CheckBoxPreference) findPreference("standardDrumkitKey"));

        padKits = new ArrayList<CheckBoxPreference>();
        padKits.add(trapSetPref);
        padKits.add(standardDrumkitPref);

        Preference.OnPreferenceChangeListener onPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String key = preference.getKey();
                if (key.equals("trapSetKey")) {
                    //Reset other items
                    CheckBoxPreference p = (CheckBoxPreference)findPreference("standardDrumkitKey");
                    p.setChecked(false);
                }
                else if (key.equals("standardDrumkitKey")) {
                    //Reset other items
                    CheckBoxPreference p = (CheckBoxPreference)findPreference("trapSetKey");
                    p.setChecked(false);
                }

                //Force the current focused checkbox to always stay checked when pressed
                //i.e confirms value when newValue is checked (true) and discards newValue
                //when newValue is unchecked (false)

                if(preference == standardDrumkitPref) {
                    MySharedPreferences.setPrefStandardDrumkit(getActivity(), (boolean) newValue);
                    MySharedPreferences.setPrefAnotherSet(getActivity(), false);
                }else if (preference == trapSetPref) {
                    MySharedPreferences.setPrefAnotherSet(getActivity(), (boolean) newValue);
                    MySharedPreferences.setPrefStandardDrumkit(getActivity(), false);
                }

                return true;
            }
        };

        trapSetPref.setOnPreferenceChangeListener(onPreferenceChangeListener);

        standardDrumkitPref.setOnPreferenceChangeListener(onPreferenceChangeListener);




    }


    public static ArrayList<CheckBoxPreference> getPadKitList(){

        return padKits;
    }


}
