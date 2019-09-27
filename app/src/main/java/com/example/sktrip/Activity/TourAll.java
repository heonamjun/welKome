package com.example.sktrip.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sktrip.Adapter.DataAdapter;
import com.example.sktrip.Data.GradeTourData;
import com.example.sktrip.Fragment.Fragment_menu1_first;
import com.example.sktrip.R;
import com.example.sktrip.TourApi.LoadTourApi;
import com.example.sktrip.TourApi.Model.DataRES;
import com.example.sktrip.TourApi.OnItemClick;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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