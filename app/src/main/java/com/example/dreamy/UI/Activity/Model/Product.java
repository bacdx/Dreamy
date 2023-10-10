package com.example.dreamy.UI.Activity.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id ;
    private String ten ;
    private String manhasanxuat ;
    private String maloai ;
    private String ngaynhap ;
    private String soluong ;
    private String ghichu ;
    private String gia ;
    private String img ;
    public Product() {
    }


    public Product(String id, String ten, String manhasanxuat, String maloai, String ngaynhap, String soluong, String ghichu, String gia, String img) {
        this.id = id;
        this.ten = ten;
        this.manhasanxuat = manhasanxuat;
        this.maloai = maloai;
        this.ngaynhap = ngaynhap;
        this.soluong = soluong;
        this.ghichu = ghichu;
        this.gia = gia;
        this.img = img ;
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

    public String getManhasanxuat() {
        return manhasanxuat;
    }

    public void setManhasanxuat(String manhasanxuat) {
        this.manhasanxuat = manhasanxuat;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

}
