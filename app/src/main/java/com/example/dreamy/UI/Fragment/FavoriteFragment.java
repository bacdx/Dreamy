package com.example.dreamy.UI.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.dreamy.InterfaceRetrofit.ProductsInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Manager.ProfileManager;
import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.ProductTop;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.ProductAdapter;
import com.google.android.play.core.integrity.p;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FavoriteFragment extends Fragment implements PropertyChangeListener {
    private RecyclerView recyclerViewMyList;
    private RecyclerView recyclerViewTopList;
    private TextView tvMyList;
    private TextView tvToplist;
    private ArrayList<Product> listMy;
    private ArrayList<Product> listTop;
    private ProductsInterface productsInterface;
    private ProductAdapter topAdapter,myAdapter;
    private ProductManager productManager;
    Retrofit retrofit ;

    public FavoriteFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit=RetrofitService.getClient();
         productsInterface=retrofit.create(ProductsInterface.class);
         productManager=ProductManager.getInstance(getContext());
        productManager.addListener(this);
         productManager.getTopProducts();
         productManager.getListFavorite();
         listTop=productManager.getTopList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerViewMyList=view.findViewById(R.id.rcv_myFavor);
        recyclerViewTopList=view.findViewById(R.id.rcv_TopFavor);

        listMy=productManager.getFavoriteList();
        topAdapter=new ProductAdapter(getContext(),listTop);
        myAdapter=new ProductAdapter(getContext(),listMy);
        recyclerViewMyList.setAdapter(myAdapter);
        recyclerViewTopList.setAdapter(topAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productManager.removeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("top")){
            listTop=productManager.getTopList();
            topAdapter.updateData(listTop);
        }
        if(propertyChangeEvent.getPropertyName().equals("fav")){
            listMy=productManager.getFavoriteList();
            myAdapter.updateData(listMy);
        }
    }
}