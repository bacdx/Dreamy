package com.example.dreamy.UI.Activity.Model;

import java.io.Serializable;

public class History implements Serializable {
    private String ten ;
    private double gia ;
    private String img ;
    private int id ;
    private int id_sp ;
    private int id_kh;

    public History() {
    }

    public History(String ten, double gia, String img, int id, int id_sp, int id_kh) {
        this.ten = ten;
        this.gia = gia;
        this.img = img;
        this.id = id;
        this.id_sp = id_sp;
        this.id_kh = id_kh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public int getId_kh() {
        return id_kh;
    }

    public void setId_kh(int id_kh) {
        this.id_kh = id_kh;
    }
}
