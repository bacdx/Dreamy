package com.example.dreamy.UI.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPaySDK;


public class ConfirmOrderActivity extends AppCompatActivity {
    private String amount = "10000";
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "Demo SDK";
    private String merchantCode = "SCB01";
    private String merchantNameLabel = "Nhà cung cấp";
    private String description = "Thanh toán dịch vụ ABC";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZaloPaySDK.init(554, Environment.SANDBOX);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}
