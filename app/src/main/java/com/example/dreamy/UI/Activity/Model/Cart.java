package com.example.dreamy.UI.Activity.Model;

import java.util.List;

public class Cart {
    private String id ;
    private String ten ;
    private List<String> size;
    private String gia ;
    private String img ;

    public Cart() {
    }

    public Cart(String id, String ten, List<String> size, String gia, String img) {
        this.id = id;
        this.ten = ten;
        this.size = size;
        this.gia = gia;
        this.img = img;
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

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
