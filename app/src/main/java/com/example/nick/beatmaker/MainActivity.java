package com.example.nick.beatmaker;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SoundPool sp;
    private int metronome;
    private int hihat;
    private int crash;
    private int snare;
    private int kick;

    boolean metronomeOn = false;
    Handler handler;
    Button metronomeButton;


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
        metronome = sp.load(getApplicationContext(),R.raw.metronome,1);

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

    public void startMetronome(View v){

        if (!metronomeOn) {

            metronomeButton.setText("Stop");

            metronomeOn = true;

            handler = new Handler();
            Runnable run = new Runnable() {
                @Override
                public void run() {

                    sp.play(metronome, 1.0f, 1.0f, 0, 0, 1.0f);

                    handler.postDelayed(this, 1000);

                }
            };

            handler.post(run);

        }else{

            metronomeButton.setText("Start");
            handler.removeCallbacksAndMessages(null);
            metronomeOn = false;

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
