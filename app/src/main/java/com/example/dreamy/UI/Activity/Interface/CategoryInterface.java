package com.example.dreamy.UI.Activity.Interface;

import com.example.dreamy.UI.Activity.Model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {
    @GET("list")
    Call<List<Category>> getList();
}
