package com.example.dreamy.UI.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Adapter.ProductAdapter;
import com.example.dreamy.UI.Activity.Model.Category;
import com.example.dreamy.UI.Activity.Model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    TextView textView ;
    List<Product> list;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    private List<Product> getListCung() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("1","Áo Thun Nữ Cơ Bản Cotton BCI Cao Cấp","mda1","Ao Nu","12/03/2022","4","Khong co gi","199.000","https://bizweb.dktcdn.net/100/438/408/products/ao-thun-nu-tsn6260-tr1-6.jpg?v=1690163494807"));
        list.add(new Product("2","Áo Sơ Mi Hàn Quốc Cổ Tròn","mda1","Ao Nu","12/03/2022","4","Khong co gi","199.000","https://down-vn.img.susercontent.com/file/4cf5035d00a9a9210bafa17a919946a7"));
        list.add(new Product("3","Áo LOVITO cổ vuông tay ngắn phồng ","mda1","Ao Nu","12/03/2022","4","Khong co gi","138.000","https://down-vn.img.susercontent.com/file/sg-11134201-7qver-lg1r2e9lari805"));
        list.add(new Product("4","Áo Khoét Vai Bo Len Tăm Siêu Xinhp","mda1","Ao Nu","12/03/2022","4","Khong co gi","172.000","https://down-vn.img.susercontent.com/file/sg-11134201-22100-d383yjn0m5iv1a"));
        list.add(new Product("3","Áo LOVITO cổ vuông tay ngắn phồng ","mda1","Ao Nu","12/03/2022","4","Khong co gi","138.000","https://down-vn.img.susercontent.com/file/sg-11134201-7qver-lg1r2e9lari805"));
        list.add(new Product("4","Áo Khoét Vai Bo Len Tăm Siêu Xinhp","mda1","Ao Nu","12/03/2022","4","Khong co gi","172.000","https://down-vn.img.susercontent.com/file/sg-11134201-22100-d383yjn0m5iv1a"));
        return list;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_product, container, false);
        spin_gia = view.findViewById(R.id.spinGia);
        spin_size = view.findViewById(R.id.spinSize);
        textView = view.findViewById(R.id.tv_name_category);
        recyclerView = view.findViewById(R.id.rcv_product);
        if(getArguments()!=null){
            String name = getArguments().getString("name");

            textView.setText(name);
        }
        // adapter spinner 1
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(spin1));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.style_spinner,arrayList);
        spin_gia.setAdapter(adapter);

        // adapter spinner 2
        ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList(spin2));
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(),R.layout.style_spinner,arrayList1);
        spin_size.setAdapter(adapter1);
        list = getListCung();
        productAdapter = new ProductAdapter(getContext(), list, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {

            }
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productAdapter);
        return view;
    }
}