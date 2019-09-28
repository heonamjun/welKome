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
import com.example.sktrip.Data.GradeTourData;
import com.example.sktrip.R;

import java.util.ArrayList;

public class LikeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<com.example.sktrip.Data.GradeTourData> GradeTourData;
    private Context context;
    private int layoutId;


    /**
     * 생성자
     */
    public LikeAdapter(ArrayList<com.example.sktrip.Data.GradeTourData> GradeTourData, Context context, int layoutId) {
        this.GradeTourData = GradeTourData;
        this.context = context;
        this.layoutId = layoutId;

    }



    /**
     * 레이아웃을 만들고 Holder에 저장
     * 뷰 홀더를 생성하고 뷰를 붙여주는 부분
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new LikeHolder(view);
    }

    /**
     * 넘겨받은 데이터를 화면에 출력
     * 재활용 되는 뷰가 호출하여 실행되는 메소드
     * 뷰홀더를 전달하고 어댑터는 position의 데이터를 결합
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof LikeHolder){
            ((LikeHolder)holder).LikeTourTitle.setText(GradeTourData.get(position).getTitle());
            ((LikeHolder)holder).LikeTourAdd1.setText(GradeTourData.get(position).getAddr1());
            Glide.with(context).load(GradeTourData.get(position)
                    .getFirstimage())
                    .into(((LikeHolder) holder).LikeImage);
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
    public static class LikeHolder extends RecyclerView.ViewHolder {
        private ImageView LikeImage;
        private TextView LikeTourTitle;
        private TextView LikeTourAdd1;


        public LikeHolder(@NonNull View itemView) {
            super(itemView);
            LikeImage = itemView.findViewById(R.id.LikeImage);
            LikeTourTitle = itemView.findViewById(R.id.LikeTourTitle);
            LikeTourAdd1 = itemView.findViewById(R.id.LikeTourAdd1);

        }
    }
}