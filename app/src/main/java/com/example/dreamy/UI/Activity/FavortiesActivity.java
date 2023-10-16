package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dreamy.R;

public class FavortiesActivity extends AppCompatActivity {
    ImageView imgBACK ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorties);
        imgBACK = findViewById(R.id.img_back);
        imgBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             finish();
            }
        });
    }
}