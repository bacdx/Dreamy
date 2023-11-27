package com.example.dreamy;

import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Model.Product;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ProductController implements PropertyChangeListener {
    private ProductManager productManager =ProductManager.getInstance();


    public ProductController() {

    }



    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                if(propertyChangeEvent.getPropertyName()=="allProduct"){
                    propertyChangeEvent.getOldValue();
                }
    }
}
