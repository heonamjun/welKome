package com.example.sktrip.Fragment;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sktrip.R;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

//8c33961b-880a-4b3d-b140-fb83bea55a92 tmap key
public class Fragment_menu3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu3, container, false);

        LinearLayout linearLayoutTmap = (LinearLayout)view.findViewById(R.id.linearLayoutTmap);
        TMapView tmapView = new TMapView(getActivity());

        tmapView.setSKTMapApiKey("8c33961b-880a-4b3d-b140-fb83bea55a92");
        linearLayoutTmap.addView( tmapView );

        tmapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
            @Override
            public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                Toast.makeText(getActivity(),"눌럿어",Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }
        });



        return view;
    }
}
