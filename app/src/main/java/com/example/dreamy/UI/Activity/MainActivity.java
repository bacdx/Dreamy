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
    ImageView image_cart;
    EditText ed_search;
    LinearLayout linearLayout;
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
        image_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        ed_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final  int DRAWABLE_LEFT = 0;
                if (motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() <= (ed_search.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        // Thực hiện công việc tìm kiếm ở đây
                        replaceFragment(new ChatboxFragment());
                        return true;
                    }
                }
                return false;
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}