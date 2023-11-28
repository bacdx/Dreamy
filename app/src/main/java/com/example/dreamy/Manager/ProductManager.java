package com.example.dreamy.Manager;

import com.example.dreamy.Interface.ProductsInterface;
import com.example.dreamy.Interface.RetrofitService;
import com.example.dreamy.Model.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductManager {
    private   static  ProductManager Instance;
    private PropertyChangeSupport pys= new PropertyChangeSupport(this);

    private  Retrofit retrofit;
   private ProductsInterface productsInterface;
    private List<Product> products=new ArrayList<Product>() ;

    private ProductManager() {
         retrofit= RetrofitService.getClient();
         productsInterface=retrofit.create(ProductsInterface.class);


    }
    public void addListener(PropertyChangeListener listener){
        pys.addPropertyChangeListener(listener);
    }
    public void removeListener(PropertyChangeListener listener){
        pys.removePropertyChangeListener(listener);
    }


    public static ProductManager getInstance() {
        if(Instance==null){
            Instance=new ProductManager();
        }
        return Instance;
    }


     public List<Product> getProducts() {
        Call<List<Product>> call=productsInterface.getListHome();
         call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                  products=response.body();
                  pys.firePropertyChange("allProduct",null,products);

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                 products=null;
            }

        });
return null;
    }

    public List<Product> getProductsByMaSP(String loaiSp) {
        Call<List<Product>> call=productsInterface.getList(loaiSp);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products=response.body();
                pys.firePropertyChange("productById",null,products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                products=null;
            }
        });
        return products;
    }




}
