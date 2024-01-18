package com.example.dreamy.Manager;

import com.example.dreamy.InterfaceRetrofit.IDanhGia;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Model.DanhGia;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DanhGiaManager  {
    private  PropertyChangeSupport propertyChangeSupport;
    private Retrofit retrofit;
    private IDanhGia iDanhGia;
    private static DanhGiaManager instance=new DanhGiaManager();

    private DanhGiaManager() {
        propertyChangeSupport=new PropertyChangeSupport(this);
        this.retrofit = RetrofitService.getClient();
        this.iDanhGia = retrofit.create(IDanhGia.class);
    }
    public static DanhGiaManager getInstance(){
        if (instance==null){
            instance=new DanhGiaManager();
        }
        return instance;
    }
    public void postDanhGia(DanhGia  danhGia){
        Call<Void> call=iDanhGia.postDanhGia(danhGia);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                propertyChangeSupport.firePropertyChange("danhgia",null,null);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    private void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }
}
