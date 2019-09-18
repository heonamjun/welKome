package com.example.sktrip.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sktrip.R;
import com.example.sktrip.TourApi.LoadTourApi;
import com.example.sktrip.TourApi.Model.DataRES;
import com.example.sktrip.TourApi.TourApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_menu4 extends Fragment {
    private TourApiService service;
    public static final String baseUrl = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/"; //국문 API 서비스

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu4, container, false);

        Call<DataRES> client = LoadTourApi.getInstance().getService().getdetailCommon();
        client.enqueue(new Callback<DataRES>() {
            @Override
            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
                Log.d("menu4", response.body().getResponse().getBody().getItems().getItem().get(0).getOverview());
            }

            @Override
            public void onFailure(Call<DataRES> call, Throwable t) {
                Log.d("error", "연결 안됨!");
                Log.d("error", t.getMessage());
                Log.d("error", t.toString());
            }
        });


        return view;
    }
}

