package com.example.dreamy.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamy.R;

public class LoadingActivivy extends AppCompatActivity implements Runnable {
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Thread( this::run).start();

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
