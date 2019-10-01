package com.welkome.sktrip.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.welkome.sktrip.Adapter.LikeAdapter;

import com.welkome.sktrip.Data.GradeTourData;
import com.welkome.sktrip.R;
import com.welkome.sktrip.Retrofit.APIClient;
import com.welkome.sktrip.Retrofit.APIInterface;
import com.welkome.sktrip.Retrofit.LikeData;
import com.welkome.sktrip.TourApi.OnItemClick;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//8c33961b-880a-4b3d-b140-fb83bea55a92 tmap key
public class Fragment_menu3 extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LikeAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu3, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.LikeRecyclerView);
//        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.menu2_first_sr);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        final SharedPreferences preferences = getActivity().getSharedPreferences("auto", Context.MODE_PRIVATE);
        final String userID = preferences.getString("inputId",null);
        final APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<List<LikeData>> call = apiInterface.LikePageList(userID);
        call.enqueue(new Callback<List<LikeData>>() {
            @Override
            public void onResponse(Call<List<LikeData>> call, Response<List<LikeData>> response) {

                ArrayList<GradeTourData> data = new ArrayList<>(); //데이터 받아서 adapter 에 보내줄 data 생성
                for(int i = 0 ; i < response.body().size(); i++){
                    data.add(new GradeTourData(
                            response.body().get(i).getCheckbox(),
                            response.body().get(i).getId(),
                            response.body().get(i).getTitle(),
                            response.body().get(i).getContentid(),
                            response.body().get(i).getAddr1(),
                            ChageHttps(response.body().get(i).getFirstimage()),
                            response.body().get(i).getMapx(),
                            response.body().get(i).getMapy()


                    ));
                }
                adapter = new LikeAdapter(data, getActivity(), R.layout.fragment_menu3_like_cv, (OnItemClick) getActivity());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<LikeData>> call, Throwable t) {

            }
        });



        return view;
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

}
