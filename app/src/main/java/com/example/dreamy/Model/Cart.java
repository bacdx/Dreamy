package com.example.dreamy.Model;

import java.util.List;

public class Cart {
    private int trangThai=0;
    private int maChiTiet ;
    private int maSanPham ;
    private ProductDetal productDetal;
    private Product product;
    private int soLuong;
    private int maSize;
    private int maColor;
    private float gia;

    public Cart() {
    }

    public Cart(int maChiTiet, int maSanPham, ProductDetal productDetal, Product product, int soLuong, int maSize, int maColor) {
        this.maChiTiet = maChiTiet;
        this.maSanPham = maSanPham;
        this.productDetal = productDetal;
        this.product = product;
        this.soLuong = soLuong;
        this.maSize = maSize;
        this.maColor = maColor;

    }

    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public ProductDetal getProductDetal() {
        return productDetal;
    }

    public void setProductDetal(ProductDetal productDetal) {
        this.productDetal = productDetal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = soLuong*(float)product.getGia();
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public int getMaColor() {
        return maColor;
    }

    public void setMaColor(int maColor) {
        this.maColor = maColor;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "maChiTiet=" + maChiTiet +
                ", maSanPham=" + maSanPham +
                ", productDetal=" + productDetal +
                ", product=" + product +
                ", soLuong=" + soLuong +
                ", maSize=" + maSize +
                ", maColor=" + maColor +
                ", gia=" + gia +
                '}';
    }
}
