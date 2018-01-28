package com.example.nick.beatmaker;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Nick on 2018-01-13.
 */

public class AudioRecorder {

    MediaRecorder recorder;

    String outputFile = Environment.getExternalStorageDirectory().getAbsolutePath();


    AudioRecorder() throws IOException {

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(outputFile);

    }

    public void startRecording(){

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        Log.i("Try","Exception");
        recorder.start();

    }

    public void stopRecording(){

        recorder.stop();
    }
}
