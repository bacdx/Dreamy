package com.example.dreamy.Model;

import com.google.gson.annotations.SerializedName;

public class ProductTop extends Product{

    @SerializedName("count")
    private int favor;

    public ProductTop() {
    }

    public ProductTop(String id) {
        super(id);
    }




    public int getFavor() {
        return favor;
    }

    public void setFavor(int favor) {
        this.favor = favor;
    }
}
