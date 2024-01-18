package com.example.dreamy;

import android.util.Log;

import com.example.dreamy.InterfaceRetrofit.ProductDetalInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.ProductDetal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetalController {

    private Retrofit retrofit;
    private Res res;
    public ArrayList<ProductDetal> list=new ArrayList<>();
    private PropertyChangeSupport propertyChangeSupport;

    public ProductDetalController(Res res) {
        retrofit= RetrofitService.getClient();
        this.res=res;
        propertyChangeSupport=new PropertyChangeSupport(this);

    }
    public ProductDetal getOderderItem(int maMau,int maSize ){


        for(int i=0;i<list.size();i++){

            ProductDetal productDetal=list.get(i);
            if (maMau==productDetal.getMaMau()&& maSize==productDetal.getMaSize()){
              return productDetal;
            }
        }
        return null;
    }


public void addLisent(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
}
    public void disLisent(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }



    public ArrayList<ProductDetal> getResounce(Product product){
        if(product.getProductDetals()==null||product.getProductDetals().size()==0){
            ProductDetalInterface productDetalInterface=retrofit.create(ProductDetalInterface.class);
            Call<ArrayList<ProductDetal>> call=productDetalInterface.getCall(product.getId());
            call.enqueue(new Callback<ArrayList<ProductDetal>>() {
                @Override
                public void onResponse(Call<ArrayList<ProductDetal>> call, Response<ArrayList<ProductDetal>> response) {

                    product.setProductDetals(response.body());

                    propertyChangeSupport.firePropertyChange("listdetal",null,null);

                }

                @Override
                public void onFailure(Call<ArrayList<ProductDetal>> call, Throwable t) {
                    res.error(t.toString());
                }
            });
        }


    return null;
    }

    public interface Res{
        public void error(String t);

        public void resOK(ArrayList<ProductDetal> list);
    }

}


