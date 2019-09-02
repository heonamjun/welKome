package com.example.sktrip.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.sktrip.R;

import com.example.sktrip.TourApiService;



public class Fragment_menu1 extends Fragment {
    private TourApiService service;
    static String con = "";
    public static final String baseUrl = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu1,container,false);


        return view;
    }
}
