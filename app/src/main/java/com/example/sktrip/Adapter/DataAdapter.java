package com.example.sktrip.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sktrip.R;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int layoutId;
    private List<String> items;

    public DataAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        String item = items.get(position);
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).textView.setText(item);
            ((ItemViewHolder) holder).ratingBar.setStepSize((float)0.5);
            ((ItemViewHolder) holder).ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    String grade = String.valueOf(rating);
                    ((ItemViewHolder) holder).textView2.setText(grade);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void addItem(List<String> item) {
        if (items == null)
            items = new ArrayList<>();
        items.addAll(item);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView textView2;
        private RatingBar ratingBar;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.TourTitle);
            textView2 = itemView.findViewById(R.id.TourGrade);
            ratingBar = itemView.findViewById(R.id.TourRating);
        }
    }

}