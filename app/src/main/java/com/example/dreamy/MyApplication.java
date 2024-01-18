package com.example.dreamy;

import android.app.Application;

import androidx.room.Room;

import com.example.dreamy.Database.SQL.AppDatabase;
import com.example.dreamy.Manager.CartManager;
import com.example.dreamy.Manager.ProDuctDetalManager;
import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Manager.TypeProductManage;

public class MyApplication extends Application {
    public static final int APP_ID = 553;
    public static final String MAC_KEY = "9phuAOYhan4urywHTh0ndEXiV3pKHr5Q";
//    public static final String URL_CREATE_ORDER = "https://sb-openapi.zalopay.vn/v2/create";
    public   String URL_CREATE_ORDER = "http://192.168.9.8:3000/api/order";
    public    TypeProductManage typeProductManage ;
    public    ProductManager productManager;
    public ProDuctDetalManager  proDuctDetalManager;
    public CartManager cartManager;

    @Override
    public void onCreate() {
        super.onCreate();
        typeProductManage=TypeProductManage.getInstance();
        productManager=ProductManager.getInstance(this);
        proDuctDetalManager=ProDuctDetalManager.getInstance();
        cartManager=CartManager.getInstance(this);
    }
}
