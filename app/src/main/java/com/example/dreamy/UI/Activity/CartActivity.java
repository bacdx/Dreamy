package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dreamy.Database.SQL.AppDatabase;
import com.example.dreamy.Database.SQL.Dao.ProductDao;
import com.example.dreamy.Database.SQL.model.Product;
import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Adapter.CartAdapter;
import com.example.dreamy.UI.Activity.Model.Cart;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartActivity extends AppCompatActivity {
//    private AppDatabase database= Room.databaseBuilder(this, AppDatabase.class,"dreamy_local").build();
    ProductDao productDao;
    CartAdapter adapter;
    List<Product> list = new ArrayList<>();
    RecyclerView rcv_cart ;
    double total = 0;

    ImageView imgBACK ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rcv_cart = findViewById(R.id.rcv_cart);
        imgBACK = findViewById(R.id.img_back);
//        productDao=database.productDao();

//        list=productDao.getAll();

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


        findViewById(R.id.tvLichSu).setOnClickListener(view -> {
            startActivity(new Intent(this, HistoryActivity.class));
        });
    }
    public void setListItem(){

    }
    public   List<String>  getFavorties(){
        SharedPreferences sharedPreferences;
        List<String> list1=new ArrayList<>();
        Type messageListType = new TypeToken<List<String>>() {
        }.getType();
        String json =new Gson().toJson(list1,messageListType);

        sharedPreferences= getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key),getApplicationContext().MODE_PRIVATE);
        String serializedList = sharedPreferences.getString("myIntegerList", "");
        if (serializedList.isEmpty()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("myIntegerList",json);
            return list1;
        } else {
            List<String> myList = new Gson().fromJson(serializedList, new TypeToken<List<Integer>>() {}.getType());
            return myList;
        }
    }


}