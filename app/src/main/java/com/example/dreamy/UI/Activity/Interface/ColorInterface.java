package com.example.dreamy.UI.Activity.Interface;

import com.example.dreamy.UI.Activity.Model.Color;
import com.example.dreamy.UI.Activity.Model.ImageProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ColorInterface {
    @GET("colors")
    Call<List<Color>> getColor(@Query("masp")String masp );
}
