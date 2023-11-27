package com.example.dreamy.Interface;

import com.example.dreamy.Model.Color;
import com.example.dreamy.Model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CreateOrder {
    @POST("order")
    Call<Order.OdrerResresult> postOrder(@Body Order order);
}
