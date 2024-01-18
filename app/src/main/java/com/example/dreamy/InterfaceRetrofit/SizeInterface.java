package com.example.dreamy.InterfaceRetrofit;

import com.example.dreamy.Model.Size;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SizeInterface {
    @GET("sizes")
    Call<ArrayList<Size>> getSizes(@Query("masp")String masanpham );
}
