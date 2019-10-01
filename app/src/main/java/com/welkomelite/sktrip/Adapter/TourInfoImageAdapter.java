package com.welkomelite.sktrip.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.welkomelite.sktrip.R;

import java.util.ArrayList;

public class TourInfoImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<com.welkomelite.sktrip.Data.TourInfoImageData> TourInfoImageData;
    private Context context;
    private int layoutId;


    /**
     * 생성자
     */
    public TourInfoImageAdapter(ArrayList<com.welkomelite.sktrip.Data.TourInfoImageData> TourInfoImageData, Context context, int layoutId ) {
        this.TourInfoImageData = TourInfoImageData;
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
        return new TourImage(view);
    }

    /**
     * 넘겨받은 데이터를 화면에 출력
     * 재활용 되는 뷰가 호출하여 실행되는 메소드
     * 뷰홀더를 전달하고 어댑터는 position의 데이터를 결합
     */
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context)
                .load(TourInfoImageData.get(position).getOriginimgurl())
                .into(((TourImage) holder).TourInfoImage);
    }


    /**
     * 데이터 수 반환
     */
    @Override
    public int getItemCount() {
        return TourInfoImageData.size();
    }


    /**
     * GradeTour
     * 레이아웃 객체화
     */
    public static class TourImage extends RecyclerView.ViewHolder {
        private ImageView TourInfoImage;

        public TourImage(@NonNull View itemView) {
            super(itemView);
            TourInfoImage = itemView.findViewById(R.id.TourInfoImage);
        }
    }
}