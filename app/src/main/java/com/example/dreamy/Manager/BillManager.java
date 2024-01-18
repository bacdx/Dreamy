package com.example.dreamy.Manager;

import android.os.Bundle;
import android.util.Log;

import com.example.dreamy.InterfaceRetrofit.IBillsInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Model.Bill;
import com.example.dreamy.Model.InvoidDetals;
import com.example.dreamy.Model.User;
import com.google.android.play.core.integrity.e;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BillManager {
    private PropertyChangeSupport propertyChangeSupport;
    private ArrayList<Bill> bills;
    private ArrayList<Bill> billHuy;
    private ArrayList<Bill> billsXacNhan;
    private ArrayList<Bill> billsChoXacNhan;
    private ArrayList<Bill> billHoanThanh;
    private  User user=ProfileManager.getInstant().getMyAccount();
    Retrofit retrofit;
    IBillsInterface iBillsInterface;
    private static BillManager instant;

    private BillManager() {
        retrofit= RetrofitService.getClient();
        iBillsInterface=retrofit.create(IBillsInterface.class);
        propertyChangeSupport=new PropertyChangeSupport(this);
        billHuy=new ArrayList<>();
        billsXacNhan=new ArrayList<>();
        billsChoXacNhan=new ArrayList<>();
        billHoanThanh=new ArrayList<>();
    }
    public void resigListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    public void unResigListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }


    public static BillManager getInstant() {
        if (instant==null){
            instant=new BillManager();
        }
        return instant;
    }

    public ArrayList<Bill> getBillHuy() {
        return billHuy;
    }

    public ArrayList<Bill> getBillsXacNhan() {
        return billsXacNhan;
    }

    public ArrayList<Bill> getBillsChoXacNhan() {
        return billsChoXacNhan;
    }

    public ArrayList<Bill> getBillHoanThanh() {
        return billHoanThanh;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }
    public ArrayList<Bill> fetBill(){
        return  this.bills;
    }

    public void getBills(String maKhachHang){



        Call<ArrayList<Bill>> call= iBillsInterface.getBills(maKhachHang);
        call.enqueue(new Callback<ArrayList<Bill>>() {
            @Override
            public void onResponse(Call<ArrayList<Bill>> call, Response<ArrayList<Bill>> response) {
                bills=response.body();
                for (int i=0;i<bills.size();i++){
                    Bill bill=bills.get(i);
                    if (bill.trangThai.equals(Bill.DANG_CHO_XAC_NHAN)){
                        billsChoXacNhan.add(bill);

                    }
                    else if (bill.trangThai.equals(Bill.DA_XAC_NHAN)){
                        billsChoXacNhan.add(bill);

                    }
                    else if (bill.trangThai.equals(Bill.DA_HOAN_THANH)){
                        billHoanThanh.add(bill);
                    }
                    else if (bill.trangThai.equals(Bill.DA_HUY)){
                        billHuy.add(bill);

                    }

                }
                propertyChangeSupport.firePropertyChange("0",null,null);
            }

            @Override
            public void onFailure(Call<ArrayList<Bill>> call, Throwable t) {

            }
        });
    }

    public void HuyBills(Bill bill){
        Call <IBillsInterface.DataHuy> call=iBillsInterface.huyDon(bill.id);
        call.enqueue(new Callback<IBillsInterface.DataHuy>() {
            @Override
            public void onResponse(Call<IBillsInterface.DataHuy> call, Response<IBillsInterface.DataHuy> response) {

               IBillsInterface.DataHuy data =response.body();
                if(Integer.valueOf(data.getId())>0){
                    bill.setTrangThai(Bill.DA_HUY);
                    billHuy.add(bill);
                    propertyChangeSupport.firePropertyChange("0",null,bill);
                }else {
                    propertyChangeSupport.firePropertyChange("1",null,null);
                    bill.setTrangThai(Bill.DANG_CHO_XAC_NHAN);
                }

            }

            @Override
            public void onFailure(Call<IBillsInterface.DataHuy> call, Throwable t) {
                propertyChangeSupport.firePropertyChange("2",null,null);
                bill.setTrangThai(Bill.DANG_CHO_XAC_NHAN);

            }

        });
    }
    public ArrayList<Bill> getBillByState(String state){
        ArrayList <Bill> list=new ArrayList<>();
        Bill bill;
        for (int i=0;i<bills.size();i++){

             bill=bills.get(i);
             bill.getTrangThai();
            if (bill.getTrangThai().equals(state)){
                list.add(bill);
            }
        }
        return list;

    }
    public  void getBillDetal(Bill  bill){
        Retrofit retrofit =RetrofitService.getClient();
        IBillsInterface iBillsInterface=retrofit.create(IBillsInterface.class);
        Call<ArrayList<InvoidDetals>> call=iBillsInterface.getNvoiDetals(bill.getId());
        call.enqueue(new Callback<ArrayList<InvoidDetals>>() {
            @Override
            public void onResponse(Call<ArrayList<InvoidDetals>> call, Response<ArrayList<InvoidDetals>> response) {
                ArrayList<InvoidDetals> invoidDetals=response.body();
                bill.setListInvoiceDetails(invoidDetals);
                propertyChangeSupport.firePropertyChange("detalBill",null,bill);


            }

            @Override
            public void onFailure(Call<ArrayList<InvoidDetals>> call, Throwable t) {

            }
        });

    }

}
