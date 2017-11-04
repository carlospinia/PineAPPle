package com.example.myfirstapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
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

        Button bVibrate = (Button) v.findViewById(R.id.btn_vibrar);
        Button bVibrate_sound = (Button) v.findViewById(R.id.btn_sonar_vibrar);
        Button bSound = (Button) v.findViewById(R.id.btn_sonar);

        final Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.game_over);

        bVibrate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    vibrator.vibrate(800);
                }
                return false;
            }
        });

        bVibrate_sound.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    vibrator.vibrate(800);
                    mp.start();
                }
                return false;
            }
        });

        bSound.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    mp.start();
                }
                return false;
            }
        });

        return inflater.inflate(R.layout.fragment_one, container, false);
    }

}
