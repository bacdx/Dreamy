package com.example.dreamy.Interface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private  static final   String URI= "http://192.168.9.4:3000/api/";
        private static Retrofit retrofit = null;

        public static Retrofit getClient() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(URI)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }


}
