package com.example.dreamy.UI.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Adapter.SlideHomeAdapter;
import com.example.dreamy.UI.Activity.Model.PhotoSlide;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  View view;
    private ViewPager vpr ;
    private CircleIndicator circleIndicator;
    private SlideHomeAdapter slideHomeAdapter ;
    private List<PhotoSlide> photoList ;
    private Timer timer;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home, container, false);
        vpr = (ViewPager) view.findViewById(R.id.vpr);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.circle_indicator);
        photoList = getListPhoto();
        slideHomeAdapter = new SlideHomeAdapter(getContext(), photoList);
        vpr.setAdapter(slideHomeAdapter);
        circleIndicator.setViewPager(vpr);
        slideHomeAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlide();
        return  view ;
    }
    private List<PhotoSlide> getListPhoto() {
        List<PhotoSlide> list = new ArrayList<>();
        list.add(new PhotoSlide(R.drawable.slide_img1));
        list.add(new PhotoSlide(R.drawable.slide_img2));
        list.add(new PhotoSlide(R.drawable.slide_img3));
        list.add(new PhotoSlide(R.drawable.slide_img4));
        return list;
    }
    private void autoSlide() {
        if (photoList == null || photoList.isEmpty() || vpr == null) {
            return;
        }
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int curentItem = vpr.getCurrentItem();
                        int toltalItem = photoList.size() - 1;
                        if (curentItem < toltalItem) {
                            curentItem++;
                            vpr.setCurrentItem(curentItem);
                        } else {
                            vpr.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 3000, 4000);
    }
}