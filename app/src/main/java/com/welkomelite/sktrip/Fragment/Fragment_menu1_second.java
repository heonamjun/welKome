package com.welkomelite.sktrip.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.welkomelite.sktrip.Adapter.StarImageAdapter;
import com.welkomelite.sktrip.Data.starItem;
import com.welkomelite.sktrip.R;
import com.welkomelite.sktrip.TourApi.OnItemClick;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_menu1_second extends Fragment  {

    RecyclerView mrecyclerview;
    RecyclerView.Adapter madapter;

    RecyclerView.LayoutManager mlayoutmanager;
    ArrayList<starItem> mydataset;
    private Context mContext;
    OnItemClick callback;


    public static Fragment_menu2_second newInstance() {
        Fragment_menu2_second fragment = new Fragment_menu2_second();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu1_star, container, false);

        final LinearLayout listb,poselist;



        poselist =  (LinearLayout)view.findViewById(R.id.recyclelist2); // 연예인 포즈


        mrecyclerview=(RecyclerView)view.findViewById(R.id.recyclerviewtest);
        mrecyclerview.setHasFixedSize(true); // 카드뷰 사이즈 고정
        mlayoutmanager = new GridLayoutManager(mContext, 2);// 사이클뷰 디자인부분 2열
    //    mlayoutmanager = new LinearLayoutManager(mContext , LinearLayout.VERTICAL,false); // 가로로 스크롤
        mrecyclerview.setLayoutManager(mlayoutmanager);



        mydataset = new ArrayList<>();



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(); // 파베 객체 생성.ㄵ
        DatabaseReference databaseReference = firebaseDatabase.getReference("drama_list"); //v파베에서 참조할 데이터 .ㄵ

        databaseReference.addValueEventListener(new ValueEventListener() { // 파베 데이터참조 값이 변경되거나 부를때 쓰는 리스너.ㄵ
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mydataset.clear();
                for (DataSnapshot filesnapshot : dataSnapshot.getChildren()) {
                    starItem ditem = filesnapshot.getValue(starItem.class);                    //item.class 에다가 filesnapshot(데이터값) 넣기.ㄵ
                    mydataset.add(ditem);
                }
                madapter.notifyDataSetChanged(); //어댑터에 리스트가 바뀌엇다는걸 알림.ㄵ
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        // 참조 데이터 기준으로 콜벡리스너
        madapter = new StarImageAdapter(getActivity().getApplicationContext(), mydataset,callback);

        mrecyclerview.setAdapter(madapter);
        return view;
    }

}
