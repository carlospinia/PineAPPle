package com.example.myfirstapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Seccion1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccion1);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.appbarsec1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment fragment;
        fragment = new FragmentThree();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_secc1, fragment).commit();

        Intent intent = getIntent();
        String item_name = intent.getStringExtra(MainActivity.ITEM_NAME);
        getSupportActionBar().setTitle(item_name);
    }
}
