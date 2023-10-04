package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Fragment.CategoryFragment;
import com.example.dreamy.UI.Activity.Fragment.ChatboxFragment;
import com.example.dreamy.UI.Activity.Fragment.FavoriesFragment;
import com.example.dreamy.UI.Activity.Fragment.HomeFragment;
import com.example.dreamy.UI.Activity.Fragment.ProductFragment;
import com.example.dreamy.UI.Activity.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    EditText ed_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new HomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id==R.id.home){
                replaceFragment(new HomeFragment());
            } else if (id==R.id.product) {
                replaceFragment(new CategoryFragment());
            }
            else if (id==R.id.favorites) {
                replaceFragment(new FavoriesFragment());
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

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}