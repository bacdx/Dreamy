package com.example.dreamy.Manager;

import com.example.dreamy.CreateOrder;
import com.example.dreamy.Model.OderDetal;
import com.example.dreamy.Model.User;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class PayController   {
    private ProfileManager profileManager=ProfileManager.getInstant();

    private String mAmount;

    private CreateOrder createOrder;

    private  PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private static PayController instance;



    private PayController() {

        createOrder =new CreateOrder();
    }

    public static PayController getInstance() {
        if (instance==null){
            instance=new PayController();
        }
        return instance;
    }

    public void Pay(List<OderDetal> list ,String DiaChi,String PhuongThuc) throws Exception {
        long amount =0;
        for (int i=0; i<list.size();i++){
            amount+=list.get(i).getDonGia();
        }
        mAmount=Long.toString(amount);




        String jsonArray= new Gson().toJson(list);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    User user=profileManager.getMyAccount();
                   JSONObject order= createOrder.createOrder(user.getId(),mAmount,jsonArray, DiaChi,PhuongThuc);

                    String code = order.getString("return_code");
                    if (code.equals("1")) {
                        String token =order.getString("zp_trans_token");
                        String id =order.getString("id");
                        pcs.firePropertyChange("Pay",null,new String []{token,id});
                    }else if(code.equals("2")){
                        pcs.firePropertyChange("Pay",null,new String("error"));

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }


}
