package com.example.dreamy.Interface;

import com.example.dreamy.Model.Color;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ColorInterface {
    @GET("colors")
    Call<List<Color>> getColor(@Query("masp")String masp );
}
