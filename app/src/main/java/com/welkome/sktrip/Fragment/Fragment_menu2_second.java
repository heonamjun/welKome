package com.welkome.sktrip.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.welkome.sktrip.Adapter.DataAdapter;
import com.welkome.sktrip.R;

public class Fragment_menu2_second extends Fragment {
    private RecyclerView recyclerView;
    private DataAdapter adapter;


    public Fragment_menu2_second(){

    }

    public static Fragment_menu2_second newInstance() {
        Fragment_menu2_second fragment = new Fragment_menu2_second();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu2_viewpager, container, false);
        return view;
    }

}
