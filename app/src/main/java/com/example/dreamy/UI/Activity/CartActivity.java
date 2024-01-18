package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamy.Database.SQL.Dao.CartDAO;
import com.example.dreamy.Database.SQL.Dao.FavoriteDAO;

import com.example.dreamy.InterfaceRetrofit.ProductsInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Manager.CartManager;
import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Model.Cart;
import com.example.dreamy.Model.OderDetal;
import com.example.dreamy.Model.Order;
import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.ProductDetal;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.CartAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity implements PropertyChangeListener, CartAdapter.ChangePrice {


    CartAdapter adapter;
    List<Product> list = new ArrayList<>();
    ArrayList<Cart> carts = new ArrayList<>();
    private CartManager cartManager;
    private AppCompatButton btnMua;
    private TextView tvTong;
    RecyclerView rcv_cart;

    double total = 0;


    ImageView imgBACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rcv_cart = findViewById(R.id.rcv_cart);
        imgBACK = findViewById(R.id.img_back);
        btnMua=findViewById(R.id.btnMua);
        tvTong=findViewById(R.id.tv_tongTien);

        cartManager = CartManager.getInstance(this);
        cartManager.setListen(this);
        cartManager.getListChiTiet();
        cartManager.getListProduct();


        adapter = new CartAdapter(this, carts,this);
        adapter.setFragmentManager(getSupportFragmentManager());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_cart.setLayoutManager(linearLayoutManager);
        rcv_cart.setAdapter(adapter);
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thanhtoan();
            }
        });
        imgBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        findViewById(R.id.tvLichSu).setOnClickListener(view -> {
            startActivity(new Intent(this, HistoryActivity.class));
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cartManager.disListen(this);
    }

    public void setListItem() {

    }


    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("cart")) {
            carts = (ArrayList<Cart>) propertyChangeEvent.getNewValue();


        }
        if (propertyChangeEvent.getPropertyName().equals("cart1")) {
            carts = (ArrayList<Cart>) propertyChangeEvent.getNewValue();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.setCartList(carts);
                }
            });
            setTong();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void thanhtoan() {
        ArrayList<OderDetal> oderDetals = new ArrayList<>();
        for (int i = 0; i < carts.size(); i++) {
            Cart cart = carts.get(i);
            if (cart.getTrangThai() == 1) {
                Product product = cart.getProduct();
                ProductDetal detal = cart.getProductDetal();
                OderDetal oderDetal = new OderDetal();
                oderDetal.setSoLuong(String.valueOf(cart.getSoLuong()));
                oderDetal.setMasize(String.valueOf(detal.getMaSize()));
                oderDetal.setDonGia(product.getGia()*cart.getSoLuong());
                oderDetal.setMamau(String.valueOf(detal.getMaMau()));
                oderDetal.setUriImg(product.getImg());
                oderDetal.setTenProduct(product.getTen());
                oderDetal.setId(product.getId());
                oderDetal.setMaCTSanPham(String.valueOf(detal.getId()));
                oderDetal.setMaSanPham(Integer.valueOf(product.getId()));
                oderDetals.add(oderDetal);

            }
        }
        if (oderDetals != null && oderDetals.size() > 0) {
            Intent intent = new Intent(CartActivity.this, PayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("oderDetals", oderDetals);
            intent.putExtra("data", bundle);
            startActivity(intent);
        }

    }

    @Override
    public void change() {
        setTong();
    }
    private void setTong(){
        DecimalFormat df = new DecimalFormat("#.##");
        float tong=(float) 0;
        for (int i=0;i<carts.size();i++){
            Cart cart=carts.get(i);
            Product product=cart.getProduct();
            tong+=cart.getSoLuong()*product.getGia();
        }
        tvTong.setText(String.valueOf(df.format(tong)));
    }
}