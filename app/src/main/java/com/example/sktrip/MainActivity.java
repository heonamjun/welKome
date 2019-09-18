package com.example.sktrip;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.sktrip.Fragment.Fragment_menu1;
import com.example.sktrip.Fragment.Fragment_menu2;
import com.example.sktrip.Fragment.Fragment_menu2_TourInfo;
import com.example.sktrip.Fragment.Fragment_menu3;
import com.example.sktrip.Fragment.Fragment_menu4;
import com.example.sktrip.TourApi.OnItemClick;


public class MainActivity extends AppCompatActivity implements OnItemClick {

    public static FragmentTransaction ft;
    public static FragmentManager fm;

    public FragmentManager manager = getSupportFragmentManager();
    public FragmentTransaction transaction;

    private static final String SELECTED_ITEM = "SELECTED_ITEM";
    public static final String MENU1 = "MENU1";
    public static final String MENU2 = "MENU2";
    public static final String MENU3 = "MENU3";
    public static final String MENU4 = "MENU4";
    public static final String TOURINFO = "TOURINFO";


    final Fragment fragmentMenu1 = new Fragment_menu1();
    final Fragment fragmentMenu2 = new Fragment_menu2();
    final Fragment fragmentMenu3 = new Fragment_menu3();
    final Fragment fragmentMenu4 = new Fragment_menu4();


    public BottomNavigationView navigation;
    private int mselecteditem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();


        /**
         BottomNavigationView 지정 (리스너)
         */

        navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                SelectedFragment(menuItem);
                return false;
            }
        });


        MenuItem selecteditem;

        if (savedInstanceState != null) {
            mselecteditem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selecteditem = navigation.getMenu().findItem(mselecteditem);
        } else {
            selecteditem = navigation.getMenu().getItem(0);
        }
        SelectedFragment(selecteditem);

    }


    /*
        mselecteditem 저장
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mselecteditem);
        super.onSaveInstanceState(outState);
    }


    /*
        뒤로가기 버튼 눌렀을 때 추천하기로 이동
     */
    @Override
    public void onBackPressed() {
        MenuItem homeItem = navigation.getMenu().getItem(0);

        if (mselecteditem != homeItem.getItemId()) {
            SelectedFragment(homeItem);

            // Select home item
            navigation.setSelectedItemId(homeItem.getItemId());
        } else {
            super.onBackPressed();
        }
    }

    public void SelectedFragment(MenuItem item) {
        Fragment frag = null;
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.navigation_menu1:
                replaceFragment(fragmentMenu1, MENU1);
                break;

            case R.id.navigation_menu2:
                replaceFragment(fragmentMenu2, MENU2);
                break;

            case R.id.navigation_menu3:
                replaceFragment(fragmentMenu3, MENU3);
                break;

            case R.id.navigation_menu4:
                replaceFragment(fragmentMenu4, MENU4);
                break;
        }

        mselecteditem = item.getItemId();

        /*
            첫화면 설정
         */
        if (frag != null) {
            transaction = manager.beginTransaction();
            transaction.replace(R.id.frame_layout, fragmentMenu1, MENU1).commit();
        }
    }

    public void replaceFragment(Fragment fragment, String tag) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment, tag)
                .addToBackStack(null)
                .commit();

    }


    /*
            DataAdapter 선택시
            @NonNull >>> 디폴트 인수 일 때 null
     */
    @Override
    public void onClick(@NonNull String title,
                        @NonNull String addr1,
                        @NonNull String addr2,
                        @NonNull Integer areacode,
                        @NonNull Integer booktour,
                        @NonNull String cat1,
                        @NonNull String cat2,
                        @NonNull String cat3,
                        @NonNull Integer contentid,
                        @NonNull Integer contenttypeid,
                        @NonNull Long createdtime,
                        @NonNull String firstimage,
                        @NonNull String firstimage2,
                        @NonNull Double mapx,
                        @NonNull Double mapy,
                        @NonNull Integer mlevel,
                        @NonNull Long modifiedtime,
                        @NonNull Integer readcount,
                        @NonNull Integer sigungucode,
                        @NonNull String tel) {


        Log.d("MainActivity_Title", title); // 확인
        final Fragment fragmentMenu2TourInfo = new Fragment_menu2_TourInfo();
        Bundle args = new Bundle();

        /*
            Fragment_menu2_TourInfo.class 생성 및
            DataAdapter 에서 받은 데이터 Fragment_menu2_TourInfo.class 에 전송
            null이 아닐 경우에만 데이터 보내기

         */
        if (title != null)
            args.putString("title", title);
        if (addr1 != null)
            args.putString("addr1", addr1);
        if (addr2 != null)
            args.putString("addr2", addr2);
        if (areacode != null)
            args.putInt("areacode", areacode);
        if (booktour != null)
            args.putInt("booktour", booktour);
        if (cat1 != null)
            args.putString("cat1", cat1);
        if (cat2 != null)
            args.putString("cat2", cat2);
        if (cat3 != null)
            args.putString("cat3", cat3);
        if (contentid != null)
            args.putInt("contentid", contentid);
        if (contenttypeid != null)
            args.putInt("contenttypeid", contenttypeid);
        if (createdtime != null)
            args.putLong("createdtime", createdtime);
        if (firstimage != null)
            args.putString("firstimage", firstimage);
        if (firstimage2 != null)
            args.putString("firstimage2", firstimage2);
        if (mapx != null)
            args.putDouble("mapx", mapx);
        if (mapy != null)
            args.putDouble("mapy", mapy);
        if (mlevel != null)
            args.putInt("mlevel", mlevel);
        if (modifiedtime != null)
            args.putLong("modifiedtime", modifiedtime);
        if (readcount != null)
            args.putInt("readcount", readcount);
        if (sigungucode != null)
            args.putInt("titsigungucodele", sigungucode);
        if (tel != null)
            args.putString("tel", tel);


        fragmentMenu2TourInfo.setArguments(args);

        transaction = manager.beginTransaction();
        transaction.add(R.id.frame_layout, fragmentMenu2TourInfo, TOURINFO)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onclick(String value) {

    }
}