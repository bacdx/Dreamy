package com.example.dreamy.UI.Activity.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    private String id ;
    private String ten ;
    private String img ;
    private String note ;

    public Category() {
    }

    public Category(String id, String ten, String img, String note) {
        this.id = id;
        this.ten = ten;
        this.img = img;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
