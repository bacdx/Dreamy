package com.example.dreamy.UI.Activity.Interface;

import com.example.dreamy.UI.Activity.Model.History;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HistoryInterface {
    @GET("/lichsu")
    Call<List<History>> list(@Query("id_kh")String id_kh);
}
