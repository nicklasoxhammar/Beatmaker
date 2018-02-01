package com.example.nick.beatmaker;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

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
    static int tom1;
    static int tom2;
    static int tom3;
    static int ride;
    static int rimshot;

    static int[] sounds;


    public SoundPlayer(Context context){

        sp = new SoundPool(200, AudioManager.STREAM_MUSIC,0);


        if (MySharedPreferences.getPrefStandardDrumKit(context) == true) {

            crash = sp.load(context, R.raw.standardcrash, 1);
            rimshot = sp.load(context, R.raw.standardrimshot, 1);
            ride = sp.load(context, R.raw.standardride, 1);
            tom1 = sp.load(context, R.raw.standardtom1, 1);
            tom2 = sp.load(context, R.raw.standardtom2, 1);
            tom3 = sp.load(context, R.raw.standardtom3, 1);
            hihat = sp.load(context, R.raw.standardhihat, 1);
            snare = sp.load(context, R.raw.standardsnare, 1);
            kick = sp.load(context, R.raw.standardkick, 1);

        }

        else if (MySharedPreferences.getPrefTrapKit(context) == true){

            crash = sp.load(context, R.raw.trapgunshot, 1);
            rimshot = sp.load(context, R.raw.trapambience, 1);
            ride = sp.load(context, R.raw.trapguncock, 1);
            tom1 = sp.load(context, R.raw.traptom1, 1);
            tom2 = sp.load(context, R.raw.traptom2, 1);
            tom3 = sp.load(context, R.raw.traptom3, 1);
            hihat = sp.load(context, R.raw.traphihat, 1);
            snare = sp.load(context, R.raw.trapsnare, 1);
            kick = sp.load(context, R.raw.trapkick, 1);
        }

        else {

            crash = sp.load(context, R.raw.standardcrash, 1);
            rimshot = sp.load(context, R.raw.standardride, 1);
            ride = sp.load(context, R.raw.standardride, 1);
            tom1 = sp.load(context, R.raw.standardkick, 1);
            tom2 = sp.load(context, R.raw.standardkick, 1);
            tom3 = sp.load(context, R.raw.standardkick, 1);
            hihat = sp.load(context, R.raw.standardhihat, 1);
            snare = sp.load(context, R.raw.standardsnare, 1);
            kick = sp.load(context, R.raw.standardkick, 1);

        }



        sounds = new int[]{crash, rimshot, ride, tom1, tom2, tom3, hihat, snare, kick};

    }


    public static void playPadSound(int tag){

        for (int i = 0; i < sounds.length; i++){

            if(tag == sounds[i]){

                sp.play(sounds[i],1.0f,1.0f,0,0,1.0f);

            }

        }




    }


}
