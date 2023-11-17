package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Fragment.CategoryFragment;
import com.example.dreamy.UI.Activity.Fragment.ChatboxFragment;
import com.example.dreamy.UI.Activity.Fragment.HomeFragment;
import com.example.dreamy.UI.Activity.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageView image_cart,img_search;
    EditText ed_search;
    public LinearLayout linearLayout;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linearLayout);
        replaceFragment(new HomeFragment());
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id==R.id.home){

                replaceFragment(new HomeFragment());
            } else if (id==R.id.product) {

                replaceFragment(new CategoryFragment());
            }
            else if (id==R.id.favorites) {
                startActivity(new Intent(MainActivity.this, FavortiesActivity.class));
            }
            else if (id==R.id.chatbox) {

                replaceFragment(new ChatboxFragment());
            }
            else if (id==R.id.profile) {

                replaceFragment(new ProfileFragment());
            }
            return true;
        });
       ed_search = findViewById(R.id.ed_search);
        image_cart= findViewById(R.id.img_cart);
        img_search = findViewById(R.id.img_search);
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
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String name_sreach = ed_search.getText().toString();
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                intent.putExtra("name_sreach",name_sreach);
                startActivity(intent);
            }
        });
    }
}