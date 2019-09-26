package com.example.sktrip.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.sktrip.Fragment.Fragment_menu2_first;
import com.example.sktrip.Fragment.Fragment_menu2_second;
import com.example.sktrip.Fragment.Fragment_menu2_third;

import java.util.ArrayList;
import java.util.List;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;


    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return Fragment_menu2_first.newInstance();

        } else if (position == 1) {
            return Fragment_menu2_second.newInstance();

        } else if (position == 2) {
            return Fragment_menu2_third.newInstance();

        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
