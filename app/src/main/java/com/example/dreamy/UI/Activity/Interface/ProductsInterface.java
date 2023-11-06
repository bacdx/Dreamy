package com.example.dreamy.UI.Activity.Interface;


import com.example.dreamy.UI.Activity.Model.Color;
import com.example.dreamy.UI.Activity.Model.PhotoProducts;
import com.example.dreamy.UI.Activity.Model.Product;
import com.example.dreamy.UI.Activity.Model.Size;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductsInterface {
    @GET("sanphams")
    Call<List<Product>> getList(@Query("ma_loai")String loaisp);
    @GET("sanpham")
    Call<List<Product>> getListHome();
    @GET("colors")
    Call<List<Color>> getListColor(@Query("masp")String masp);
    @GET("sizes")
    Call<List<Size>> getListSize(@Query("masp") String masp);
    @GET("image")
    Call<List<PhotoProducts>> getListImage(@Query("masp") String masp);
}
