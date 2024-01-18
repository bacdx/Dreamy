package com.example.dreamy.Controller;


import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Model.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommentController {
    private Retrofit retrofit;
    private boolean ReadyData=false;
    private ArrayList<Comment> list;

    public CommentController() {

    }
    public void getCommentByMaSp(String maSp){

    }

    public boolean isReadyData() {
        return ReadyData;
    }

    public void setReadyData(boolean readyData) {
        ReadyData = readyData;
    }

    public ArrayList<Comment> getList() {
        ArrayList<Comment> list1;
        return list;
    }

    private void setList(ArrayList<Comment> list) {
        this.list = list;
    }
    public void showMore(){

    }
}
