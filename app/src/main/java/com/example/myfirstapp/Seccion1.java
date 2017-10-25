package com.example.myfirstapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Seccion1 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccion1);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.appbarsec1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button buttonMarcador = (Button) findViewById(R.id.addMarcador);
        buttonMarcador.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addMarcador();
            }
        });

        Intent intent = getIntent();
        String item_name = intent.getStringExtra(MainActivity.ITEM_NAME);
        getSupportActionBar().setTitle(item_name);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
    }

    private void addMarcador(){
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(40.3936945, -3.701519)));
    }
}
