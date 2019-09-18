package com.example.sktrip.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sktrip.R;
import com.example.sktrip.TourApi.LoadTourApi;
import com.example.sktrip.TourApi.Model.DataRES;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_menu2_TourInfo extends Fragment {
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar TourInfoTitle;
    TextView TourInfoTitle2;
    TextView TourInfoContents;
    ImageView TourInfoFirstImage;


    private String title;
    private String addr1;
    private String addr2;
    private Integer areacode;
    private Integer booktour;
    private String cat1;
    private String cat2;
    private String cat3;
    private Integer contentid;
    private Integer contenttypeid;
    private Long createdtime;
    private String firstimage;
    private String firstimage2;
    private Double mapx;
    private Double mapy;
    private Integer mlevel;
    private Long modifiedtime;
    private Integer readcount;
    private Integer sigungucode;
    private String tel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu2_tourinfo, container, false);


        /*
         *  MainActivity에서 받은 Bundle 데이터 받기
         *
         * */
        Bundle args = new Bundle();
        args = this.getArguments();
        if (args != null) {
            args = getArguments();
            title = args.getString("title");
            addr1 = args.getString("addr1");
            addr2 = args.getString("addr2");
            areacode = args.getInt("areacode");
            booktour = args.getInt("booktour");
            cat1 = args.getString("cat1");
            cat2 = args.getString("cat2");
            cat3 = args.getString("cat3");
            contentid = args.getInt("contentid");
            contenttypeid = args.getInt("contenttypeid");
            createdtime = args.getLong("createdtime");
            firstimage = args.getString("firstimage");
            firstimage2 = args.getString("firstimage2");
            mapx = args.getDouble("mapx");
            mapy = args.getDouble("mapy");
            mlevel = args.getInt("mlevel");
            modifiedtime = args.getLong("modifiedtime");
            readcount = args.getInt("readcount");
            sigungucode = args.getInt("sigungucode");
            tel = args.getString("tel");


            try {
                Log.d("Fragment_menu2_TourInfo", "title :" + title);
                Log.d("Fragment_menu2_TourInfo", "addr1 :" + addr1);
                Log.d("Fragment_menu2_TourInfo", "addr2 :" + addr2);
                Log.d("Fragment_menu2_TourInfo", "areacode :" + String.valueOf(areacode));
                Log.d("Fragment_menu2_TourInfo", "booktour :" + String.valueOf(booktour));
                Log.d("Fragment_menu2_TourInfo", "cat1 :" + cat1);
                Log.d("Fragment_menu2_TourInfo", "cat2 :" + cat2);
                Log.d("Fragment_menu2_TourInfo", "cat3 :" + cat3);
                Log.d("Fragment_menu2_TourInfo", "contentid :" + String.valueOf(contentid));
                Log.d("Fragment_menu2_TourInfo", "contenttypeid :" + String.valueOf(contenttypeid));
                Log.d("Fragment_menu2_TourInfo", "createdtime :" + String.valueOf(createdtime));
                Log.d("Fragment_menu2_TourInfo", "firstimage :" + firstimage);
                Log.d("Fragment_menu2_TourInfo", "firstimage2 :" + firstimage2);
                Log.d("Fragment_menu2_TourInfo", "mapx :" + String.valueOf(mapx));
                Log.d("Fragment_menu2_TourInfo", "mapy :" + String.valueOf(mapy));
                Log.d("Fragment_menu2_TourInfo", "mlevel :" + String.valueOf(mlevel));
                Log.d("Fragment_menu2_TourInfo", "modifiedtime :" + String.valueOf(modifiedtime));
                Log.d("Fragment_menu2_TourInfo", "readcount :" + String.valueOf(readcount));
                Log.d("Fragment_menu2_TourInfo", "sigungucode :" + String.valueOf(sigungucode));
                Log.d("Fragment_menu2_TourInfo", "tel :" + tel);
            }catch (Exception e){

            }
        }


        collapsingToolbarLayout = view.findViewById(R.id.TourInfo_CollapsingToolbarLayout);
        TourInfoTitle = view.findViewById(R.id.TourInfo_Toolbar);
        TourInfoTitle2 = view.findViewById(R.id.TourInfo_Title2);
        TourInfoFirstImage = view.findViewById(R.id.TourInfo_firstimage);
        TourInfoContents = view.findViewById(R.id.TourInfo_contents);

        String TourContents = "";

        collapsingToolbarLayout.setTitleEnabled(false);
        TourInfoTitle.setTitle(title);
        TourInfoTitle2.setText(title);

        Glide.with(getActivity())
                .load(firstimage)
                .into(TourInfoFirstImage);

        TourContents = "주소 : " + addr1 + "\n";
        TourContents += "tel : " + tel + "\n";

        TourInfoContents.setText(TourContents);


        Call<DataRES> client = LoadTourApi.getInstance().getService().getdetailCommon(5,1);
        client.enqueue(new Callback<DataRES>() {
            @Override
            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
                DataRES body = response.body();

                int size = body.getResponse().getBody().getItems().getItem().size(); //api 받아온 데이터 item의 크기
                for(int i=0;i<size;i++){
                    Log.d("Tourinfo",body.getResponse().getBody().getItems().getItem().get(i).getOverview());
                }

            }

            @Override
            public void onFailure(Call<DataRES> call, Throwable t) {
                Log.d("error", "연결 안됨!");
                Log.d("error", t.getMessage());
                Log.d("error", t.toString());
            }
        });





















        /*

        관광지 정보
        식당 정보
        숙박 정보
        상세정보 제공

         */

        return view;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
