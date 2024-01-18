package com.example.dreamy.Manager;

import com.example.dreamy.InterfaceRetrofit.ProductDetalInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;

import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.ProductDetal;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProDuctDetalManager {

    private static ProDuctDetalManager instance;
    private ProductManager productManager=ProductManager.getInstance() ;
    private Retrofit retrofit ;
    private ProductDetalInterface productDetalInterface;
    private  ArrayList<ProductDetal> productDetals;
    private PropertyChangeSupport propertyChangeSupport;
    private ProDuctDetalManager(){
        productDetals=new ArrayList<>();
        retrofit= RetrofitService.getClient();
         productDetalInterface =retrofit.create(ProductDetalInterface.class);
        propertyChangeSupport=new PropertyChangeSupport(this);
    }

    public static ProDuctDetalManager getInstance() {
        if (instance==null){
            instance=new ProDuctDetalManager();
        }
        return instance;
    }

    public ArrayList<ProductDetal> getProductDetal(Product product){

    Call<ArrayList<ProductDetal>> productDetalCall=productDetalInterface.getCall(product.getId());
    productDetalCall.enqueue(new Callback<ArrayList<ProductDetal>>() {

        @Override
        public void onResponse(Call<ArrayList<ProductDetal>> call, Response<ArrayList<ProductDetal>> response) {
            product.setProductDetals(response.body());
         for (int i =0;i<response.body().size();i++){
             addProductDetal(response.body().get(i));

         }
            propertyChangeSupport.firePropertyChange("productDetal",null,null);
        }

        @Override
        public void onFailure(Call<ArrayList<ProductDetal>> call, Throwable t) {

        }
    });
    return productDetals;
    }

    public ProductDetal addProductDetal(ProductDetal productDetal){
        int check=-1;
        ProductDetal detal;
        for (int i=0;i<productDetals.size();i++){
            detal=productDetals.get(i);
            if(detal.getId()==productDetal.getId()){
                check=1;
               break;
            }
        }
        if (check!=1){
            productDetals.add(productDetal);
            return productDetal;
        } return productDetal;

    }

}
