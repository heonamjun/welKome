package com.example.sktrip.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.sktrip.Adapter.firstpageAdapter;
import com.example.sktrip.Data.GradeTourData;
import com.example.sktrip.R;
import com.example.sktrip.TourApi.LoadTourApi;
import com.example.sktrip.TourApi.Model.DataRES;
import com.example.sktrip.TourApi.OnItemClick;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Fragment_menu1_all_list extends Fragment {

    private int SpinnerNum = 1;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private firstpageAdapter adapter;

    public static Fragment_menu1_all_list newInstance() {
        Fragment_menu1_all_list fragment = new Fragment_menu1_all_list();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu2_viewpager, container, false);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final Spinner spinner = (Spinner) view.findViewById(R.id.FristSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    SearchArea(1);//서울
                    SpinnerNum = 1;
                } else if (position == 1) {
                    SearchArea(2);//인천
                    SpinnerNum = 2;
                } else if (position == 2) {
                    SearchArea(3);//대전
                    SpinnerNum = 3;
                } else if (position == 3) {
                    SearchArea(4);//대구
                    SpinnerNum = 4;
                } else if (position == 4) {
                    SearchArea(5);//광주
                    SpinnerNum = 5;
                } else if (position == 5) {
                    SearchArea(6);//부산
                    SpinnerNum = 6;
                } else if (position == 6) {
                    SearchArea(7);//울산
                    SpinnerNum = 7;
                } else if (position == 7) {
                    SearchArea(8);//세종
                    SpinnerNum = 8;
                } else if (position == 8) {
                    SearchArea(31);//경기도
                    SpinnerNum = 31;
                } else if (position == 9) {
                    SearchArea(32);//강원도
                    SpinnerNum = 32;
                } else if (position == 10) {
                    SearchArea(33);//충청북도
                    SpinnerNum = 33;
                } else if (position == 11) {
                    SearchArea(34);//충청남도
                    SpinnerNum = 34;
                } else if (position == 12) {
                    SearchArea(35);//경상북도
                    SpinnerNum = 35;
                } else if (position == 13) {
                    SearchArea(36);//경상남도
                    SpinnerNum = 36;
                } else if (position == 14) {
                    SearchArea(37);//전라북도
                    SpinnerNum = 37;
                } else if (position == 15) {
                    SearchArea(38);//전라남도
                    SpinnerNum = 38;
                } else if (position == 16) {
                    SearchArea(39);//제주도
                    SpinnerNum = 39;
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        SearchView searchview = (SearchView) view.findViewById(R.id.SearchTour);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchKeyword(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        recyclerView(view);
        SearchArea(1);
        Refresh();
        return view;
    }

    private void recyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.menu2_first_rv);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.menu2_first_sr);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager
                = new GridLayoutManager(getContext(),2);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(gridLayoutManager);

    }



    private void Refresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SearchArea(SpinnerNum);

                swipeRefreshLayout.setColorSchemeResources(
                        android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                );
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void SearchArea(int areacode) {
        Call<DataRES> client = LoadTourApi.getInstance().getService().getareaBasedList("Y", "B", 12, areacode, 999, 1);
        client.enqueue(new Callback<DataRES>() {
            @Override
            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
                if (response.code() == 200) {
                    DataRES body = response.body();

                    ArrayList<GradeTourData> data = new ArrayList<>(); //데이터 받아서 adapter 에 보내줄 data 생성
                    int size = body.getResponse().getBody().getItems().getItem().size(); //api 받아온 데이터 item의 크기
                    int[] num = new int[size];

                    for (int i = 0; i < size; i++) {    //item의 크기만큼 중복없이 난수 생성
                        num[i] = (int) (Math.random() * size);
                        for (int j = i; j < i - 1; j++) {
                            if (num[i] == num[j]) {
                                i--;
                                break;
                            }
                        }
                    }

                    for (int i = 0; i < size; i++) {
                        String con1 = "";
                        String con2 = "";
                        con1 = body.getResponse().getBody().getItems().getItem().get(num[i]).getFirstimage();
                        con2 = body.getResponse().getBody().getItems().getItem().get(num[i]).getFirstimage2();
                        if (con1 != null) {
                            con1 = con1.replace("http://", "https://");
                            con2 = con2.replace("http://", "https://");
                        } else {
                            con1 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKMAAAB7CAMAAAAv38DwAAAAOVBMVEX4+Pezuav7+/qvtaba3Nbz9PLQ08zm5+Pp6ue4vrGss6PDyL3w8O7Bxbrs7erT1s7JzcPf4tylrZwuo5cQAAAEK0lEQVR4nO2a3XqkIAyGJQERFIXe/8VuEpwfZ+w+bdcuHOQ76EwVy9tAAgkOg0qlUqlUKpVKpVKpVCqVSqX6PcFykGvN8y6YAh6VVmgNdRRYNC9CM/UFub4hEmRoTXUQjCeMBktXhgwniAZzD4ywy8czRoPu1qAho89JdGpGUr07b81cHJYUz6bhiT1xbMS4hK8RCmVqggjz1xEJ0jYYbpjO/eRT/X/Es5Xlr4prA8azsI0m5TEnPLkVG6yMJ4w4l7rbcfbdndB3wIim3GM1DNsrZA+MmJxA7JzgO2Q0MswwTL46B0zYHSMvd7CmSMLMJn1x/PaMuAmVqdcweG6SOmPkkS5PV1aekrEnRtkpumeimYlCV4y0GkM5GHahNhl7YiQAmJ+nX2Rq2xMjp4BwdJFNGb/PyBkgPM8+EzknfJ0ObRl59h1DDQUjyKYjRsOpwPNgS0x32BUj8iK9Pn4VZtsZ41yX63otZm7hDhlte0Zjak5lUwhhLtIgd7anIAbJBWBwzskW8jXf6YHRmOcK1PtGvA9GHN2OAbC+Jd99MBrE7Fca67XM75lhJ4ycdxnyGWPOctcWjOV7dQrZrP1vLd9CNE2KUuf15U/N2Kbu/BKk/464taqSbmeFnRNADKURIVnSefsVTUPLgjh8TQ0JVao+9BNH+G3ncXsR2e1VRl+Kd/By3+23b5/39vIMxamy7kHI7boUMdSD3rnW8Lycpxt7vz9FM8mBTeBubyUpyWhuxwnW8DOhPFcm8cqzBupMDoGEESaDMSTq8X4udGdkpIU+hJGrVHsbwsKYQkRc6Q/QHp1rqfHjYkZjFtjtSOZYaGrNDzvcGBMhEU7a7cgLepBvq8FME9KFSJYmxnB9bGdGHCsj1yNkv7o8dgo3xo0LkTnkyugoW0T5P3iPxLMAfB6djDVZ8+IFkuaj5fcjhJFGSi5CwjwcGW0gs4U01nS7xDAk3LhpkCrqbjxiDKXYYv3FjNOGqTKOWMeP9mbzK+MYFxe3rTLOBGars1WT243EY23EgWL+tMMfMjoTSz4wzu+MJZbpw1fGhc/faEJM1CAIY/hgpxGfGTPJft7jjxg9m4S7AhuN5CY8gK+MK25bdMLIJMQh85inhdhxZM+/+8yViJWRzzLYHGuUwgn4WmR8ZtyGMKcAlZHiFR/WmOrJhkI++RsyI/vML/g15SPs0BIfKXu2y1KeXuC5M9IcJYsJI/0rYyHXQE5lKEELxa0Wb3b0rHLlexYU17xMwFpXlMNfipLTI4YjM8aNuqcQOcYZqulI1aW9kWco9AtjVbwyy3E5cZxYUpJRdiPH9PywwhoShcExWJhCmsCGEegReb0HtiTFyJWLutnZtHB1bde1yeJ+mnov6AzuEILrDbh9e/x4fO6P7IezmkOoVCqVSqVSqVQqlUqlUqlU/6Y/sxooCVUeRkIAAAAASUVORK5CYII=";
                            con2 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKMAAAB7CAMAAAAv38DwAAAAOVBMVEX4+Pezuav7+/qvtaba3Nbz9PLQ08zm5+Pp6ue4vrGss6PDyL3w8O7Bxbrs7erT1s7JzcPf4tylrZwuo5cQAAAEK0lEQVR4nO2a3XqkIAyGJQERFIXe/8VuEpwfZ+w+bdcuHOQ76EwVy9tAAgkOg0qlUqlUKpVKpVKpVCqVSqX6PcFykGvN8y6YAh6VVmgNdRRYNC9CM/UFub4hEmRoTXUQjCeMBktXhgwniAZzD4ywy8czRoPu1qAho89JdGpGUr07b81cHJYUz6bhiT1xbMS4hK8RCmVqggjz1xEJ0jYYbpjO/eRT/X/Es5Xlr4prA8azsI0m5TEnPLkVG6yMJ4w4l7rbcfbdndB3wIim3GM1DNsrZA+MmJxA7JzgO2Q0MswwTL46B0zYHSMvd7CmSMLMJn1x/PaMuAmVqdcweG6SOmPkkS5PV1aekrEnRtkpumeimYlCV4y0GkM5GHahNhl7YiQAmJ+nX2Rq2xMjp4BwdJFNGb/PyBkgPM8+EzknfJ0ObRl59h1DDQUjyKYjRsOpwPNgS0x32BUj8iK9Pn4VZtsZ41yX63otZm7hDhlte0Zjak5lUwhhLtIgd7anIAbJBWBwzskW8jXf6YHRmOcK1PtGvA9GHN2OAbC+Jd99MBrE7Fca67XM75lhJ4ycdxnyGWPOctcWjOV7dQrZrP1vLd9CNE2KUuf15U/N2Kbu/BKk/464taqSbmeFnRNADKURIVnSefsVTUPLgjh8TQ0JVao+9BNH+G3ncXsR2e1VRl+Kd/By3+23b5/39vIMxamy7kHI7boUMdSD3rnW8Lycpxt7vz9FM8mBTeBubyUpyWhuxwnW8DOhPFcm8cqzBupMDoGEESaDMSTq8X4udGdkpIU+hJGrVHsbwsKYQkRc6Q/QHp1rqfHjYkZjFtjtSOZYaGrNDzvcGBMhEU7a7cgLepBvq8FME9KFSJYmxnB9bGdGHCsj1yNkv7o8dgo3xo0LkTnkyugoW0T5P3iPxLMAfB6djDVZ8+IFkuaj5fcjhJFGSi5CwjwcGW0gs4U01nS7xDAk3LhpkCrqbjxiDKXYYv3FjNOGqTKOWMeP9mbzK+MYFxe3rTLOBGars1WT243EY23EgWL+tMMfMjoTSz4wzu+MJZbpw1fGhc/faEJM1CAIY/hgpxGfGTPJft7jjxg9m4S7AhuN5CY8gK+MK25bdMLIJMQh85inhdhxZM+/+8yViJWRzzLYHGuUwgn4WmR8ZtyGMKcAlZHiFR/WmOrJhkI++RsyI/vML/g15SPs0BIfKXu2y1KeXuC5M9IcJYsJI/0rYyHXQE5lKEELxa0Wb3b0rHLlexYU17xMwFpXlMNfipLTI4YjM8aNuqcQOcYZqulI1aW9kWco9AtjVbwyy3E5cZxYUpJRdiPH9PywwhoShcExWJhCmsCGEegReb0HtiTFyJWLutnZtHB1bde1yeJ+mnov6AzuEILrDbh9e/x4fO6P7IezmkOoVCqVSqVSqVQqlUqlUqlU/6Y/sxooCVUeRkIAAAAASUVORK5CYII=";
                        }

                        data.add(new GradeTourData(
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getTitle(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getAddr1(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getAddr2(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getAreacode(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getBooktour(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getCat1(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getCat2(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getCat3(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getContentid(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getContenttypeid(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getCreatedtime(),
                                con1,
                                con2,
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getMapx(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getMapy(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getMlevel(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getModifiedtime(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getReadcount(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getSigungucode(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getTel(),
                                body.getResponse().getBody().getItems().getItem().get(num[i]).getZipcode()
                        ));
                    }

                    adapter = new firstpageAdapter(data, getActivity(), R.layout.fragment_menu1_first_cv, (OnItemClick) getActivity());
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("리스폰 코드", "" + response.code());
                }

            }

            @Override
            public void onFailure(Call<DataRES> call, Throwable t) {
                Log.d("error", "연결 안됨!");
                Log.d("error", t.getMessage());
                Log.d("error", t.toString());
            }
        });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void SearchKeyword(String query) {
        String keyword = "";
        keyword = query.trim();

        Call<DataRES> client = LoadTourApi.getInstance().getService().getsearchKeyword("Y", "A", 12, keyword, 999, 1);
        client.enqueue(new Callback<DataRES>() {
            @Override
            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
                if (response.code() == 200) {
                    DataRES body = response.body();

                    ArrayList<GradeTourData> data = new ArrayList<>(); //데이터 받아서 adapter 에 보내줄 data 생성

                    for (int i = 0; i < body.getResponse().getBody().getItems().getItem().size(); i++) {

                        String con1 = "";
                        String con2 = "";
                        con1 = body.getResponse().getBody().getItems().getItem().get(i).getFirstimage();
                        con2 = body.getResponse().getBody().getItems().getItem().get(i).getFirstimage2();
                        if (con1 != null) {
                            con1 = con1.replace("http://", "https://");
                            con2 = con2.replace("http://", "https://");
                        } else {
                            con1 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKMAAAB7CAMAAAAv38DwAAAAOVBMVEX4+Pezuav7+/qvtaba3Nbz9PLQ08zm5+Pp6ue4vrGss6PDyL3w8O7Bxbrs7erT1s7JzcPf4tylrZwuo5cQAAAEK0lEQVR4nO2a3XqkIAyGJQERFIXe/8VuEpwfZ+w+bdcuHOQ76EwVy9tAAgkOg0qlUqlUKpVKpVKpVCqVSqX6PcFykGvN8y6YAh6VVmgNdRRYNC9CM/UFub4hEmRoTXUQjCeMBktXhgwniAZzD4ywy8czRoPu1qAho89JdGpGUr07b81cHJYUz6bhiT1xbMS4hK8RCmVqggjz1xEJ0jYYbpjO/eRT/X/Es5Xlr4prA8azsI0m5TEnPLkVG6yMJ4w4l7rbcfbdndB3wIim3GM1DNsrZA+MmJxA7JzgO2Q0MswwTL46B0zYHSMvd7CmSMLMJn1x/PaMuAmVqdcweG6SOmPkkS5PV1aekrEnRtkpumeimYlCV4y0GkM5GHahNhl7YiQAmJ+nX2Rq2xMjp4BwdJFNGb/PyBkgPM8+EzknfJ0ObRl59h1DDQUjyKYjRsOpwPNgS0x32BUj8iK9Pn4VZtsZ41yX63otZm7hDhlte0Zjak5lUwhhLtIgd7anIAbJBWBwzskW8jXf6YHRmOcK1PtGvA9GHN2OAbC+Jd99MBrE7Fca67XM75lhJ4ycdxnyGWPOctcWjOV7dQrZrP1vLd9CNE2KUuf15U/N2Kbu/BKk/464taqSbmeFnRNADKURIVnSefsVTUPLgjh8TQ0JVao+9BNH+G3ncXsR2e1VRl+Kd/By3+23b5/39vIMxamy7kHI7boUMdSD3rnW8Lycpxt7vz9FM8mBTeBubyUpyWhuxwnW8DOhPFcm8cqzBupMDoGEESaDMSTq8X4udGdkpIU+hJGrVHsbwsKYQkRc6Q/QHp1rqfHjYkZjFtjtSOZYaGrNDzvcGBMhEU7a7cgLepBvq8FME9KFSJYmxnB9bGdGHCsj1yNkv7o8dgo3xo0LkTnkyugoW0T5P3iPxLMAfB6djDVZ8+IFkuaj5fcjhJFGSi5CwjwcGW0gs4U01nS7xDAk3LhpkCrqbjxiDKXYYv3FjNOGqTKOWMeP9mbzK+MYFxe3rTLOBGars1WT243EY23EgWL+tMMfMjoTSz4wzu+MJZbpw1fGhc/faEJM1CAIY/hgpxGfGTPJft7jjxg9m4S7AhuN5CY8gK+MK25bdMLIJMQh85inhdhxZM+/+8yViJWRzzLYHGuUwgn4WmR8ZtyGMKcAlZHiFR/WmOrJhkI++RsyI/vML/g15SPs0BIfKXu2y1KeXuC5M9IcJYsJI/0rYyHXQE5lKEELxa0Wb3b0rHLlexYU17xMwFpXlMNfipLTI4YjM8aNuqcQOcYZqulI1aW9kWco9AtjVbwyy3E5cZxYUpJRdiPH9PywwhoShcExWJhCmsCGEegReb0HtiTFyJWLutnZtHB1bde1yeJ+mnov6AzuEILrDbh9e/x4fO6P7IezmkOoVCqVSqVSqVQqlUqlUqlU/6Y/sxooCVUeRkIAAAAASUVORK5CYII=";
                            con2 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKMAAAB7CAMAAAAv38DwAAAAOVBMVEX4+Pezuav7+/qvtaba3Nbz9PLQ08zm5+Pp6ue4vrGss6PDyL3w8O7Bxbrs7erT1s7JzcPf4tylrZwuo5cQAAAEK0lEQVR4nO2a3XqkIAyGJQERFIXe/8VuEpwfZ+w+bdcuHOQ76EwVy9tAAgkOg0qlUqlUKpVKpVKpVCqVSqX6PcFykGvN8y6YAh6VVmgNdRRYNC9CM/UFub4hEmRoTXUQjCeMBktXhgwniAZzD4ywy8czRoPu1qAho89JdGpGUr07b81cHJYUz6bhiT1xbMS4hK8RCmVqggjz1xEJ0jYYbpjO/eRT/X/Es5Xlr4prA8azsI0m5TEnPLkVG6yMJ4w4l7rbcfbdndB3wIim3GM1DNsrZA+MmJxA7JzgO2Q0MswwTL46B0zYHSMvd7CmSMLMJn1x/PaMuAmVqdcweG6SOmPkkS5PV1aekrEnRtkpumeimYlCV4y0GkM5GHahNhl7YiQAmJ+nX2Rq2xMjp4BwdJFNGb/PyBkgPM8+EzknfJ0ObRl59h1DDQUjyKYjRsOpwPNgS0x32BUj8iK9Pn4VZtsZ41yX63otZm7hDhlte0Zjak5lUwhhLtIgd7anIAbJBWBwzskW8jXf6YHRmOcK1PtGvA9GHN2OAbC+Jd99MBrE7Fca67XM75lhJ4ycdxnyGWPOctcWjOV7dQrZrP1vLd9CNE2KUuf15U/N2Kbu/BKk/464taqSbmeFnRNADKURIVnSefsVTUPLgjh8TQ0JVao+9BNH+G3ncXsR2e1VRl+Kd/By3+23b5/39vIMxamy7kHI7boUMdSD3rnW8Lycpxt7vz9FM8mBTeBubyUpyWhuxwnW8DOhPFcm8cqzBupMDoGEESaDMSTq8X4udGdkpIU+hJGrVHsbwsKYQkRc6Q/QHp1rqfHjYkZjFtjtSOZYaGrNDzvcGBMhEU7a7cgLepBvq8FME9KFSJYmxnB9bGdGHCsj1yNkv7o8dgo3xo0LkTnkyugoW0T5P3iPxLMAfB6djDVZ8+IFkuaj5fcjhJFGSi5CwjwcGW0gs4U01nS7xDAk3LhpkCrqbjxiDKXYYv3FjNOGqTKOWMeP9mbzK+MYFxe3rTLOBGars1WT243EY23EgWL+tMMfMjoTSz4wzu+MJZbpw1fGhc/faEJM1CAIY/hgpxGfGTPJft7jjxg9m4S7AhuN5CY8gK+MK25bdMLIJMQh85inhdhxZM+/+8yViJWRzzLYHGuUwgn4WmR8ZtyGMKcAlZHiFR/WmOrJhkI++RsyI/vML/g15SPs0BIfKXu2y1KeXuC5M9IcJYsJI/0rYyHXQE5lKEELxa0Wb3b0rHLlexYU17xMwFpXlMNfipLTI4YjM8aNuqcQOcYZqulI1aW9kWco9AtjVbwyy3E5cZxYUpJRdiPH9PywwhoShcExWJhCmsCGEegReb0HtiTFyJWLutnZtHB1bde1yeJ+mnov6AzuEILrDbh9e/x4fO6P7IezmkOoVCqVSqVSqVQqlUqlUqlU/6Y/sxooCVUeRkIAAAAASUVORK5CYII=";
                        }

                        data.add(new GradeTourData(
                                body.getResponse().getBody().getItems().getItem().get(i).getTitle(),
                                body.getResponse().getBody().getItems().getItem().get(i).getAddr1(),
                                body.getResponse().getBody().getItems().getItem().get(i).getAddr2(),
                                body.getResponse().getBody().getItems().getItem().get(i).getAreacode(),
                                body.getResponse().getBody().getItems().getItem().get(i).getBooktour(),
                                body.getResponse().getBody().getItems().getItem().get(i).getCat1(),
                                body.getResponse().getBody().getItems().getItem().get(i).getCat2(),
                                body.getResponse().getBody().getItems().getItem().get(i).getCat3(),
                                body.getResponse().getBody().getItems().getItem().get(i).getContentid(),
                                body.getResponse().getBody().getItems().getItem().get(i).getContenttypeid(),
                                body.getResponse().getBody().getItems().getItem().get(i).getCreatedtime(),
                                con1,
                                con2,
                                body.getResponse().getBody().getItems().getItem().get(i).getMapx(),
                                body.getResponse().getBody().getItems().getItem().get(i).getMapy(),
                                body.getResponse().getBody().getItems().getItem().get(i).getMlevel(),
                                body.getResponse().getBody().getItems().getItem().get(i).getModifiedtime(),
                                body.getResponse().getBody().getItems().getItem().get(i).getReadcount(),
                                body.getResponse().getBody().getItems().getItem().get(i).getSigungucode(),
                                body.getResponse().getBody().getItems().getItem().get(i).getTel(),
                                body.getResponse().getBody().getItems().getItem().get(i).getZipcode()
                        ));
                    }

                    adapter = new firstpageAdapter(data, getActivity(), R.layout.fragment_menu1_first_cv, (OnItemClick) getActivity());
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("리스폰 코드", "" + response.code());
                }

            }

            @Override
            public void onFailure(Call<DataRES> call, Throwable t) {
                Log.d("error", "연결 안됨!");
                Log.d("error", t.getMessage());
                Log.d("error", t.toString());
            }
        });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

