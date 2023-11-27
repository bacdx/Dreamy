package com.example.dreamy.Interface;


import com.example.dreamy.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductsInterface {
    @GET("sanphams")
    Call<List<Product>> getList(@Query("ma_loai")String loaisp);
    @GET("sanphams")
    Call<List<Product>> getListByIDs(@Query("ma_loai")String loaisp);
    @GET("sanpham")
    Call<List<Product>> getListHome();
    @GET("sanphams")
    Call<List<Product>> getListbyMaNhaSanSuat(@Query("manhasanxuat")String s);
}
