package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Fragment.ProductFragment;
import com.example.dreamy.UI.Activity.Model.Category;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        replaceFragment(new ProductFragment());
    }
    private void replaceFragment(Fragment fragment){
        Category category = (Category) getIntent().getSerializableExtra("Category");
        Bundle bundle = new Bundle();
        String ten = category.getTen();
        Log.d("Name category", "replaceFragment: " +ten);
        fragment.setArguments(bundle);
        bundle.putString("name",ten);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_product,fragment);
        fragmentTransaction.commit();
    }
}