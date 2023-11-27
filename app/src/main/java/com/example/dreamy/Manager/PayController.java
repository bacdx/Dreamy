package com.example.dreamy.Manager;

import com.example.dreamy.CreateOrder;
import com.example.dreamy.Model.OderDetal;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Observer;

public class PayController   {

    private OderDetal oderDetal;
    private String amount;

    private CreateOrder createOrder;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

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

    public void Pay(String amount, OderDetal oderDetal) throws Exception {
        this.amount=amount;
        this.oderDetal = oderDetal;
         ArrayList<OderDetal> list=new ArrayList<>();
         list.add(oderDetal);

        String jsonArray= new Gson().toJson(list);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   JSONObject order= createOrder.createOrder(amount,jsonArray);
                   pcs.firePropertyChange("Pay",null,order);

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
