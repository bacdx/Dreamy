package com.example.dreamy.Manager;

import com.example.dreamy.InterfaceRetrofit.CommentInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Model.Comment;
import com.example.dreamy.Model.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommentManager  {
    private static  CommentManager instance;

    Retrofit retrofit;
    CommentInterface commentInterface;
    private  PropertyChangeSupport propertyChangeSupport;
    private CommentManager() {
        retrofit=RetrofitService.getClient();
        commentInterface=retrofit.create(CommentInterface.class);
    propertyChangeSupport= new PropertyChangeSupport(this);
    }

    public static CommentManager getInstance() {
        if (instance==null){
            instance=new CommentManager();
        }
        return instance;
    }

    public void getCommentbySP(Product product ){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<ArrayList<Comment>> call= commentInterface.getComment(product.getId());
              try {
                  Response<ArrayList<Comment>> response =call.execute();
                  product.setComments(response.body());
                  propertyChangeSupport.firePropertyChange("commentByID",null,product.getComments());
              }
              catch (Exception exception){

              }
            }
        }).start();
    }

    public void postComment(Comment comment){
        Call<Void> call=commentInterface.postComment(comment);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                propertyChangeSupport.firePropertyChange("comment",null,null);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }


    public void addListener(PropertyChangeListener listener ){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void removeListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener( listener);
    }


}
