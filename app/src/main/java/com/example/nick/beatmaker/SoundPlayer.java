package com.example.nick.beatmaker;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.widget.GridLayout;

import com.example.nick.beatmaker.listeners.MySharedPreferences;

import static android.content.ContentValues.TAG;


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

        //standardhihat has an int value of 1, standardcrash has value of 2, etc..
        sp = new SoundPool(100, AudioManager.STREAM_MUSIC,0);


        if (MySharedPreferences.getPrefStandardDrumkit(context) == true) {

            hihat = sp.load(context, R.raw.standardhihat, 1);
            crash = sp.load(context, R.raw.standardcrash, 1);
            snare = sp.load(context, R.raw.standardsnare, 1);
            kick = sp.load(context, R.raw.standardkick, 1);
        }

        else if (MySharedPreferences.getPrefAnotherSet(context) == true){

            hihat = sp.load(context, R.raw.traphihat, 1);
            crash = sp.load(context, R.raw.trapguncock, 1);
            snare = sp.load(context, R.raw.trapsnare, 1);
            kick = sp.load(context, R.raw.trapkick, 1);
        }



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
