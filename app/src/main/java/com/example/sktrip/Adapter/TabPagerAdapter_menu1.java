package com.example.sktrip.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sktrip.Fragment.Fragment_menu1_first;
import com.example.sktrip.Fragment.Fragment_menu1_second;

public class TabPagerAdapter_menu1 extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter_menu1(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return Fragment_menu1_first.newInstance();

        } else if (position == 1) {
            return Fragment_menu1_second.newInstance();
        }
        else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}