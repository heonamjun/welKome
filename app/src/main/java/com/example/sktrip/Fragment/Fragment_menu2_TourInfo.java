package com.example.sktrip.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sktrip.Adapter.TourInfoImageAdapter;
import com.example.sktrip.Data.TourInfoImageData;
import com.example.sktrip.R;
import com.example.sktrip.TourApi.LoadTourApi;
import com.example.sktrip.TourApi.Model.DataRES;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_menu2_TourInfo extends Fragment {
    CollapsingToolbarLayout collapsingToolbarLayout;

    private RecyclerView recyclerView;
    private TourInfoImageAdapter adapter;

    Toolbar toolbar;
    TextView TourInfoTitle;
    TextView TourInfoContents;
    TextView TourInfoContents2;
    TextView TourInfoOverview;
    TextView TourInfoHomepage;
    ImageView TourInfoFirstImage;
    LinearLayout TourInfoLayout;

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
    private String zipcode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu2_tourinfo, container, false);


        /**
         *  MainActivity에서 받은 Bundle 데이터 받기
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
            zipcode = args.getString("zipcode");


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
                Log.d("Fragment_menu2_TourInfo", "zipcode :" + zipcode);
            } catch (Exception e) {

            }
        }


        collapsingToolbarLayout = view.findViewById(R.id.TourInfo_CollapsingToolbarLayout);
        // 상단바
        toolbar = view.findViewById(R.id.TourInfo_Toolbar);
        // 타이틀
        TourInfoTitle = view.findViewById(R.id.TourInfo_Title2);
        // 상단바 이미지
        TourInfoFirstImage = view.findViewById(R.id.TourInfo_firstimage);
        // 제목
        TourInfoContents = view.findViewById(R.id.TourInfo_contents);
        // 우편번호 , 전화번호명 , 전화번호
        TourInfoContents2 = view.findViewById(R.id.TourInfo_contents2);
        // 개요
        TourInfoOverview = view.findViewById(R.id.TourInfo_overview);
        // 관련 홈페이지
        TourInfoHomepage = view.findViewById(R.id.TourInfo_homepage);

        TourInfoLayout = view.findViewById(R.id.TourInfo_layout);


        /**
         * 상단바의 뒤로가기 버튼
         */
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        collapsingToolbarLayout.setTitleEnabled(false);
        toolbar.setTitle(title);
        TourInfoTitle.setText(title);

        /**
         * 상단바의 이미지
         */
        Glide.with(getActivity())
                .load(firstimage)
                .into(TourInfoFirstImage);

        /**
         상세정보 제공
         */
        String TourContents = "";
        if (zipcode != null)
            TourContents += "우편번호 : " + zipcode + "\n";
        if (addr1 != null)
            TourContents += "주소 : " + addr1 + "\n";
        if (tel != null)
            TourContents += "tel : " + tel + "\n";

        TourInfoContents.setText(TourContents);

        //공통정보 조회 조회 및 출력
        TourInfoHO();

        recyclerView = (RecyclerView) view.findViewById(R.id.TourInfoRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false));

        //이미지정보 조회 및 출력
        TourInfoImage();


        return view;
    }


    /**
     * 공통정보 조회
     * overview , homepage 요청 및 출력
     */
    private void TourInfoHO() {
        Call<DataRES> client = LoadTourApi.getInstance().getService().getdetailCommon(contentid);
        client.enqueue(new Callback<DataRES>() {
            @Override
            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
                if (response.code() == 200) {
                    DataRES Body = response.body();
                    Log.d("TourInfoHOMEPAGE", Body.getResponse().getHeader().getResultCode());

                    try {
                        Log.d("id: ", Body.getResponse().getBody().getItems().getItem().get(0).getContentid().toString());
                        Log.d("OVERVIEW", Body.getResponse().getBody().getItems().getItem().get(0).getOverview());
                        Log.d("HOMEPAGE", Body.getResponse().getBody().getItems().getItem().get(0).getHomepage());
                        Log.d("TELNAME", Body.getResponse().getBody().getItems().getItem().get(0).getTelname());
                        Log.d("TEL", Body.getResponse().getBody().getItems().getItem().get(0).getTel());
                    } catch (Exception e) {
                    }

                    if (Body.getResponse().getBody().getItems().getItem().get(0).getTelname() != null) {
                        String Telname = "전화명 : " + Body.getResponse().getBody().getItems().getItem().get(0).getTelname() + "\n";
                        TourInfoContents2.setText(Telname);
                    }

                    if (Body.getResponse().getBody().getItems().getItem().get(0).getTel() != null) {
                        String Tel = "전화 : " + Body.getResponse().getBody().getItems().getItem().get(0).getTel() + "\n";
                        TourInfoContents2.setText(Tel);
                    }


                    if (Body.getResponse().getBody().getItems().getItem().get(0).getOverview() != null) {
                        String Overview = "개요\n" + ChageBr(Body.getResponse().getBody().getItems().getItem().get(0).getOverview()) + "\n";
                        TourInfoOverview.setText(Overview);
                    }
                    if (Body.getResponse().getBody().getItems().getItem().get(0).getHomepage() != null) {
                        String HomePage = "\n" + ChageHOME(Body.getResponse().getBody().getItems().getItem().get(0).getHomepage()) + "\n";
                        TourInfoHomepage.setText(HomePage);
                    }

                }
            }

            @Override
            public void onFailure(Call<DataRES> call, Throwable t) {
                Log.d("error", "연결 안됨!");
                Log.d("error", t.getMessage());
                Log.d("error", t.toString());
                Log.d("error", String.valueOf(t.fillInStackTrace()));
            }
        });
    }


    private void TourInfoImage() {
        Call<DataRES> client = LoadTourApi.getInstance().getService().getdetailImage(contentid, "Y", "Y", 999, 1);
        client.enqueue(new Callback<DataRES>() {
            @Override
            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
                if (response.code() == 200) {
                    DataRES Body = response.body();
                    Log.d("TourInfoImage", Body.getResponse().getHeader().getResultCode());
                    String imageURl = "";

                    String imageURL1 = null;
                    String imageURL2 = null;
                    String imageURL3 = null;

                    ArrayList<TourInfoImageData> data = new ArrayList<>(); //데이터 받아서 adapter 에 보내줄 data 생성
                    for (int i = 0; i < Body.getResponse().getBody().getItems().getItem().size(); i++) {
                        if (Body.getResponse().getBody().getItems().getItem().get(i).getOriginimgurl() != null) {
                            imageURL1 = ChageHttps(Body.getResponse().getBody().getItems().getItem().get(i).getOriginimgurl());
                        }
                        if (Body.getResponse().getBody().getItems().getItem().get(i).getSerialnum() != null) { //추후 변경
                            imageURL2 = ChageHttps(Body.getResponse().getBody().getItems().getItem().get(i).getSerialnum());
                        }
                        if (Body.getResponse().getBody().getItems().getItem().get(i).getSmallimageurl() != null) {
                            imageURL3 = ChageHttps(Body.getResponse().getBody().getItems().getItem().get(i).getSmallimageurl());
                        }

                        try {
                            Log.d("imageURL1", imageURL1);
                            Log.d("imageURL2", imageURL2);
                            Log.d("imageURL3", imageURL3);
                        } catch (Exception e) {

                        }
                        data.add(new TourInfoImageData(imageURL1, imageURL2, imageURL3));
                    }
                    adapter = new TourInfoImageAdapter(data, getActivity(), R.layout.fragment_menu2_tourinfo_cv);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<DataRES> call, Throwable t) {
                Log.d("error", "연결 안됨!");
                Log.d("error", t.getMessage());
                Log.d("error", t.toString());
                Log.d("error", String.valueOf(t.fillInStackTrace()));
            }
        });
    }


    /**
     * ChageHttps
     * http 를 https로 변환
     *
     * @param text
     * @return
     */
    public String ChageHttps(String text) {
        String trans = "";
        trans = text;
        if (trans != null) {
            trans = trans.replace("http://", "https://");
        } else {
            trans = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKMAAAB7CAMAAAAv38DwAAAAOVBMVEX4+Pezuav7+/qvtaba3Nbz9PLQ08zm5+Pp6ue4vrGss6PDyL3w8O7Bxbrs7erT1s7JzcPf4tylrZwuo5cQAAAEK0lEQVR4nO2a3XqkIAyGJQERFIXe/8VuEpwfZ+w+bdcuHOQ76EwVy9tAAgkOg0qlUqlUKpVKpVKpVCqVSqX6PcFykGvN8y6YAh6VVmgNdRRYNC9CM/UFub4hEmRoTXUQjCeMBktXhgwniAZzD4ywy8czRoPu1qAho89JdGpGUr07b81cHJYUz6bhiT1xbMS4hK8RCmVqggjz1xEJ0jYYbpjO/eRT/X/Es5Xlr4prA8azsI0m5TEnPLkVG6yMJ4w4l7rbcfbdndB3wIim3GM1DNsrZA+MmJxA7JzgO2Q0MswwTL46B0zYHSMvd7CmSMLMJn1x/PaMuAmVqdcweG6SOmPkkS5PV1aekrEnRtkpumeimYlCV4y0GkM5GHahNhl7YiQAmJ+nX2Rq2xMjp4BwdJFNGb/PyBkgPM8+EzknfJ0ObRl59h1DDQUjyKYjRsOpwPNgS0x32BUj8iK9Pn4VZtsZ41yX63otZm7hDhlte0Zjak5lUwhhLtIgd7anIAbJBWBwzskW8jXf6YHRmOcK1PtGvA9GHN2OAbC+Jd99MBrE7Fca67XM75lhJ4ycdxnyGWPOctcWjOV7dQrZrP1vLd9CNE2KUuf15U/N2Kbu/BKk/464taqSbmeFnRNADKURIVnSefsVTUPLgjh8TQ0JVao+9BNH+G3ncXsR2e1VRl+Kd/By3+23b5/39vIMxamy7kHI7boUMdSD3rnW8Lycpxt7vz9FM8mBTeBubyUpyWhuxwnW8DOhPFcm8cqzBupMDoGEESaDMSTq8X4udGdkpIU+hJGrVHsbwsKYQkRc6Q/QHp1rqfHjYkZjFtjtSOZYaGrNDzvcGBMhEU7a7cgLepBvq8FME9KFSJYmxnB9bGdGHCsj1yNkv7o8dgo3xo0LkTnkyugoW0T5P3iPxLMAfB6djDVZ8+IFkuaj5fcjhJFGSi5CwjwcGW0gs4U01nS7xDAk3LhpkCrqbjxiDKXYYv3FjNOGqTKOWMeP9mbzK+MYFxe3rTLOBGars1WT243EY23EgWL+tMMfMjoTSz4wzu+MJZbpw1fGhc/faEJM1CAIY/hgpxGfGTPJft7jjxg9m4S7AhuN5CY8gK+MK25bdMLIJMQh85inhdhxZM+/+8yViJWRzzLYHGuUwgn4WmR8ZtyGMKcAlZHiFR/WmOrJhkI++RsyI/vML/g15SPs0BIfKXu2y1KeXuC5M9IcJYsJI/0rYyHXQE5lKEELxa0Wb3b0rHLlexYU17xMwFpXlMNfipLTI4YjM8aNuqcQOcYZqulI1aW9kWco9AtjVbwyy3E5cZxYUpJRdiPH9PywwhoShcExWJhCmsCGEegReb0HtiTFyJWLutnZtHB1bde1yeJ+mnov6AzuEILrDbh9e/x4fO6P7IezmkOoVCqVSqVSqVQqlUqlUqlU/6Y/sxooCVUeRkIAAAAASUVORK5CYII=";
        }

        return trans;
    }

    /**
     * ChageBr
     * overview 의 <br />,<br> 을 "\n"로 바꿔주기
     *
     * @param text
     * @return
     */
    public String ChageBr(String text) {
        String trans;
        trans = text;
        if (trans != null) {
            trans = trans.replace("<br>", "\n");
            trans = trans.replace("<br />", "\n");
            trans = trans.replace("</a>", "");
        } else {

        }
        return trans;
    }

    /**
     * ChageHOME
     * html 태그 제거하기
     *
     * @param html
     * @return
     */
    public String ChageHOME(String html) {
        String trans = "";
        String result = "";
        trans = html;
        String regex1 = "\\<.*?\\>";        //html 태그 제거하기 코드

        if (trans != null) {
            result = trans.replaceAll(regex1, "\n");
        } else {
        }

        return result;
    }

}
