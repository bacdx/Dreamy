package com.example.dreamy.InterfaceRetrofit;

import com.example.dreamy.Model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {
    @GET("loaisanphams")
    Call<List<Category>> getList();
}
