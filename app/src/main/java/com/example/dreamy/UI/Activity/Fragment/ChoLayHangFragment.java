package com.example.dreamy.UI.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamy.R;


public class ChoLayHangFragment extends Fragment {
    public ChoLayHangFragment() {
        // Required empty public constructor
    }


    public static ChoLayHangFragment newInstance(String param1, String param2) {
        ChoLayHangFragment fragment = new ChoLayHangFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cho_lay_hang, container, false);
    }
}