package com.example.sktrip.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.sktrip.Adapter.DataAdapter;
import com.example.sktrip.DTO.Testdata;
import com.example.sktrip.R;
import com.example.sktrip.TourApiService;
import com.example.sktrip.model.DataRES;



import java.util.ArrayList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_menu2_first extends Fragment {
    public static final String PAGE_TITLE = "관광";

    // Store instance variables
    private String title;
    private int page;

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Testdata> list = new ArrayList<>();

    private TourApiService service;
    public static final String baseUrl = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/"; //국문 API 서비스


    // newInstance constructor for creating fragment with arguments
    public static Fragment_menu2_first newInstance(int page, String title) {
        Fragment_menu2_first fragment = new Fragment_menu2_first();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu2_first, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.menu2_first_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        loadTourApi(view);

        return view;
    }

    private void loadTourApi(View view) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(TourApiService.class); //api 초기화


        Call<DataRES> client = service.getareaCode(1, 10, 1);
        client.enqueue(new Callback<DataRES>() {
            @Override
            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
                if (response.code() == 200) {
                    DataRES body = response.body();

                    List<String> items = new ArrayList<>();

                    for (int i = 0; i < body.getResponse().getBody().getItems().getItem().size(); i++) {
                        String name = body.getResponse().getBody().getItems().getItem().get(i).getName();
                        items.add(name);
                    }

                    adapter = new DataAdapter(getActivity(),R.layout.fragment_menu2_first_cv);
                    adapter.addItem(items);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                } else {
                    Log.e("리스폰 코드", "" + response.code());
                }

            }


            @Override
            public void onFailure(Call<DataRES> call, Throwable t) {
                Log.d("error", "연결 안됨!");
                Log.d("error", t.getMessage());
                Log.d("error", t.toString());
            }
        });

    }

}
