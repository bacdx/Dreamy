package com.example.dreamy.InterfaceRetrofit;


import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.ProductTop;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductsInterface {
    @GET("sanphams")
    Call<ArrayList<Product>> getList(@Query("ma_loai")String loaisp);
    @GET("sanpham")
    Call<ArrayList<Product>> getListByIDs(@Query("ma_loai")String loaisp);
    @GET("sanpham1")
    Call<Product> getProductByID(@Query("id")String id);

    @GET("sanpham2")
    Call<ArrayList<Product>> getProductByIDs(@Query("id") List<Integer> list);
    @GET("search")
    Call<ArrayList<Product>> getListSearch();
    @GET("sanpham")
    Call<ArrayList<Product>> getListHome(@Query("id")String id);
    @GET("sanphams")
    Call<ArrayList<Product>> getListbyMaNhaSanSuat(@Query("manhasanxuat")String s);
    @GET("top")
    Call<ArrayList<Product>> getTopList();
    @GET("favorite")
    Call<ArrayList<Product>> getListFavor(@Query("makhachhang") String makhachhang);
    @POST Call<Integer> changeFavorite (@Body String masp);
}
