package com.example.nick.beatmaker;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SoundPool sp;
    private int hihat;
    private int crash;
    private int snare;
    private int kick;

    Metronome metronome;
    Button metronomeButton;
    boolean metronomeOn = false;

    TextView bpmText;
    SeekBar bpmSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metronomeButton = (Button) findViewById(R.id.metronomeButton);

        sp = new SoundPool(50, AudioManager.STREAM_MUSIC,0);
        hihat = sp.load(getApplicationContext(),R.raw.hihat,1);
        crash = sp.load(getApplicationContext(),R.raw.crash,1);
        snare = sp.load(getApplicationContext(),R.raw.snare,1);
        kick = sp.load(getApplicationContext(),R.raw.kick,1);


        bpmText = (TextView) findViewById(R.id.bpmText);
        bpmSlider = (SeekBar) findViewById(R.id.bpmSlider);
        bpmSlider.setMax(200);
        bpmSlider.setProgress(60);

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

    public void playSoundHihat (View v) {
        sp.play(hihat,1.0f,1.0f,0,0,1.0f);
    }
    public void playSoundCrash (View v) {
        sp.play(crash,1.0f,1.0f,0,0,1.0f);

    }
    public void playSoundSnare (View v) {
        sp.play(snare,1.0f,1.0f,0,0,1.0f);

    }
    public void playSoundKick (View v) {
        sp.play(kick,1.0f,1.0f,0,0, 1.0f);

    }



    public void updateBpm(int progress){

        bpmText.setText("BPM: " + String.valueOf(progress));

    }

    public void startMetronome(View view){

        if (!metronomeOn) {

            metronomeOn = true;
            metronomeButton.setText("Stop");
            bpmSlider.setEnabled(false);

            metronome = new Metronome((double) bpmSlider.getProgress());
            metronome.playPublic();

        }else{

            metronomeOn = false;
            metronomeButton.setText("Start");
            bpmSlider.setEnabled(true);

            metronome.stop();
        }
    }



   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void padClicked(View view){

        int id = view.getId();
        String ourId = "";

        ourId = view.getResources().getResourceEntryName(id);

        int resourceId = getResources().getIdentifier(ourId, "raw", "com.example.nick.beatmaker");

        final MediaPlayer mplayer = MediaPlayer.create(this, resourceId);
        mplayer.start();

        mplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mplayer.release();
            }
        });

    }*/
}
