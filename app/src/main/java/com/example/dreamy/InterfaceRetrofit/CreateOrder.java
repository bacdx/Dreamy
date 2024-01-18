package com.example.dreamy.InterfaceRetrofit;

import com.example.dreamy.Model.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CreateOrder {
    @POST("order")
    Call<Order.OdrerResresult> postOrder(@Body Order order);
}
