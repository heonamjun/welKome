package com.example.sktrip.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sktrip.Activity.TourAll;
import com.example.sktrip.Adapter.DataAdapter;
import com.example.sktrip.Adapter.firstpageAdapter;
import com.example.sktrip.Data.GradeTourData;
import com.example.sktrip.Data.RecyclerItem;
import com.example.sktrip.MainActivity;
import com.example.sktrip.R;
import com.example.sktrip.Retrofit.APIClient;
import com.example.sktrip.Retrofit.APIInterface;
import com.example.sktrip.Retrofit.RecommendData;
import com.example.sktrip.TourApi.LoadTourApi;
import com.example.sktrip.TourApi.Model.DataRES;
import com.example.sktrip.TourApi.OnItemClick;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_menu1_first extends Fragment {

    ImageView RecommendImage;
    TextView RecommendTitle;
    TextView RecommendAddr1;
    CardView RecommendCardView;

    private int contentid = 0;

    private OnItemClick mCallback;
    private Button allbutton;


    public static Fragment_menu1_first newInstance() {
        Fragment_menu1_first fragment = new Fragment_menu1_first();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu1_main, container, false);

        RecommendImage = view.findViewById(R.id.RecommendImage);
        RecommendTitle = view.findViewById(R.id.RecommendTitle);
        RecommendAddr1 = view.findViewById(R.id.RecommendAddr1);
        RecommendCardView = view.findViewById(R.id.RecommendCardView);

        allbutton = (Button) view.findViewById(R.id.All_list);

        LinearLayout Recommendlayout = (LinearLayout) view.findViewById(R.id.RecommendLayout);
        LinearLayout starlayout = (LinearLayout) view.findViewById(R.id.starLayout);


        final APIInterface apiInterface;
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<List<RecommendData>> call = apiInterface.ReadRecommend();
        call.enqueue(new Callback<List<RecommendData>>() {
            @Override
            public void onResponse(Call<List<RecommendData>> call, Response<List<RecommendData>> response) {
                int num = (int) (Math.random() * response.body().size());

                Glide.with(getActivity())
                        .load(ChageHttps(response.body().get(num).getFirstimage()))
                        .into(RecommendImage);

                RecommendTitle.setText(response.body().get(num).getTitle());
                RecommendAddr1.setText(response.body().get(num).getAddr1());

                contentid = response.body().get(num).getContentid();
            }

            @Override
            public void onFailure(Call<List<RecommendData>> call, Throwable t) {

            }
        });


        Recommendlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Fragment fragment_menu2_tourInfo = new Fragment_menu2_TourInfo();

                Bundle args = new Bundle();
                if (contentid != 0)
                    args.putInt("contentid", contentid);

                fragment_menu2_tourInfo.setArguments(args);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment_menu2_tourInfo);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        starlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("잘눌려요");
                Toast.makeText(getContext(), "cardview", Toast.LENGTH_LONG).show();


            }
        });


        allbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("잘눌려요");
                Toast.makeText(getContext(), "클릭", Toast.LENGTH_LONG).show();

                final Fragment fragment_menu1_all_list = new Fragment_menu1_all_list();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment_menu1_all_list);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }


    /**
     * ChageHttps
     * http 를 https로 변환
     *
     * @param text
     * @return
     */
    public String ChageHttps(String text) {
        String trans = "";
        trans = text;
        if (trans != null) {
            trans = trans.replace("http://", "https://");
        } else {
            trans = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKMAAAB7CAMAAAAv38DwAAAAOVBMVEX4+Pezuav7+/qvtaba3Nbz9PLQ08zm5+Pp6ue4vrGss6PDyL3w8O7Bxbrs7erT1s7JzcPf4tylrZwuo5cQAAAEK0lEQVR4nO2a3XqkIAyGJQERFIXe/8VuEpwfZ+w+bdcuHOQ76EwVy9tAAgkOg0qlUqlUKpVKpVKpVCqVSqX6PcFykGvN8y6YAh6VVmgNdRRYNC9CM/UFub4hEmRoTXUQjCeMBktXhgwniAZzD4ywy8czRoPu1qAho89JdGpGUr07b81cHJYUz6bhiT1xbMS4hK8RCmVqggjz1xEJ0jYYbpjO/eRT/X/Es5Xlr4prA8azsI0m5TEnPLkVG6yMJ4w4l7rbcfbdndB3wIim3GM1DNsrZA+MmJxA7JzgO2Q0MswwTL46B0zYHSMvd7CmSMLMJn1x/PaMuAmVqdcweG6SOmPkkS5PV1aekrEnRtkpumeimYlCV4y0GkM5GHahNhl7YiQAmJ+nX2Rq2xMjp4BwdJFNGb/PyBkgPM8+EzknfJ0ObRl59h1DDQUjyKYjRsOpwPNgS0x32BUj8iK9Pn4VZtsZ41yX63otZm7hDhlte0Zjak5lUwhhLtIgd7anIAbJBWBwzskW8jXf6YHRmOcK1PtGvA9GHN2OAbC+Jd99MBrE7Fca67XM75lhJ4ycdxnyGWPOctcWjOV7dQrZrP1vLd9CNE2KUuf15U/N2Kbu/BKk/464taqSbmeFnRNADKURIVnSefsVTUPLgjh8TQ0JVao+9BNH+G3ncXsR2e1VRl+Kd/By3+23b5/39vIMxamy7kHI7boUMdSD3rnW8Lycpxt7vz9FM8mBTeBubyUpyWhuxwnW8DOhPFcm8cqzBupMDoGEESaDMSTq8X4udGdkpIU+hJGrVHsbwsKYQkRc6Q/QHp1rqfHjYkZjFtjtSOZYaGrNDzvcGBMhEU7a7cgLepBvq8FME9KFSJYmxnB9bGdGHCsj1yNkv7o8dgo3xo0LkTnkyugoW0T5P3iPxLMAfB6djDVZ8+IFkuaj5fcjhJFGSi5CwjwcGW0gs4U01nS7xDAk3LhpkCrqbjxiDKXYYv3FjNOGqTKOWMeP9mbzK+MYFxe3rTLOBGars1WT243EY23EgWL+tMMfMjoTSz4wzu+MJZbpw1fGhc/faEJM1CAIY/hgpxGfGTPJft7jjxg9m4S7AhuN5CY8gK+MK25bdMLIJMQh85inhdhxZM+/+8yViJWRzzLYHGuUwgn4WmR8ZtyGMKcAlZHiFR/WmOrJhkI++RsyI/vML/g15SPs0BIfKXu2y1KeXuC5M9IcJYsJI/0rYyHXQE5lKEELxa0Wb3b0rHLlexYU17xMwFpXlMNfipLTI4YjM8aNuqcQOcYZqulI1aW9kWco9AtjVbwyy3E5cZxYUpJRdiPH9PywwhoShcExWJhCmsCGEegReb0HtiTFyJWLutnZtHB1bde1yeJ+mnov6AzuEILrDbh9e/x4fO6P7IezmkOoVCqVSqVSqVQqlUqlUqlU/6Y/sxooCVUeRkIAAAAASUVORK5CYII=";
        }

        return trans;
    }
}
