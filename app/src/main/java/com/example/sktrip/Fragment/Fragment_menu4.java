package com.example.sktrip.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sktrip.R;
import com.example.sktrip.TourApiService;
import com.example.sktrip.model.DataRES;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_menu4 extends Fragment {
    private TourApiService service;
    public static final String baseUrl = "https://api.visitkorea.or.kr/openapi/service/rest/KorService/"; //국문 API 서비스

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu4,container,false);


        final TextView textView = (TextView) view.findViewById(R.id.testcontent);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(TourApiService.class); //api 초기화


        Call<DataRES> client = service.getareaCode(1,10,1);
        client.enqueue(new Callback<DataRES>() {
            @Override
            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
                DataRES contents = response.body();
                String text="";
                Log.d("error", contents.getResponse().getHeader().getResultMsg());

                for(int i = 0; i < contents.getResponse().getBody().getItems().getItem().size();i++){
                    text += "name : " + contents.getResponse().getBody().getItems().getItem().get(i).getName() + "\n";
                }

                Log.d("content : ",text);
                textView.setText(text);
            }

            @Override
            public void onFailure(Call<DataRES> call, Throwable t) {
                Log.d("error", "연결 안됨!");
                Log.d("error", t.getMessage());
                Log.d("error", t.toString());
            }
        });


//        //비동기식 형식 선언과 동시에 처리
//        service.getcategoryCode(12,10,1).enqueue(new Callback<DataRES>() {
//            @Override
//            public void onResponse(Call<DataRES> call, Response<DataRES> response) {
//                if(response.isSuccessful()){
//                    DataRES contents = response.body();
//                    String con = "";
//                    Log.d("data", contents.getResponse().getHeader().getResultMsg() + "");
//                    textView.setText(con);
//                }else{
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DataRES> call, Throwable t) {
//                Log.d("error", "연결 안됨!!!!!");
//                Log.d("error",t.getMessage());
//                Log.d("error",t.toString());
//            }
//        });


        return view;
    }
}

