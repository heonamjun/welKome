package com.example.sktrip.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sktrip.R;
import com.example.sktrip.TourApi.OnItemClick;

import java.util.ArrayList;


public class MypageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<com.example.sktrip.Data.GradeTourData> GradeTourData;
    private Context context;
    private int layoutId;
    private OnItemClick mCallback;


    /**
     * 생성자
     */
    public MypageAdapter(ArrayList<com.example.sktrip.Data.GradeTourData> GradeTourData, Context context, int layoutId, OnItemClick mCallback) {
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
        return new MypageHolder(view);
    }

    /**
     * 넘겨받은 데이터를 화면에 출력
     * 재활용 되는 뷰가 호출하여 실행되는 메소드
     * 뷰홀더를 전달하고 어댑터는 position의 데이터를 결합
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof MypageHolder){
            ((MypageHolder)holder).MyPageTourTitle.setText(GradeTourData.get(position).getTitle());
            ((MypageHolder)holder).MyPageTourAdd1.setText(GradeTourData.get(position).getAddr1());
            Glide.with(context).load(GradeTourData.get(position)
                    .getFirstimage())
                    .into(((MypageHolder) holder).MyPageImage);

            ((MypageHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
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
    public static class MypageHolder extends RecyclerView.ViewHolder {
        private ImageView MyPageImage;
        private TextView MyPageTourTitle;
        private TextView MyPageTourAdd1;


        public MypageHolder(@NonNull View itemView) {
            super(itemView);
            MyPageImage = itemView.findViewById(R.id.MyPageImage);
            MyPageTourTitle = itemView.findViewById(R.id.MyPageTourTitle);
            MyPageTourAdd1 = itemView.findViewById(R.id.MyPageTourAdd1);

        }
    }
}