package com.example.dreamy.InterfaceRetrofit;



import com.example.dreamy.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface IUser {
    @POST("signin")
    Call<User>signIn(@Body User user);
    @POST("update")
    Call<User>updateUser(@Body User user);

}
