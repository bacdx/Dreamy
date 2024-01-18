package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dreamy.InterfaceRetrofit.ProductsInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Manager.BillManager;
import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Manager.ProfileManager;
import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.User;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.SearchAdapter;
import com.example.dreamy.UI.Fragment.CategoryFragment;
import com.example.dreamy.UI.Fragment.ChatboxFragment;
import com.example.dreamy.UI.Fragment.FavoriteFragment;
import com.example.dreamy.UI.Fragment.HomeFragment;
import com.example.dreamy.UI.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ImageView image_cart,img_search;
    SearchView sv_search;
    LinearLayout linearLayout;

    ListView  listView;
    List<Product> list;
 SearchAdapter adapter;
    Retrofit retrofit;
    ProductManager productManager=ProductManager.getInstance();
    BottomNavigationView bottomNavigationView;
    private BillManager billManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        linearLayout = findViewById(R.id.linearLayout);
        sv_search = findViewById(R.id.ed_search);
        image_cart= findViewById(R.id.img_cart);

        billManager=BillManager.getInstant();
        billManager.getBills("1");
        bottomNavigationView = findViewById(R.id.bottom_nav);
        listView=findViewById(R.id.lv_search_activityMain);
        bottomNavigationView.setSelectedItemId(R.id.home);
        retrofit=RetrofitService.getClient();
        productManager.getProducts("0");
        productManager.getListFavorite();
        list=new ArrayList<>();
        adapter=new SearchAdapter((ArrayList<Product>) list,this);
        listView.setAdapter(adapter);
        replaceFragment(new HomeFragment());
        getListSearch();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
           if(id!=R.id.profile){
               linearLayout.setVisibility(View.VISIBLE);
           }

            if(id==R.id.home){

                replaceFragment(new HomeFragment());
            } else if (id==R.id.product) {

                replaceFragment(new CategoryFragment());
            }
            else if (id==R.id.favorites) {
                replaceFragment(new FavoriteFragment());
            }
//            else if (id==R.id.chatbox) {
//
//                replaceFragment(new ChatboxFragment());
//            }
            else if (id==R.id.profile) {
                replaceFragment(new ProfileFragment());
                linearLayout.setVisibility(View.GONE);
            }
            return true;
        });

        image_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        sreach();
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private  void sreach(){
        sv_search.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.setList((ArrayList<Product>) list);
            }
        });
        sv_search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.setList(new ArrayList<>());
                return false;
            }
        });


        sv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }


    private  void getListSearch(){
        ProductsInterface productsInterface=retrofit.create(ProductsInterface.class);
        Call <ArrayList<Product>> call=productsInterface.getListSearch();
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                list=response.body();

            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });

    }

}