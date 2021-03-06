package com.oxhammar.nick.beatmaker.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.oxhammar.nick.beatmaker.R;
import com.oxhammar.nick.beatmaker.fragments.PadKitsFragment;

/**
 * Created by Nick on 2018-01-25.
 */

public class PadKitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padkits);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Pad Kits");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new PadKitsFragment())
                .commit();



    }




}
