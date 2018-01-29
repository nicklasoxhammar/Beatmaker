package com.example.nick.beatmaker.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.example.nick.beatmaker.R;
import com.example.nick.beatmaker.fragments.FileViewerFragment;

/**
 * Created by Nick on 2018-01-29.
 */

public class RecordedActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_file_viewer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Settings");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        Log.d("tag", "onCreate: reach?");

        /*getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new FileViewerFragment())
                .commit();*/

        FileViewerFragment.newInstance(1);

    }



}
