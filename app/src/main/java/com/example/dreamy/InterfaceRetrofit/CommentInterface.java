package com.example.dreamy.InterfaceRetrofit;

import com.example.dreamy.Model.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommentInterface {
    @GET("comments")
    public Call<ArrayList<Comment>> getComment(@Query("masanpham")String maSp);
    @POST("postcomment")
    public Call<Void> postComment(@Body Comment comment);
}
