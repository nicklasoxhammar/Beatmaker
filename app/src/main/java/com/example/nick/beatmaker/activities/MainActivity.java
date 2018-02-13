package com.example.nick.beatmaker.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.example.nick.beatmaker.Metronome;
import com.example.nick.beatmaker.R;
import com.example.nick.beatmaker.SoundPlayer;
import com.example.nick.beatmaker.MySharedPreferences;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static java.lang.String.valueOf;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Menu menu;

    Metronome metronome;
    Button metronomeButton;
    boolean metronomeOn = false;

    SeekBar bpmSlider;

    ArrayList<GridLayout> padKitLayouts;
    ArrayList<Button> soundPadButtons;
    RelativeLayout metronomeContainer;
    GridLayout trapKitLayout;
    GridLayout standardDrumKitLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PreferenceManager.setDefaultValues(this, R.xml.padkits, false);

        //Makes the phone volume buttons work even when no sound is playing.
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        //Set up all the stuff!
        soundPadButtons = new ArrayList<Button>();
        addSoundPads();
        padKitLayouts = new ArrayList<GridLayout>();
        addPadKitLayouts();

        setupMetronome();
        checkPadKitPreference();

        SoundPlayer soundPlayer = new SoundPlayer(getApplicationContext());

        makeActionOverflowMenuShown();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
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

        metronomeButton.setText(valueOf(progress + 40));

    }

    public void startMetronome(View view){

        if (!metronomeOn) {

            metronomeButton.setBackgroundResource(R.drawable.metronome_stop_button);

            metronomeOn = true;
            bpmSlider.setEnabled(false);

            metronome = new Metronome((double) bpmSlider.getProgress() + 40);
            metronome.playPublic();

        }else{

            metronomeOn = false;
            metronomeButton.setBackgroundResource(R.drawable.metronome_start_button);
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


    public void checkPadKitPreference() {

        for (GridLayout padKitLayout : padKitLayouts) {

            padKitLayout.setVisibility(View.GONE);
        }

        if (MySharedPreferences.getPrefStandardDrumKit(this) == true){
            standardDrumKitLayout.setVisibility(GridLayout.VISIBLE);
            Log.d(TAG, "checkPadKitPreference: WHHHHY THIIIS NOOOOOOOOO WORK?!?!?!??!");
            Log.d(TAG, "checkPadKitPreference: WHHHHY THIIIS NOOOOOOOOrawesfrawsfO WORK?!?!?!??!");
            Log.d(TAG, "checkPadKitPreference: WHHHHY THIIIS NOOOOOOOOO 1213r2WORK?!?!?!??!");
            Log.d(TAG, "checkPadKitPreference: WHHHHY THIIIS NOOOO111OOO12131OO WORK?!?!?!??!");

         }else if (MySharedPreferences.getPrefTrapKit(this) == true) {
            trapKitLayout.setVisibility(GridLayout.VISIBLE);

        } else {
            standardDrumKitLayout.setVisibility(GridLayout.VISIBLE);
        }

    }



    public void addSoundPads(){

        //Standard drum kit
        soundPadButtons.add((Button) findViewById(R.id.standardcrash));
        soundPadButtons.add((Button) findViewById(R.id.standardhihat));
        soundPadButtons.add((Button) findViewById(R.id.standardsnare));
        soundPadButtons.add((Button) findViewById(R.id.standardkick));
        soundPadButtons.add((Button) findViewById(R.id.standardride));
        soundPadButtons.add((Button) findViewById(R.id.standardtom1));
        soundPadButtons.add((Button) findViewById(R.id.standardtom2));
        soundPadButtons.add((Button) findViewById(R.id.standardtom3));
        soundPadButtons.add((Button) findViewById(R.id.standardrimshot));

        //Trap kit
        soundPadButtons.add((Button) findViewById(R.id.traphihat));
        soundPadButtons.add((Button) findViewById(R.id.trapguncock));
        soundPadButtons.add((Button) findViewById(R.id.trapkick));
        soundPadButtons.add((Button) findViewById(R.id.trapsnare));
        soundPadButtons.add((Button) findViewById(R.id.trapgunshot));
        soundPadButtons.add((Button) findViewById(R.id.trapambience));
        soundPadButtons.add((Button) findViewById(R.id.traptom1));
        soundPadButtons.add((Button) findViewById(R.id.traptom2));
        soundPadButtons.add((Button) findViewById(R.id.traptom3));

        for (Button soundPad : soundPadButtons){

            soundPad.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){

                        //get the soundplayer to play the sound.
                        SoundPlayer.playPadSound(Integer.parseInt(String.valueOf(view.getTag())));
                    }

                    //These are here to make the button color change work when pressed.
                    if(motionEvent.getAction() == MotionEvent.ACTION_BUTTON_PRESS){
                        return true;
                    }
                    if (motionEvent.getAction() == MotionEvent.ACTION_BUTTON_RELEASE){
                        return true;
                    }

                    return false;
                }
            });


        }

    }

    public void addPadKitLayouts(){

        padKitLayouts.add(trapKitLayout = (GridLayout) this.findViewById(R.id.trapKitLayout));
        padKitLayouts.add(standardDrumKitLayout = (GridLayout) this.findViewById(R.id.standardDrumKitLayout));
    }

    public void setupMetronome(){

        metronomeContainer = (RelativeLayout) this.findViewById(R.id.metronomeContainer);
        metronomeButton = (Button) findViewById(R.id.metronomeButton);

        bpmSlider = (SeekBar) findViewById(R.id.bpmSlider);
        bpmSlider.setMax(160);
        bpmSlider.setProgress(80);

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

        showOrHideMetronome();

    }

    @Override
    public void onDestroy() {

        if (metronomeOn){
            metronome.stop();
        }

        super.onDestroy();
    }


}
