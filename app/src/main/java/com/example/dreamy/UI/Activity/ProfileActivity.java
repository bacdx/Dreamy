package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dreamy.R;
import com.example.dreamy.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}