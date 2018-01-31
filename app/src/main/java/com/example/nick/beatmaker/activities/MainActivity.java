package com.example.nick.beatmaker.activities;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.SystemClock;
import android.preference.CheckBoxPreference;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nick.beatmaker.Metronome;
import com.example.nick.beatmaker.R;
import com.example.nick.beatmaker.RecordingService;
import com.example.nick.beatmaker.SoundPlayer;
import com.example.nick.beatmaker.MySharedPreferences;
import com.example.nick.beatmaker.fragments.PadKitsFragment;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static java.lang.String.valueOf;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    private boolean mStartRecording = true;

    private Menu menu;

    Metronome metronome;
    Button metronomeButton;
    boolean metronomeOn = false;

    TextView bpmText;
    SeekBar bpmSlider;

    ArrayList<GridLayout> padKitLayouts;
    ArrayList<Button> soundPadButtons;
    RelativeLayout metronomeContainer;
    GridLayout trapKitLayout;
    GridLayout standardDrumKitLayout;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted) finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ask for permission to record audio.
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

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

            case R.id.action_recordings:
                Intent u = new Intent(this, RecordedActivity.class);
                startActivity(u);
                return true;

            case R.id.action_record:
                onRecord(mStartRecording);


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


    public void checkPadKitPreference() {

        for (GridLayout padKitLayout : padKitLayouts) {

            padKitLayout.setVisibility(View.GONE);
        }

        if (MySharedPreferences.getPrefStandardDrumKit(this) == true){
            standardDrumKitLayout.setVisibility(GridLayout.VISIBLE);

         }else if (MySharedPreferences.getPrefTrapKit(this) == true) {
            trapKitLayout.setVisibility(GridLayout.VISIBLE);

        } else {
            standardDrumKitLayout.setVisibility(GridLayout.VISIBLE);
        }

    }

    private void onRecord(boolean start){

        Intent intent = new Intent(this, RecordingService.class);

        if (start) {
            // start recording
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_media_stop_dark));
            //mPauseButton.setVisibility(View.VISIBLE);
            mStartRecording = false;
            Toast.makeText(this, R.string.toast_recording_start,Toast.LENGTH_SHORT).show();
            File folder = new File(Environment.getExternalStorageDirectory() + "/BeatMaker");
            if (!folder.exists()) {
                //folder /BeatMaker doesn't exist, create the folder
                folder.mkdir();
            }

            //start RecordingService
            this.startService(intent);
            //keep screen on while recording
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        } else {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_mic_white_36dp));
            mStartRecording = true;
            //stop recording
            this.stopService(intent);
            //allow the screen to turn off again once recording is finished
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    public void addSoundPads(){

        //Standard drum kit
        soundPadButtons.add((Button) findViewById(R.id.standardcrash));
        soundPadButtons.add((Button) findViewById(R.id.standardhihat));
        soundPadButtons.add((Button) findViewById(R.id.standardsnare));
        soundPadButtons.add((Button) findViewById(R.id.standardkick));

        //Trap kit
        soundPadButtons.add((Button) findViewById(R.id.traphihat));
        soundPadButtons.add((Button) findViewById(R.id.trapguncock));
        soundPadButtons.add((Button) findViewById(R.id.trapkick));
        soundPadButtons.add((Button) findViewById(R.id.trapsnare));

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

        bpmText = (TextView) findViewById(R.id.bpmText);
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


}
