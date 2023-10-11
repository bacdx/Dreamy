package com.example.dreamy.UI.Activity.Model;

import java.io.Serializable;

public class Category implements Serializable {
    private String id ;
    private String ten ;
    private String image ;
    private String note ;

    public Category() {
    }

    public Category(String id, String ten, String image, String note) {
        this.id = id;
        this.ten = ten;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
