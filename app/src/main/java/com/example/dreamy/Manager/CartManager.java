package com.example.dreamy.Manager;

import android.content.Context;
import android.util.Log;


import com.example.dreamy.Database.SQL.Dao.CartDAO;

import com.example.dreamy.InterfaceRetrofit.ProductDetalInterface;
import com.example.dreamy.InterfaceRetrofit.ProductsInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Model.Cart;

import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.ProductDetal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartManager {
    private static CartManager instance;
    private ProductManager productManager ;
    private ProDuctDetalManager proDuctDetalManager;

    private ArrayList<Cart> carts;
    private  Retrofit retrofit;
   private ProductDetalInterface productDetalInterface;
    private ProductsInterface productsInterface;

    private Context context;
    private CartDAO cartDAO;
    private PropertyChangeSupport propertyChangeSupport;


    private CartManager(Context context) {
        this.context=context;
        propertyChangeSupport=new PropertyChangeSupport(this);
        cartDAO=new CartDAO(context);
        carts =new ArrayList<>();
        proDuctDetalManager=ProDuctDetalManager.getInstance();
        productManager=ProductManager.getInstance(context);
        retrofit= RetrofitService.getClient();
        productDetalInterface =retrofit.create(ProductDetalInterface.class);
        productsInterface=retrofit.create(ProductsInterface.class);

    }

    public static CartManager getInstance(Context context) {
        if (instance==null){
             instance=new CartManager(context);
        }
        return instance;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }

    public void setProductToListCart(float price){
        int check =-1;
        Cart cart;

    }
    public boolean addToCart(ProductDetal productDetal,int soluong,int maSize,int maSoluong){
        Log.e("log1", productDetal.toString() );
        if (cartDAO.insert(productDetal.getId(),productDetal.getMaSanPham(),soluong,maSize,maSoluong)>0){
            return true;
        }
        return false;



    }
    public void  getListChiTiet(){
        carts=cartDAO.select();

        if(carts!=null&&carts.size()>0) {
            Call<ArrayList<ProductDetal>> call = productDetalInterface.getProductDetal(carts);
//        productManager.getProducts(ca);
            call.enqueue(new Callback<ArrayList<ProductDetal>>() {
                @Override
                public void onResponse(Call<ArrayList<ProductDetal>> call, Response<ArrayList<ProductDetal>> response) {
                    ArrayList<ProductDetal> list = response.body();
                    Cart cart  ;
                    ProductDetal detal ;
                    for (int i = 0; i < carts.size(); i++) {

                        cart = carts.get(i);
                        for (int j = 0; j < list.size(); j++) {
                            detal = list.get(j);
                            Log.e("list", detal.toString());
                            if (detal.getId() == Integer.valueOf(cart.getMaChiTiet())) {
                                cart.setProductDetal(detal);
                                list.remove(j);
                                break;
                            }
                        }
                    }

                    propertyChangeSupport.firePropertyChange("cart", null, carts);

                }

                @Override
                public void onFailure(Call<ArrayList<ProductDetal>> call, Throwable t) {

                }
            });
        }
    }
    public void  getListProduct(){
        carts=cartDAO.select();


        if(carts!=null&&carts.size()>0) {
            ArrayList<Integer> listMaSP=new ArrayList<>();
            for (int i=0;i<carts.size();i++){
                listMaSP.add(carts.get(i).getMaSanPham());
            }

            Call<ArrayList<Product>> call=productsInterface.getProductByIDs(listMaSP);
            call.enqueue(new Callback<ArrayList<Product>>() {
                @Override
                public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                    ArrayList<Product> list = response.body();
                    Cart cart = new Cart();
                    Product product ;
                    for (int i = 0; i < carts.size(); i++) {
                        cart = carts.get(i);
                        for (int j = 0; j < list.size(); j++) {
                            product = list.get(j);
                            if (product.getId().equals(String.valueOf(cart.getMaSanPham()))) {
                                cart.setProduct(product);
                                list.remove(j);
                                break;
                            }
                        }
                    }
                    propertyChangeSupport.firePropertyChange("cart1", null, carts);
                }

                @Override
                public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

                }
            });
        }

    }
    public void setListen(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void disListen(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }



}

