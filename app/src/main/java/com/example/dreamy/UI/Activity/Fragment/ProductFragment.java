package com.example.dreamy.UI.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.dreamy.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
    Spinner spin_gia , spin_size  ;
    View view ;
    String[] spin1 ={"Giá giảm","Giá tăng","Dưới 500K","Trên 500K"};
    String[] spin2 ={"XS","S","M","L","XL","XXL"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_product, container, false);
        spin_gia = view.findViewById(R.id.spinGia);
        spin_size = view.findViewById(R.id.spinSize);


        // adapter spinner 1
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(spin1));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.style_spinner,arrayList);
        spin_gia.setAdapter(adapter);

        // adapter spinner 2
        ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList(spin2));
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(),R.layout.style_spinner,arrayList1);
        spin_size.setAdapter(adapter1);
        return view;
    }
}