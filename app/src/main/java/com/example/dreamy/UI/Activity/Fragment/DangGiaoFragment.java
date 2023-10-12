package com.example.dreamy.UI.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DangGiaoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DangGiaoFragment extends Fragment {

    public DangGiaoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DangGiaoFragment newInstance() {
        DangGiaoFragment fragment = new DangGiaoFragment();
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
        return inflater.inflate(R.layout.fragment_dang_giao, container, false);
    }
}