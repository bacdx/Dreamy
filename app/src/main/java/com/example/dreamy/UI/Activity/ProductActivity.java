package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Adapter.ProductAdapter;
import com.example.dreamy.UI.Activity.Interface.CategoryInterface;
import com.example.dreamy.UI.Activity.Interface.ProductsInterface;
import com.example.dreamy.UI.Activity.Interface.RetrofitService;
import com.example.dreamy.UI.Activity.Model.Category;
import com.example.dreamy.UI.Activity.Model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductActivity extends AppCompatActivity {

    ImageView imgback;
    Spinner spin_gia , spin_size  ;
    View view ;
    String[] spin1 ={"Giá giảm","Giá tăng","Dưới 500K","Trên 500K"};
    String[] spin2 ={"XS","S","M","L","XL","XXL"};
    TextView textView ;
    List<Product> list;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    Category category ;
    Product product;
    Button btn_loc ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        spin_gia = findViewById(R.id.spinGia);
        spin_size = findViewById(R.id.spinSize);
        textView = findViewById(R.id.tv_name_category);
        recyclerView = findViewById(R.id.rcv_product);
        imgback= findViewById(R.id.img_back);
        btn_loc = findViewById(R.id.btn_loc);
        category = (Category) getIntent().getSerializableExtra("Category");
        category = (Category) getIntent().getSerializableExtra("Product");
        textView.setText(category.getTen());
        list = new ArrayList<>();
        Log.d("id_loaisp", "onCreate: "+ category.getId());
        back();
        sp();
        getList();
     //   locsp();

    }

    private void getList(){

            // api lay du lieu sp theo loai sp o day
            Retrofit retrofit = RetrofitService.getClient();
            ProductsInterface iProductsInterface = retrofit.create(ProductsInterface.class);
            Call<List<Product>> call = iProductsInterface.getList(category.getId());
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful()){
                        list.clear();
                        list.addAll(response.body());
                        Log.d("listsp", "onResponse: "+list);
                        Log.d("listspbody", "onResponse: "+ response.body());
                        productAdapter = new ProductAdapter(ProductActivity.this, list);
                        GridLayoutManager gridLayoutManager=new GridLayoutManager(ProductActivity.this,2);

                        recyclerView.setLayoutManager(gridLayoutManager);
                        recyclerView.setAdapter(productAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.e("RetrofitError", "onFailure: ", t);
                    Toast.makeText(ProductActivity.this, "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    }
    private void locsp(){
        btn_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedSize = spin_size.getSelectedItem().toString();
                ArrayList<Product> filteredProducts = new ArrayList<>();
                for (Product product: list){
                    if (product.getManhasanxuat().equals(selectedSize)){
                        filteredProducts.add(product);
                    }
                }
                productAdapter.updateData(filteredProducts);
            }
        });
    }
    private void back(){
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }
    private void sp(){
        // adapter spinner gia
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(spin1));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.style_spinner,arrayList);
        spin_gia.setAdapter(adapter);

        // adapter spinner size
        ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList(spin2));
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,R.layout.style_spinner,arrayList1);
        spin_size.setAdapter(adapter1);

    }



}