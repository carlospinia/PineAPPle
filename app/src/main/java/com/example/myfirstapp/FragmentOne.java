package com.example.myfirstapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentOne extends Fragment {

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one, container, false);

        final Button bVibrate = (Button) v.findViewById(R.id.btn_vibrar);
        final Button bVibrate_sound = (Button) v.findViewById(R.id.btn_sonar_vibrar);
        final Button bSound = (Button) v.findViewById(R.id.btn_sonar);

        final Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        final ToneGenerator tone = new ToneGenerator(AudioManager.STREAM_MUSIC,100);
        //final MediaPlayer mp = MediaPlayer.create(this.getActivity(), R.raw.game_over);

        bVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //long[] pattern = {0, 200, 200, 200};
                //vibrator.vibrate(pattern, -1); //-1 = once; 0 = repeat indefinitely
                vibrator.vibrate(200);
            }
        });

        bSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mp.start();
                tone.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT);
            }
        });

        bVibrate_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                vibrator.vibrate(3000);
               // mp.start();
                tone.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT);
            }
        });

        return v;
    }
}
