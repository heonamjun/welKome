package com.example.sktrip.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.sktrip.Adapter.Adapter;
import com.example.sktrip.DTO.Footer;
import com.example.sktrip.DTO.Header;
import com.example.sktrip.DTO.ListItem;
import com.example.sktrip.DTO.RecyclerItem;
import com.example.sktrip.TourApi.Model.DataRES;
import com.example.sktrip.R;

import com.example.sktrip.Space;
import com.example.sktrip.TourApi.TourApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Fragment_menu1 extends Fragment {
    public static final String PAGE_TITLE = "추천하기";
    private TourApiService service;
    static String con = "";
    public static final String baseUrl = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu1, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //add space item decoration and pass space you want to give
        recyclerView.addItemDecoration(new Space(100));

        final List<RecyclerItem> recyclerViewItems = new ArrayList<>();

        service = retrofit.create(TourApiService.class); //api 초기화
        service.getareaBasedList().enqueue(new Callback<DataRES>() {
            @Override
            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
                if (response.isSuccessful()) {
                    DataRES contents = response.body();

                    Log.d("data", contents.getResponse().getBody().getItems().getItem().get(1).getFirstimage() + "");

                    for (int i = 0; i < contents.getResponse().getBody().getItems().getItem().size(); i++) {
                        con = contents.getResponse().getBody().getItems().getItem().get(i).getFirstimage();
                        if (con.startsWith("http://"))
                            con = con.replace("http://", "https://");
                    }
                    Header header = new Header("Welcome To Food Express", "Non-Veg Menu",
                            "https://firebasestorage.googleapis.com/v0/b/piko-mobile.appspot.com/o/welcom.jpg?alt=media&token=9621db01-ddc2-40dc-ae3e-6b87ac7835d6");
                    //add header

                    recyclerViewItems.add(header);
                    String[] imageUrls = {con, con};
                    String[] titles = {"경복궁",
                            "경복궁",
                            "경복궁", "경복궁", "경복궁"};
                    String[] descriptions = {"서울특별시 용산구 용산동 125번길",
                            "서울특별시 용산구 용산동 125번길",
                            "서울특별시 용산구 용산동 125번길",
                            "서울특별시 용산구 용산동 125번길", "서울특별시 용산구 용산동 125번길"};
                    String[] price = {"5000$", "5000$", "5000$", "5000$", "5000$"};
                    boolean[] isHot = {true, false, true, true, false};
                    for (int i = 0; i < imageUrls.length; i++) {
                        ListItem listItem = new ListItem(titles[i], descriptions[i], imageUrls[i], price[i], isHot[i]);
                        //add food items
                        recyclerViewItems.add(listItem);
                    }

                    Footer footer = new Footer("메에엥롱.",
                            "허남준", con);
                    //add footer
                    recyclerViewItems.add(footer);
                    //init RecyclerView

                    //finally set adapter

                } else {
                }
                recyclerView.setAdapter(new Adapter(recyclerViewItems, getActivity()));

            }

            @Override
            public void onFailure(Call<DataRES> call, Throwable t) {
                Log.d("error", "2");
            }
        });
        return view;
    }


}
