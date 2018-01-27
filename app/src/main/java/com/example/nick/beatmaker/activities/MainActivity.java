package com.example.nick.beatmaker.activities;

import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.nick.beatmaker.Metronome;
import com.example.nick.beatmaker.R;
import com.example.nick.beatmaker.SoundPlayer;
import com.example.nick.beatmaker.fragments.PadKitsFragment;
import com.example.nick.beatmaker.listeners.MySharedPreferences;

import java.lang.reflect.Field;

import static java.lang.String.valueOf;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Metronome metronome;
    Button metronomeButton;
    boolean metronomeOn = false;

    TextView bpmText;
    SeekBar bpmSlider;

    static RelativeLayout metronomeContainer;
    static GridLayout trapSetLayout;
    static GridLayout standardDrumKitLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SoundPlayer soundPlayer = new SoundPlayer(getApplicationContext());

        makeActionOverflowMenuShown();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        metronomeContainer = (RelativeLayout) this.findViewById(R.id.metronomeContainer);

        trapSetLayout = (GridLayout) this.findViewById(R.id.trapSetLayout);
        standardDrumKitLayout = (GridLayout) this.findViewById(R.id.standardDrumKitLayout);

        showOrHideMetronome();

        metronomeButton = (Button) findViewById(R.id.metronomeButton);

        bpmText = (TextView) findViewById(R.id.bpmText);
        bpmSlider = (SeekBar) findViewById(R.id.bpmSlider);
        bpmSlider.setMax(160);
        bpmSlider.setProgress(80);

        checkPadKitPreference();

        bpmSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                updateBpm(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;

            case R.id.action_padKit:
                Intent e = new Intent( this, PadKitsActivity.class);
                startActivity(e);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    public void updateBpm(int progress){

        bpmText.setText("BPM: " + valueOf(progress + 40));

    }

    public void startMetronome(View view){

        if (!metronomeOn) {

            metronomeOn = true;
            metronomeButton.setText("Stop");
            bpmSlider.setEnabled(false);

            metronome = new Metronome((double) bpmSlider.getProgress() + 40);
            metronome.playPublic();

        }else{

            metronomeOn = false;
            metronomeButton.setText("Start");
            bpmSlider.setEnabled(true);

            metronome.stop();
        }
    }

    private void makeActionOverflowMenuShown() {
        //devices with hardware menu button (e.g. Samsung Note) don't show action overflow menu
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
    }

    public void showOrHideMetronome(){

        if(MySharedPreferences.getPrefMetronome(this) == true) {
            metronomeContainer.setVisibility(RelativeLayout.VISIBLE);
        }else{
            metronomeContainer.setVisibility(RelativeLayout.GONE);
        }

    }

    public void playSound(View v){

        SoundPlayer.playPadSound(Integer.parseInt(String.valueOf(v.getTag())));

    }

    public void checkPadKitPreference(){

        if (MySharedPreferences.getPrefStandardDrumkit(this) == true){
            standardDrumKitLayout.setVisibility(GridLayout.VISIBLE);
            //SoundPlayer.standardKit(this);
        }else{
            standardDrumKitLayout.setVisibility(GridLayout.GONE);
        }

        if (MySharedPreferences.getPrefAnotherSet(this) == true){
            trapSetLayout.setVisibility(GridLayout.VISIBLE);
            //SoundPlayer.trapKit(this);
        }else{
            trapSetLayout.setVisibility(GridLayout.GONE);
        }



    }



}
