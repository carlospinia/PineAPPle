package com.example.myfirstapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        Fragment fragment = new FragmentThree();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame ,fragment).commit();

        //Floating Action Button
        final FloatingActionButton btnFab = (FloatingActionButton)findViewById(R.id.btnFab);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "¡NO TOQUES! ¿Por qué tocas?", Snackbar.LENGTH_LONG).show();
            }
        });

        NavigationView navView;
        navView = (NavigationView) findViewById(R.id.navview);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem){
                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()){
                            case R.id.menu_seccion_1:
                                fragment = new FragmentOne();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_2:
                                fragment = new FragmentTwo();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_seccion_3:
                                fragment = new FragmentThree();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_opcion_1:
                                Snackbar.make(btnFab, "Pulsada opción 1", Snackbar.LENGTH_LONG).show();
                                break;
                            case R.id.menu_opcion_2:
                                Snackbar.make(btnFab, "Pulsada opción 2", Snackbar.LENGTH_LONG).show();
                                break;
                        }

                        if (fragmentTransaction){
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();
                        return true;
                    }
                }
        );


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
