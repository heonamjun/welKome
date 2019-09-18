package com.example.sktrip.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.sktrip.Adapter.DataAdapter;
import com.example.sktrip.Data.GradeTourData;
import com.example.sktrip.TourApi.LoadTourApi;
import com.example.sktrip.TourApi.OnItemClick;
import com.example.sktrip.R;
import com.example.sktrip.TourApi.Model.DataRES;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_menu2_third extends Fragment {
    private RecyclerView recyclerView;
    private DataAdapter adapter;

    public static Fragment_menu2_third newInstance() {
        Fragment_menu2_third fragment = new Fragment_menu2_third();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu2_first, container, false);
        return view;
    }
}
