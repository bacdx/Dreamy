package com.example.dreamy.Interface;

import com.example.dreamy.Model.ImageProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ImgProductInterface {
    @GET("img")
Call<List<ImageProduct>>getImgProduct(@Query("masanpham")String masanpham );
}
