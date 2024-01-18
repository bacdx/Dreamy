package com.example.dreamy.InterfaceRetrofit;

import com.example.dreamy.Model.Cart;
import com.example.dreamy.Model.ProductDetal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductDetalInterface {
    @GET("chitietsanpham")
    Call<ArrayList<ProductDetal>> getCall(@Query("masanpham")String masapham);
    @POST("chitietsanpham1")
    Call<ArrayList<ProductDetal>> getProductDetal(@Body ArrayList<Cart>list);
    @GET("chitietsanpham2")
    Call<ProductDetal> getProductDetal1(@Query("masp")int maSp,@Query("masize") int maSize,@Query("macolor") int maColor);
}
