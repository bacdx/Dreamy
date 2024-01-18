package com.example.dreamy.UI.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Model.Category;
import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.ProductActivity;
import com.example.dreamy.UI.Adapter.ProductAdapter;
import com.example.dreamy.UI.Adapter.SlideHomeAdapter;
import com.example.dreamy.Model.PhotoSlide;
import com.example.dreamy.Model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment implements PropertyChangeListener {


    private  View view;
    private ViewPager vpr ;
    private CircleIndicator circleIndicator;
    private SlideHomeAdapter slideHomeAdapter ;
    private List<PhotoSlide> photoList ;
    private Timer timer;
    private ImageView imageView2,imageView;

    private ProductManager productManager=ProductManager.getInstance();
    RecyclerView rcv_home ;
    ArrayList<Product> list=new ArrayList<>();
    ProductAdapter productAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productManager.addListener(this);
        productManager.getRandomList("0");
        productAdapter = new ProductAdapter(getContext(), list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_home, container, false);
        vpr = (ViewPager) view.findViewById(R.id.vpr);
        imageView2=view.findViewById(R.id.flag2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category=new Category("1","Quần áo nam","","");
                Intent intent=new Intent(getContext(), ProductActivity.class);
                intent.putExtra("Category",category);
                getActivity().startActivity(intent);
            }
        });
        imageView=view.findViewById(R.id.flag);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category=new Category("2","Quần áo nữ","","");
                Intent intent=new Intent(getContext(), ProductActivity.class);
                intent.putExtra("Category",category);
                getActivity().startActivity(intent);
            }
        });
        rcv_home = view.findViewById(R.id.rcv_random);
        circleIndicator = (CircleIndicator) view.findViewById(R.id.circle_indicator);
        photoList = getListPhoto();
        slideHomeAdapter = new SlideHomeAdapter(getContext(), photoList);
        vpr.setAdapter(slideHomeAdapter);
        circleIndicator.setViewPager(vpr);
        slideHomeAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSlide();
        setRecyclerView();
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




    private void setRecyclerView(){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        rcv_home.setLayoutManager(gridLayoutManager);
        rcv_home.setAdapter(productAdapter);
    }



    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("allProduct")){
            if (propertyChangeEvent.getNewValue()!=null){
                list= (ArrayList<Product>) propertyChangeEvent.getNewValue();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        productAdapter.updateData((ArrayList<Product>) list);
                    }
                });
            }
        }

    }

    @Override
    public void onDestroy() {
        productManager.removeListener(this);
        super.onDestroy();
    }
}