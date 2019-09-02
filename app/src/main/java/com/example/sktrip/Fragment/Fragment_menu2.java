package com.example.sktrip.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.sktrip.R;

public class Fragment_menu2 extends Fragment {
    public static final String PAGE_TITLE = "평가하기";
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;


    public Fragment_menu2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment
     *
     * @return A new instance of fragment ViewPagerFragment.
     */

    public static Fragment_menu2 newInstance() {
        Fragment_menu2 fragment = new Fragment_menu2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu2, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.vpPager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        return view;
    }


    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private static final int NUM_ITEMS = 3; // ViewPager 페이지 수

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return Fragment_menu2_first.newInstance(0,"A");
            } else if (position == 1) {
                return Fragment_menu2_second.newInstance(1, "B");
            } else if (position == 2) {
                return Fragment_menu2_third.newInstance(2,"C");
            } else{
                return Fragment_menu2.newInstance();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {

            if (position == 0) {
                return Fragment_menu2_first.PAGE_TITLE;
            } else if (position == 1) {
                return Fragment_menu2_second.PAGE_TITLE;
            } else if (position == 2) {
                return Fragment_menu2_third.PAGE_TITLE;
            } else{
                return Fragment_menu2.PAGE_TITLE;
            }
        }
    }




}


