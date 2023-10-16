package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Adapter.CartAdapter;
import com.example.dreamy.UI.Activity.Model.Cart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    CartAdapter adapter;
    List<Cart> list = new ArrayList<>();
    RecyclerView rcv_cart ;
    double total = 0;
    ImageView imgBACK ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rcv_cart = findViewById(R.id.rcv_cart);
        imgBACK = findViewById(R.id.img_back);
        Cart product = new Cart();
        product.setId("1");
        product.setTen("Tên sản phẩm");
        product.setSize(Arrays.asList("S", "M", "L"));
        product.setGia("2000");
        product.setImg("https://down-vn.img.susercontent.com/file/sg-11134201-22100-d383yjn0m5iv1a");
        list.add(product);
        adapter = new CartAdapter(this,list);
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       rcv_cart.setLayoutManager(linearLayoutManager);
        rcv_cart.setAdapter(adapter);
        Log.d("listt", "onCreate: "+list);
        imgBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}