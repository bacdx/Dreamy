package com.example.dreamy.InterfaceRetrofit;


import com.example.dreamy.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILogin {
    @POST("login")
    Call<User> logIn(@Body User user);
}
