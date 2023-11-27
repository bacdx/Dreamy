package com.example.dreamy.Interface;

import com.example.dreamy.Model.Size;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SizeInterface {
    @GET("sizes")
    Call<List<Size>> getSizes(@Query("masp")String masanpham );
}
