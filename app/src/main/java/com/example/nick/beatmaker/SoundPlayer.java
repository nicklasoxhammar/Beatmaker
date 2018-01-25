package com.example.nick.beatmaker;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;


/**
 * Created by Nick on 2018-01-25.
 */

public class SoundPlayer {

    static SoundPool sp;
    static int hihat;
    static int crash;
    static int snare;
    static int kick;

    static int[] sounds;


    public SoundPlayer(Context context){

        //hihat has an int value of 1, crash has value of 2, etc..
        sp = new SoundPool(50, AudioManager.STREAM_MUSIC,0);
        hihat = sp.load(context,R.raw.hihat,1);
        crash = sp.load(context,R.raw.crash,1);
        snare = sp.load(context,R.raw.snare,1);
        kick = sp.load(context,R.raw.kick,1);

        sounds = new int[]{hihat, crash, snare, kick};

    }


    public static void playPadSound(int tag){

        for (int i = 0; i < sounds.length; i++){

            if(tag == sounds[i]){

                sp.play(sounds[i],1.0f,1.0f,0,0,1.0f);

            }

        }




    }
}
