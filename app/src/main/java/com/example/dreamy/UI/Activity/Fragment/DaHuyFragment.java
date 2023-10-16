package com.example.dreamy.UI.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DaHuyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaHuyFragment extends Fragment {


    public DaHuyFragment() {
        // Required empty public constructor
    }

    public static DaHuyFragment newInstance(String param1, String param2) {
        DaHuyFragment fragment = new DaHuyFragment();
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
        return inflater.inflate(R.layout.fragment_da_huy, container, false);
    }
}