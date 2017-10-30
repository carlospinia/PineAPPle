package com.example.myfirstapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class Seccion1 extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_REQUEST_CODE = 1;
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
        buttonMarcador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMarcador();
            }
        });

        Button buttonLatLong = (Button) findViewById(R.id.infoUbicacion);
        buttonLatLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLatLong();
            }
        });

        Intent intent = getIntent();
        String item_name = intent.getStringExtra(MainActivity.ITEM_NAME);
        getSupportActionBar().setTitle(item_name);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        // Controles UI
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mapa.setMyLocationEnabled(true);
            mapa.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            // Solicitar permiso
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
        }

        mapa.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.length > 0 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mapa.setMyLocationEnabled(true);
                    mapa.getUiSettings().setMyLocationButtonEnabled(true);
                }
            } else {
                Toast.makeText(this, "Error de permisos", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showLatLong() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria mCriteria = new Criteria();
        String bestProvider = String.valueOf(manager.getBestProvider(mCriteria, false));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location mLocation = manager.getLastKnownLocation(bestProvider);
            String lat_long = "Lat: " + mLocation.getLatitude() + ", Long: " + mLocation.getLongitude();
            Toast.makeText(this, lat_long, Toast.LENGTH_LONG).show();
        }
    }

    private void addMarcador(){
        LatLng madrid = new LatLng(40.3936945, -3.701519);
        mapa.addMarker(new MarkerOptions().position(madrid));
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 5));
        mapa.animateCamera(CameraUpdateFactory.zoomBy(10), 2000, null);
    }
}
