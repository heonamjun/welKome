package com.example.sktrip.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sktrip.R;

//8c33961b-880a-4b3d-b140-fb83bea55a92 tmap key
public class Fragment_menu3 extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            Toast.makeText(getActivity(), "아놔 먼대", Toast.LENGTH_LONG).show();
        }

        View view = inflater.inflate(R.layout.fragment_menu3, container, false);



        return view;
    }

}
