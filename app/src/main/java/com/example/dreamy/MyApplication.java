package com.example.dreamy;

import android.app.Application;

import androidx.room.Room;

import com.example.dreamy.Database.SQL.AppDatabase;

public class MyApplication extends Application {
    public static final int APP_ID = 2553;
    public static final String MAC_KEY = "PcY4iZIKFCIdgZvA6ueMcMHHUbRLYjPL";
    public static final String URL_CREATE_ORDER = "https://sb-openapi.zalopay.vn/v2/create";

}
