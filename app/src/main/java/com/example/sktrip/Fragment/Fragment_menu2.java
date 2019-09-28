package com.example.sktrip.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sktrip.Adapter.TabPagerAdapter;
import com.example.sktrip.R;
import com.example.sktrip.Retrofit.APIClient;
import com.example.sktrip.Retrofit.APIInterface;
import com.example.sktrip.Retrofit.ratingData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sktrip.Fragment.Fragment_menu1_first.ALL_LIST;
import static com.example.sktrip.Fragment.Fragment_menu1_first.STAR_LIST;
import static com.example.sktrip.MainActivity.TOURINFO;

public class Fragment_menu2 extends Fragment {

    private static final String MENU2 = "MENU2";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private TabPagerAdapter tabPagerAdapter;
    private ViewPager viewPager;
    public FragmentTransaction transaction;

    private TextView TourGradeCount;


    public Fragment_menu2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu2, container, false);


        toolbar = (Toolbar) view.findViewById(R.id.TourToolbar);
        tabLayout = (TabLayout) view.findViewById(R.id.TourTopLayout);
        viewPager = (ViewPager) view.findViewById(R.id.vpPager);
        TourGradeCount = (TextView) view.findViewById(R.id.TourGradeCount);

//        sectionPageAdapter = new SectionPageAdapter(getChildFragmentManager());
//        setupViewpager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);

        final SharedPreferences preferences = getActivity().getSharedPreferences("auto", Context.MODE_PRIVATE);

        APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);

        final String userID = preferences.getString("inputId",null);


        Call<List<ratingData>> call = apiInterface.RatingDataCount(userID);
        call.enqueue(new Callback<List<ratingData>>() {
            @Override
            public void onResponse(Call<List<ratingData>> call, Response<List<ratingData>> response) {
                int count = response.body().get(0).getCount();

                String contents  = "";
                contents += count + "개의 평점을 주셨습니다.";
                TourGradeCount.setText(contents);
            }

            @Override
            public void onFailure(Call<List<ratingData>> call, Throwable t) {

            }
        });




        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);

        tabLayout.addTab(tabLayout.newTab().setText("관광"));
        tabLayout.addTab(tabLayout.newTab().setText("음식점"));
        tabLayout.addTab(tabLayout.newTab().setText("숙박"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabPagerAdapter);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Fragment all_list = getFragmentManager().findFragmentByTag(ALL_LIST);
        transaction = getFragmentManager().beginTransaction();

        Fragment star_list = getFragmentManager().findFragmentByTag(STAR_LIST);
        if(all_list != null){
            transaction.remove(all_list).commit();
        }
        if(star_list !=null) {
            transaction.remove(star_list).commit();
        }

    }

}


