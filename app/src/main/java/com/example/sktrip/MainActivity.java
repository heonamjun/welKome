package com.example.sktrip;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.sktrip.Adapter.Adapter;
import com.example.sktrip.DTO.Footer;
import com.example.sktrip.DTO.Header;
import com.example.sktrip.DTO.ListItem;
import com.example.sktrip.DTO.RecyclerItem;
import com.example.sktrip.Fragment.Fragment_menu1;
import com.example.sktrip.Fragment.Fragment_menu2;
import com.example.sktrip.Fragment.Fragment_menu3;
import com.example.sktrip.Fragment.Fragment_menu4;
import com.example.sktrip.model.DataRES;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String FRAGMENT_FIRST = "FRAGMENT_FIRST";
    public static final String FRAGMENT_SECOND = "FRAGMENT_SECOND";
    public static final String FRAGMENT_THRID = "FRAGMENT_THRID";
    public static final String FRAGMENT_FORTH = "FRAGMENT_FORTH";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_menu1:
                    replaceFragment(new Fragment_menu1(),FRAGMENT_FIRST);
                    return true;
                case R.id.navigation_menu2:
                    replaceFragment(new Fragment_menu2(),FRAGMENT_SECOND);
                    return true;
                case R.id.navigation_menu3:
                    replaceFragment(new Fragment_menu3(),FRAGMENT_THRID);
                    return true;
                case R.id.navigation_menu4:
                    replaceFragment(new Fragment_menu4(),FRAGMENT_FORTH);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_layout, new Fragment_menu1(), FRAGMENT_FIRST)
                .commit();
    }

    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, newFragment, tag)
                .commit();

    }

}




//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        //add space item decoration and pass space you want to give
//        recyclerView.addItemDecoration(new Space(100));
//
//        final List<RecyclerItem> recyclerViewItems = new ArrayList<>();
//
//        service = retrofit.create(TourApiService.class); //api 초기화
//        service.getareaBasedList().enqueue(new Callback<DataRES>() {
//            @Override
//            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
//                if(response.isSuccessful()){
//                    DataRES contents = response.body();
//
//                    Log.d("data", contents.getResponse().getBody().getItems().getItem().get(1).getFirstimage() + "");
//
//                    for(int i = 0; i<contents.getResponse().getBody().getItems().getItem().size();i++){
//                        con = contents.getResponse().getBody().getItems().getItem().get(i).getFirstimage();
//                        if (con.startsWith("http://"))
//                            con = con.replace("http://", "https://");
//                    }
//                    Header header = new Header("Welcome To Food Express", "Non-Veg Menu",
//                            "https://firebasestorage.googleapis.com/v0/b/piko-mobile.appspot.com/o/welcom.jpg?alt=media&token=9621db01-ddc2-40dc-ae3e-6b87ac7835d6");
//                    //add header
//
//                    recyclerViewItems.add(header);
//                    String[] imageUrls = {con,con};
//                    String[] titles = {"경복궁",
//                            "경복궁",
//                            "경복궁", "경복궁", "경복궁"};
//                    String[] descriptions = {"서울특별시 용산구 용산동 125번길",
//                            "서울특별시 용산구 용산동 125번길",
//                            "서울특별시 용산구 용산동 125번길",
//                            "서울특별시 용산구 용산동 125번길", "서울특별시 용산구 용산동 125번길"};
//                    String[] price = {"5000$", "5000$", "5000$", "5000$", "5000$"};
//                    boolean[] isHot = {true, false, true, true, false};
//                    for (int i = 0; i < imageUrls.length; i++) {
//                        ListItem listItem = new ListItem(titles[i], descriptions[i], imageUrls[i], price[i],isHot[i]);
//                        //add food items
//                        recyclerViewItems.add(listItem);
//                    }
//
//                    Footer footer = new Footer("메에엥롱.",
//                            "허남준", con);
//                    //add footer
//                    recyclerViewItems.add(footer);
//                    //init RecyclerView
//
//                    //finally set adapter
//
//                }else{
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DataRES> call, Throwable t) {
//                Log.d("error", "2");
//            }
//        });
//
//
//        recyclerView.setAdapter(new Adapter(recyclerViewItems, this));

//    }
    //Method to create dummy data
/*
    List<RecyclerItem> createDummyList() {
        List<RecyclerItem> recyclerViewItems = new ArrayList<>();
        Header header = new Header("Welcome To Food Express", "Non-Veg Menu",
                "https://firebasestorage.googleapis.com/v0/b/piko-mobile.appspot.com/o/welcom.jpg?alt=media&token=9621db01-ddc2-40dc-ae3e-6b87ac7835d6");
        //add header

        recyclerViewItems.add(header);
        String[] imageUrls = {con,con};
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
            ListItem listItem = new ListItem(titles[i], descriptions[i], imageUrls[i], price[i],isHot[i]);
            //add food items
            recyclerViewItems.add(listItem);
        }

        Footer footer = new Footer("메에엥롱.",
                "허남준", con);
        //add footer
        recyclerViewItems.add(footer);
        return recyclerViewItems;
    }*/
//}
