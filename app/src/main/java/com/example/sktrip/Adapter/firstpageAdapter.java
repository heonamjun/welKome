package com.example.sktrip.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sktrip.Data.Footer;
import com.example.sktrip.Data.GradeTourData;
import com.example.sktrip.Data.RecyclerItem;
import com.example.sktrip.Data.picline;
import com.example.sktrip.TourApi.OnItemClick;
import com.example.sktrip.R;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class firstpageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<GradeTourData>GradeTourData;
    List<RecyclerItem> recyclerViewItems;

    private Context context;
    private int layoutId;
    private OnItemClick mCallback;
    private ArrayList<GradeTourData> CheckData = new ArrayList<>();
    static HashMap pathInfo = new HashMap();
    private static final int GradeTour_ITEM = 0;
    //Footer Item Type
    private static final int FOOTER_ITEM = 1;
    //Food Item Type


/*
        생성자
     */

    public firstpageAdapter(List<RecyclerItem> recyclerViewItems, Context context) {
        this.recyclerViewItems = recyclerViewItems;
        this.context = context;
    }

    public firstpageAdapter(ArrayList<GradeTourData> GradeTourData, Context context, int layoutId, OnItemClick mCallback) {
        this.GradeTourData = GradeTourData;
        this.context = context;
        this.layoutId = layoutId;
        this.mCallback = mCallback;
    }


/*
        레이아웃을 만들고 Holder에 저장
        뷰 홀더를 생성하고 뷰를 붙여주는 부분
     */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //  View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view ;
        //    return new GradeTour(view);

        if (viewType == GradeTour_ITEM) {
            view= inflater.inflate(R.layout.custom_header, parent, false);
            return new GradeTourHolder(view);
        } else if (viewType == FOOTER_ITEM) {
            view= inflater.inflate(R.layout.custom_footer, parent, false);
            return new FooterHolder(view);
        }
        return null;
    }


/*
        넘겨받은 데이터를 화면에 출력
        재활용 되는 뷰가 호출하여 실행되는 메소드
        뷰홀더를 전달하고 어댑터는 position의 데이터를 결합
     */

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        RecyclerItem recyclerViewItem = recyclerViewItems.get(position);


/*
                GradeTour view 셋팅
         */


        if (holder instanceof GradeTourHolder) {
            ((GradeTourHolder) holder).TourTitle.setText(GradeTourData.get(position).getTitle());
            ((GradeTourHolder) holder).TourAdd1.setText(GradeTourData.get(position).getAddr1());
            Glide.with(context).load(GradeTourData.get(position)
                    .getFirstimage())
                    .into(((GradeTourHolder) holder).TourImage);

            ((GradeTourHolder) holder).Check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CheckBox Check = (CheckBox) v;
                    if (Check.isChecked()) {
                        mCallback.onclick1(
                                GradeTourData.get(position).getTitle(),
                                GradeTourData.get(position).getMapx(),
                                GradeTourData.get(position).getMapy()
                        );
                    } else if (!Check.isChecked()) {
                        CheckData.remove(GradeTourData.get(position).getTitle());
                    }
                }
            });
            ((GradeTourHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
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
                            GradeTourData.get(position).getTel());
                }
            });
        } else if (holder instanceof FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) holder;
            Footer footer = (Footer) recyclerViewItem;
            //set data
            footerHolder.texViewQuote.setText(footer.getQuote());
            footerHolder.textViewAuthor.setText(footer.getAuthor());
            Glide.with(context).load(footer.getImageUrl()).into(footerHolder.imageViewFooter);


        }

    }

    @Override
    public int getItemViewType(int position) {
        //here we can set view type
        RecyclerItem recyclerViewItem = recyclerViewItems.get(position);
        //if its header then return header picline
        if (recyclerViewItem instanceof picline)
            return GradeTour_ITEM;
            //if its Footer then return Footer picline
        else if (recyclerViewItem instanceof Footer)
            return FOOTER_ITEM;
            //if its FoodItem then return Food i
        else
            return super.getItemViewType(position);

    }




/*
       데이터 수 반환
     */

    @Override
    public int getItemCount() {
        return GradeTourData.size();
    }



/*
       레이아웃 객체화
     */

    private class GradeTourHolder extends RecyclerView.ViewHolder {
        private ImageView TourImage;
        private TextView TourTitle;
        private TextView TourAdd1;
        private RatingBar TourRating;
        private CheckBox Check;

    public GradeTourHolder (@NonNull View itemView) {
        super(itemView);
        TourImage = itemView.findViewById(R.id.TourImage);
        TourTitle = itemView.findViewById(R.id.TourTitle);
        TourAdd1 = itemView.findViewById(R.id.TourAdd1);
        TourRating = itemView.findViewById(R.id.TourRating);
        Check = itemView.findViewById(R.id.checkBox);


    }
}

private class FooterHolder extends RecyclerView.ViewHolder {
    TextView texViewQuote, textViewAuthor;
    ImageView imageViewFooter;

    FooterHolder(View itemView) {
        super(itemView);
        texViewQuote = itemView.findViewById(R.id.texViewQuote);
        textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
        imageViewFooter = itemView.findViewById(R.id.imageViewFooter);
    }
}


}