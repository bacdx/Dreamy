package com.example.dreamy.UI.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.HoaDonAdapter;
import com.example.dreamy.databinding.FragmentDaHuyBinding;


public class DaHuyFragment extends Fragment {



    public DaHuyFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_da_huy,container,false);

            return view;

    }


}