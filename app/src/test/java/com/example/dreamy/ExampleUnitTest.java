package com.example.dreamy;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import com.example.dreamy.InterfaceRetrofit.ProductDetalInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Model.ProductDetal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int i;


    }

    @Test
    public void Connect(){
        Retrofit retrofit= RetrofitService.getClient();
        ProductDetalInterface productDetalInterface=retrofit.create(ProductDetalInterface.class);
        Call<ArrayList<ProductDetal>> call=productDetalInterface.getCall("1");
        call.enqueue(new Callback<ArrayList<ProductDetal>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductDetal>> call, Response<ArrayList<ProductDetal>> response) {
                ArrayList<ProductDetal> list=response.body();
                list.size();
            }

            @Override
            public void onFailure(Call<ArrayList<ProductDetal>> call, Throwable t) {

            }
        });
    }
}