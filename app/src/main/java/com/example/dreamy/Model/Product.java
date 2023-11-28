package com.example.dreamy.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id ;
    private String ten ;
    private String manhasanxuat ;
    private String maloai ;
    private String ngaynhap ;

    private String ghichu ;
    private double gia ;
    private String img ;
    private String ten_san_pham ;
    private String ten_loai_san_pham ;
    public Product() {
    }

    public Product(String id, String ten, String manhasanxuat, String maloai, String ngaynhap, String ghichu, double gia, String img, String ten_san_pham, String ten_loai_san_pham) {
        this.id = id;
        this.ten = ten;
        this.manhasanxuat = manhasanxuat;
        this.maloai = maloai;
        this.ngaynhap = ngaynhap;
        this.ghichu = ghichu;
        this.gia = gia;
        this.img = img;
        this.ten_san_pham = ten_san_pham;
        this.ten_loai_san_pham = ten_loai_san_pham;
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

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
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

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public String getTen_loai_san_pham() {
        return ten_loai_san_pham;
    }

    public void setTen_loai_san_pham(String ten_loai_san_pham) {
        this.ten_loai_san_pham = ten_loai_san_pham;
    }
}
