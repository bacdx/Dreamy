package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dreamy.InterfaceRetrofit.ProductsInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.ProductAdapter;
import com.example.dreamy.Model.Category;
import com.example.dreamy.Model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductActivity extends AppCompatActivity  {

    ImageView imgback;
    Spinner spin_gia , spin_size  ;
    View view ;
//    String[] spin1 ={"Giá giảm","Giá tăng","Dưới 500K","Trên 500K"};
//    String[] spin2 ={"XS","S","M","L","XL","XXL"};
    TextView textView ;
    ArrayList<Product> list=new ArrayList<>();
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    Category category ;
    Product product;
    Button btn_loc ;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        textView = findViewById(R.id.tv_name_category);
        recyclerView = findViewById(R.id.rcv_product);
        imgback= findViewById(R.id.img_back);
        category = (Category) getIntent().getSerializableExtra("Category");
        retrofit= RetrofitService.getClient();
        //
        back();
        textView.setText(category.getTen());
        getData(category.getId());
        list = new ArrayList<>();
        setAdapter();




     //   locsp();

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void back(){
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }



    private void setAdapter(){

        productAdapter = new ProductAdapter(ProductActivity.this, list);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(ProductActivity.this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productAdapter);

    }
    private void getData(String loaiSp){
        ProductsInterface productsInterface=retrofit.create(ProductsInterface.class);
        Call<ArrayList<Product>>call =productsInterface.getList(loaiSp);
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                setUIList(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }

    private void setUIList(ArrayList<Product> list) {
        productAdapter.updateData(list);
        recyclerView.setAdapter(productAdapter);
    }

}