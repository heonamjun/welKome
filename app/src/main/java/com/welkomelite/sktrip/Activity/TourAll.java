package com.welkomelite.sktrip.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.welkomelite.sktrip.Fragment.Fragment_menu1_first;
import com.welkomelite.sktrip.R;


public class TourAll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_menu2_viewpager);
        Fragment_menu1_first fragment_menu1_first= new Fragment_menu1_first();


        if (null == savedInstanceState) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_menu2_first_cardview, fragment_menu1_first)
                    .commit();
        }
    }
}