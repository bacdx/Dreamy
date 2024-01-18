package com.example.dreamy.InterfaceRetrofit;

import com.example.dreamy.Model.DanhGia;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IDanhGia {
    @POST ("danhgia")
    public Call<Void> postDanhGia(@Body DanhGia danhGia);
}
