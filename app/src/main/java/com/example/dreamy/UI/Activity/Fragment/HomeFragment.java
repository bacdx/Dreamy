package com.example.dreamy.UI.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Adapter.ProductAdapter;
import com.example.dreamy.UI.Activity.Adapter.SlideHomeAdapter;
import com.example.dreamy.UI.Activity.Interface.ProductsInterface;
import com.example.dreamy.UI.Activity.Interface.RetrofitService;
import com.example.dreamy.UI.Activity.Model.PhotoSlide;
import com.example.dreamy.UI.Activity.Model.Product;
import com.example.dreamy.UI.Activity.ProductActivity;
import com.example.dreamy.UI.Activity.ProductDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    RecyclerView rcv_home ;
    List<Product> list;
    ProductAdapter productAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home, container, false);
        vpr = (ViewPager) view.findViewById(R.id.vpr);
        rcv_home = view.findViewById(R.id.rcv_random);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.circle_indicator);
        photoList = getListPhoto();
        slideHomeAdapter = new SlideHomeAdapter(getContext(), photoList);
        vpr.setAdapter(slideHomeAdapter);
        circleIndicator.setViewPager(vpr);
        slideHomeAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlide();
        list= new ArrayList<Product>();
        listRandom();
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
    static final  String BASE_URL="http://192.168.0.100:3000/api/";
    private void listRandom(){
        Retrofit retrofit = RetrofitService.getClient(BASE_URL);
        ProductsInterface iProductsInterface = retrofit.create(ProductsInterface.class);
        Call<List<Product>> call = iProductsInterface.getListHome();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    list.clear();
                    list.addAll(response.body());
                    Log.d("listsp", "onResponse: "+list);
                    Log.d("listspbody", "onResponse: "+ response.body());
                    productAdapter = new ProductAdapter(getContext(), list, new ProductAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Product product) {
                            Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                            intent.putExtra("Products",product);
                            startActivity(intent);
                        }
                    });
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
                    rcv_home.setLayoutManager(gridLayoutManager);
                    rcv_home.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("RetrofitError", "onFailure: ", t);
                Toast.makeText(getContext(), "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}