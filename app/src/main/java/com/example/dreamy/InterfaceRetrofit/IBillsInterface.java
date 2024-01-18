package com.example.dreamy.InterfaceRetrofit;

import com.example.dreamy.Model.Bill;
import com.example.dreamy.Model.InvoidDetals;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IBillsInterface {
    @GET("donhangs")
    public Call<ArrayList<Bill>> getBills(@Query("makhachhang") String maKhachHang);
    @GET("huydon")
    public Call<DataHuy> huyDon(@Query("id") String id);

    @GET("chitietdons")
    public Call<ArrayList<InvoidDetals>>  getNvoiDetals(@Query("mahoadon") String maHoaDon);
    @POST("pay")
    public Call<Void> sendRes(@Query("statusCode") String statusCode,@Query("id") String id );
    public class DataHuy{
        String id="";

        public DataHuy(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
