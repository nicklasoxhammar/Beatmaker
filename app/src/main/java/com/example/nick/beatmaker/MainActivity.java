package com.example.nick.beatmaker;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SoundPool sp;
    private int sound1;
    private int sound2;
    private int sound3;
    private int sound4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new SoundPool(50, AudioManager.STREAM_MUSIC,0);
        sound1 = sp.load(getApplicationContext(),R.raw.hihat,1);
        sound2 = sp.load(getApplicationContext(),R.raw.crash,1);
        sound3 = sp.load(getApplicationContext(),R.raw.snare,1);
        sound4 = sp.load(getApplicationContext(),R.raw.kick,1);

    }

    public void playSoundHihat (View v) {
        sp.play(sound1,1.0f,1.0f,0,0,1.0f);
    }
    public void playSoundCrash (View v) {
        sp.play(sound2,1.0f,1.0f,0,0,1.0f);

    }
    public void playSoundSnare (View v) {
        sp.play(sound3,1.0f,1.0f,0,0,1.0f);

    }
    public void playSoundKick (View v) {
        sp.play(sound4,1.0f,1.0f,0,0, 1.0f);

    }

    public void startMetronome(View v){

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
