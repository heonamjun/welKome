package com.welkome.sktrip.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.welkome.sktrip.TourApi.OnItemClick;
import com.welkome.sktrip.R;

import java.util.ArrayList;

public class Data2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<com.welkome.sktrip.Data.GradeTourData> GradeTourData;

    private Context context;
    private int layoutId;
    private OnItemClick mCallback;




    /**
     * 생성자
     */
    public Data2Adapter(ArrayList<com.welkome.sktrip.Data.GradeTourData> GradeTourData, Context context, int layoutId, OnItemClick mCallback) {
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
        return new GradeTour2(view);
    }

    /**
     * 넘겨받은 데이터를 화면에 출력
     * 재활용 되는 뷰가 호출하여 실행되는 메소드
     * 뷰홀더를 전달하고 어댑터는 position의 데이터를 결합
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        /*
                GradeTour view 셋팅
         */
        if (holder instanceof GradeTour2) {
            ((GradeTour2) holder).TourTitle.setText(GradeTourData.get(position).getTitle());
            ((GradeTour2) holder).TourAdd1.setText(GradeTourData.get(position).getAddr1());


            Glide.with(context).load(GradeTourData.get(position)
                    .getFirstimage2())
                    .into(((GradeTour2) holder).TourImage);


            /**
             아이템 클릭 시 MainActivity 로 전송
             */
            ((GradeTour2) holder).itemView.setOnClickListener(new View.OnClickListener() {
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
    public static class GradeTour2 extends RecyclerView.ViewHolder {
        private ImageView TourImage;
        private TextView TourTitle;
        private TextView TourAdd1;


        public GradeTour2(@NonNull View itemView) {
            super(itemView);
            TourImage = itemView.findViewById(R.id.TourImage2);
            TourTitle = itemView.findViewById(R.id.TourTitle2);
            TourAdd1 = itemView.findViewById(R.id.TourAdd12);

        }
    }
}
