package com.example.dreamy.UI.Activity.Interface;

import com.example.dreamy.UI.Activity.Model.ImageProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ImgProductInterface {
    @GET("img")
Call<List<ImageProduct>>getImgProduct(@Query("masanpham")String masanpham );
}
