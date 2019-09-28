package com.example.sktrip.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sktrip.R;
import com.example.sktrip.Retrofit.APIClient;
import com.example.sktrip.Retrofit.APIInterface;
import com.example.sktrip.Retrofit.checkData;
import com.example.sktrip.TourApi.OnItemClick;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class firstpageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<com.example.sktrip.Data.GradeTourData> GradeTourData;

    private Context context;
    private int layoutId;
    private OnItemClick mCallback;


    /**
     * 생성자
     */
    public firstpageAdapter(ArrayList<com.example.sktrip.Data.GradeTourData> GradeTourData, Context context, int layoutId, OnItemClick mCallback) {
        this.GradeTourData = GradeTourData;
        this.context = context;
        this.layoutId = layoutId;
        this.mCallback = mCallback;
    }



    /**
     * 레이아웃을 만들고 Holder에 저장
     * 뷰 홀더를 생성하고 뷰를 붙여주는 부분
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new GradeTour(view);
    }

    /**
     * 넘겨받은 데이터를 화면에 출력
     * 재활용 되는 뷰가 호출하여 실행되는 메소드
     * 뷰홀더를 전달하고 어댑터는 position의 데이터를 결합
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final SharedPreferences preferences = context.getSharedPreferences("auto", Context.MODE_PRIVATE);

        /*
                GradeTour view 셋팅
         */
        if (holder instanceof GradeTour) {
            ((GradeTour) holder).TourTitle.setText(GradeTourData.get(position).getTitle());
            ((GradeTour) holder).TourAdd1.setText(GradeTourData.get(position).getAddr1());

            Glide.with(context).load(GradeTourData.get(position)
                    .getFirstimage2())
                    .into(((GradeTour) holder).TourImage);



            APIInterface apiInterface;
            apiInterface = APIClient.getClient().create(APIInterface.class);

            Call<List<checkData>> call = apiInterface.doCheckDataLoad(preferences.getString("inputId",null) , GradeTourData.get(position).getContentid());
            call.enqueue(new Callback<List<checkData>>() {
                @Override
                public void onResponse(Call<List<checkData>> call, Response<List<checkData>> response) {
                    int checkNumber = response.body().get(0).getCount();

                    if(checkNumber == 1){
                        ((GradeTour)holder).TourCheckBox.setChecked(true);


                    }

                }

                @Override
                public void onFailure(Call<List<checkData>> call, Throwable t) {

                }
            });





            // Check box click listener !
            ((GradeTour)holder).TourCheckBox.setOnClickListener(new CheckBox.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 1. contentid load
                    int contentid = GradeTourData.get(position).getContentid();

                    // 2. get check
                    int userCheck = 1;

                    // 3. get userID
                    String userID = preferences.getString("inputId",null);

                    // if check on  -- > DB save
                    if( ((GradeTour) holder).TourCheckBox.isChecked() ){

                        userCheck = 1;

                        // 4. Connection
                        APIInterface apiInterface;
                        apiInterface = APIClient.getClient().create(APIInterface.class);

                        Call<List<checkData>> call = apiInterface.doCheckDataInsert(userID, userCheck, contentid);

                        call.enqueue(new Callback<List<checkData>>() {
                            @Override
                            public void onResponse(Call<List<checkData>> call, Response<List<checkData>> response) {

                            }

                            @Override
                            public void onFailure(Call<List<checkData>> call, Throwable t) {

                            }
                        });



                    } else { // 체크박스 선택 해제1

                        userCheck = 0;

                        // 4. Connection
                        APIInterface apiInterface;
                        apiInterface = APIClient.getClient().create(APIInterface.class);

                        Call<List<checkData>> call = apiInterface.doCheckDataInsert(userID, userCheck, contentid);

                        call.enqueue(new Callback<List<checkData>>() {
                            @Override
                            public void onResponse(Call<List<checkData>> call, Response<List<checkData>> response) {

                            }

                            @Override
                            public void onFailure(Call<List<checkData>> call, Throwable t) {

                            }
                        });

                    }
                }
            });



            /**
             아이템 클릭 시 MainActivity 로 전송
             */
            ((GradeTour) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onClick(
                            GradeTourData.get(position).getTitle(),
                            GradeTourData.get(position).getAddr1(),
                            GradeTourData.get(position).getAddr2(),
                            GradeTourData.get(position).getAreacode(),
                            GradeTourData.get(position).getBooktour(),
                            GradeTourData.get(position).getCat1(),
                            GradeTourData.get(position).getCat2(),
                            GradeTourData.get(position).getCat3(),
                            GradeTourData.get(position).getContentid(),
                            GradeTourData.get(position).getContenttypeid(),
                            GradeTourData.get(position).getCreatedtime(),
                            GradeTourData.get(position).getFirstimage(),
                            GradeTourData.get(position).getFirstimage2(),
                            GradeTourData.get(position).getMapx(),
                            GradeTourData.get(position).getMapy(),
                            GradeTourData.get(position).getMlevel(),
                            GradeTourData.get(position).getModifiedtime(),
                            GradeTourData.get(position).getReadcount(),
                            GradeTourData.get(position).getSigungucode(),
                            GradeTourData.get(position).getTel(),
                            GradeTourData.get(position).getZipcode());

                }
            });
        }
    }


    /**
     * 데이터 수 반환
     */
    @Override
    public int getItemCount() {
        return GradeTourData.size();
    }


    /**
     * GradeTour
     * 레이아웃 객체화
     */
    public static class GradeTour extends RecyclerView.ViewHolder {
        private ImageView TourImage;
        private TextView TourTitle;
        private TextView TourAdd1;
        private CheckBox TourCheckBox;

        public GradeTour(@NonNull View itemView) {
            super(itemView);
            TourImage = itemView.findViewById(R.id.TourImage);
            TourTitle = itemView.findViewById(R.id.TourTitle);
            TourAdd1 = itemView.findViewById(R.id.TourAdd1);
            TourCheckBox = itemView.findViewById(R.id.checkBox);
        }
    }
}