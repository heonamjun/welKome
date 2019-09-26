package com.example.sktrip.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sktrip.Adapter.SectionPageAdapter;
import com.example.sktrip.Adapter.TabPagerAdapter;
import com.example.sktrip.R;

public class Fragment_menu2 extends Fragment {

    private static final String MENU2 = "MENU2";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private TabPagerAdapter tabPagerAdapter;
    private ViewPager viewPager;
    private SectionPageAdapter sectionPageAdapter;


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

//        sectionPageAdapter = new SectionPageAdapter(getChildFragmentManager());
//        setupViewpager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);

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


    public void setupViewpager(ViewPager viewPager){
        sectionPageAdapter.addFragment(new Fragment_menu2_first() , "관광지");
        sectionPageAdapter.addFragment(new Fragment_menu2_second() , "음식점");
        sectionPageAdapter.addFragment(new Fragment_menu2_third() , "숙박지");
        viewPager.setAdapter(sectionPageAdapter);
    }
}


