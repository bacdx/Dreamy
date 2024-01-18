package com.example.dreamy.Model;

import com.google.gson.annotations.SerializedName;

public class ProductDetal {
    @SerializedName("id")
    private int  id;
    @SerializedName("masanpham")
    private  int maSanPham;
    @SerializedName("masize")
    private  int maSize=-1;
    @SerializedName("mamau")
    private int maMau=-1;
     @SerializedName("soluong")
    private int soLuong;
     private int img;
     private Size size;
     private Color color;

    public ProductDetal(int id, int maSanPham, int maSize, int maMau, int soLuong, int img) {
        this.id = id;
        this.maSanPham = maSanPham;
        this.maSize = maSize;
        this.maMau = maMau;
        this.soLuong = soLuong;
        this.img = img;
    }

    public ProductDetal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ProductDetal{" +
                "id=" + id +
                ", maSanPham=" + maSanPham +
                ", maSize=" + maSize +
                ", maMau=" + maMau +
                ", soLuong=" + soLuong +

                '}';
    }
}
