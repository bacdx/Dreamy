package com.example.dreamy.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Adapter.ColorAdapter;
import com.example.dreamy.UI.Activity.Adapter.SizeAdapter;
import com.example.dreamy.UI.Activity.Adapter.SlideProductsAdapter;
import com.example.dreamy.UI.Activity.Fragment.ImageSlideFragment;
import com.example.dreamy.UI.Activity.Interface.ProductsInterface;
import com.example.dreamy.UI.Activity.Interface.RetrofitService;
import com.example.dreamy.UI.Activity.Model.Color;
import com.example.dreamy.UI.Activity.Model.PhotoProducts;
import com.example.dreamy.UI.Activity.Model.PhotoSlide;
import com.example.dreamy.UI.Activity.Model.Product;
import com.example.dreamy.UI.Activity.Model.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager mViewPager2;
    private RecyclerView mRecyclerViewColor , rcv_size;

    private CircleIndicator mCircleIndicator3;
    TextView tv_name , tv_price ;
    SizeAdapter sizeAdapter ;
    ColorAdapter colorAdapter;
    SlideProductsAdapter slideProductsAdapter;
    ImageView imgBack ;
    List<PhotoProducts> photoProductsList ;
    List<Size> sizeList ;
    List<Color> colorList;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        mViewPager2 = findViewById(R.id.view_pager2);
        mCircleIndicator3 = findViewById(R.id.circle_indicator3);
        mRecyclerViewColor = findViewById(R.id.rcvColor);
        imgBack = findViewById(R.id.img_back);
        rcv_size = findViewById(R.id.rcv_size);
        tv_name = findViewById(R.id.tvName);
        tv_price=findViewById(R.id.tvPrice);
        product = (Product) getIntent().getSerializableExtra("Products");
        back();
        tv_name.setText(product.getTen());
        tv_price.setText(product.getGia()+"đ");
        photoProductsList = new ArrayList<>();
        sizeList = new ArrayList<>();
        colorList = new ArrayList<>();
        getDataImg();

        getDataColor();
        getDataSize();

    }
    public void back(){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }
    static final  String BASE_URL="http://192.168.0.100:3000/api/";
    public void getDataSize(){
        Retrofit retrofit = RetrofitService.getClient(BASE_URL);
        ProductsInterface productsInterface = retrofit.create(ProductsInterface.class);
        Call<List<Size>> call = productsInterface.getListSize(product.getId());
        call.enqueue(new Callback<List<Size>>() {
            @Override
            public void onResponse(Call<List<Size>> call, Response<List<Size>> response) {
                if (response.isSuccessful()){
                    sizeList.clear();
                    sizeList.addAll(response.body());
                    sizeAdapter = new SizeAdapter(ProductDetailsActivity.this,sizeList);
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(ProductDetailsActivity.this,6);
                    rcv_size.setLayoutManager(gridLayoutManager);
                    rcv_size.setAdapter(sizeAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Size>> call, Throwable t) {
                Log.e("RetrofitError", "onFailure: ", t);
                Toast.makeText(ProductDetailsActivity.this, "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void getDataColor(){
        Retrofit retrofit = RetrofitService.getClient(BASE_URL);
        ProductsInterface productsInterface = retrofit.create(ProductsInterface.class);
        Call<List<Color>> call = productsInterface.getListColor(product.getId());
        call.enqueue(new Callback<List<Color>>() {
           @Override
           public void onResponse(Call<List<Color>> call, Response<List<Color>> response) {
               if (response.isSuccessful()){
                   colorList.clear();
                   colorList.addAll(response.body());
                   colorAdapter = new ColorAdapter(ProductDetailsActivity.this,colorList);
                   GridLayoutManager gridLayoutManager=new GridLayoutManager(ProductDetailsActivity.this,8);
                   mRecyclerViewColor.setLayoutManager(gridLayoutManager);
                   mRecyclerViewColor.setAdapter(colorAdapter);
               }

           }

           @Override
           public void onFailure(Call<List<Color>> call, Throwable t) {
               Log.e("RetrofitError", "onFailure: ", t);
               Toast.makeText(ProductDetailsActivity.this, "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
    }
    public void getDataImg(){
        Retrofit retrofit = RetrofitService.getClient(BASE_URL);
        ProductsInterface productsInterface = retrofit.create(ProductsInterface.class);
        Call<List<PhotoProducts>> call = productsInterface.getListImage(product.getId());
        call.enqueue(new Callback<List<PhotoProducts>>() {
            @Override
            public void onResponse(Call<List<PhotoProducts>> call, Response<List<PhotoProducts>> response) {
                if (response.isSuccessful()){
                    photoProductsList.clear();
                    photoProductsList.addAll(response.body());
                    slideProductsAdapter=new SlideProductsAdapter(ProductDetailsActivity.this,photoProductsList);
                    mViewPager2.setAdapter(slideProductsAdapter);
                    mCircleIndicator3.setViewPager(mViewPager2);
                    slideProductsAdapter.registerDataSetObserver(mCircleIndicator3.getDataSetObserver());
                }
            }

            @Override
            public void onFailure(Call<List<PhotoProducts>> call, Throwable t) {
                Log.e("RetrofitError", "onFailure: ", t);
                Toast.makeText(ProductDetailsActivity.this, "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }









}