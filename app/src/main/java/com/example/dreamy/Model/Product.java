package com.example.dreamy.Model;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable , Parcelable {

    public static final String IMGDEFAUSE="https://dony.vn/wp-content/uploads/2022/03/decoimplementsr-goc-chup-anh-cho-shop-quan-ao-1.jpg";
    private String id ;
    private String ten ;
    private float danhGia ;
    private String manhasanxuat ;
    private String maloai ;
    private String ngaynhap ;

    private String ghichu ;

    @SerializedName("gia")
    private double gia ;
    private String img ;
    private ArrayList<ProductDetal>  productDetals;
    private String ten_san_pham ;
    private String ten_loai_san_pham ;
    private ArrayList<Size> sizes;
    private ArrayList<Color> colors;
    private ArrayList<Comment> comments;
    private int CountFavor=0;
    private int khuyenmai=-1;

    public Product(String id, String ten, float danhGia, String manhasanxuat, String maloai, String ngaynhap, String ghichu, double gia, String img, ArrayList<ProductDetal> productDetals, String ten_san_pham, String ten_loai_san_pham, ArrayList<Size> sizes, ArrayList<Color> colors, ArrayList<Comment> comments, int countFavor, int khuyenmai) {
        this.id = id;
        this.ten = ten;
        this.danhGia = danhGia;
        this.manhasanxuat = manhasanxuat;
        this.maloai = maloai;
        this.ngaynhap = ngaynhap;
        this.ghichu = ghichu;
        this.gia = gia;
        this.img = img;
        this.productDetals = productDetals;
        this.ten_san_pham = ten_san_pham;
        this.ten_loai_san_pham = ten_loai_san_pham;
        this.sizes = sizes;
        this.colors = colors;
        this.comments = comments;
        CountFavor = countFavor;
        this.khuyenmai = khuyenmai;
    }

    public Product() {
    }
    public Product(String id){
        this.id=id;
    }
    protected Product(Parcel in) {
        id = in.readString();
        ten = in.readString();
        danhGia = in.readFloat();
        manhasanxuat = in.readString();
        maloai = in.readString();
        ngaynhap = in.readString();
        ghichu = in.readString();
        gia = in.readDouble();
        img = in.readString();
        ten_san_pham = in.readString();
        ten_loai_san_pham = in.readString();
        sizes = in.createTypedArrayList(Size.CREATOR);
        colors = in.createTypedArrayList(Color.CREATOR);
        CountFavor = in.readInt();
        khuyenmai = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(float danhGia) {
        this.danhGia = danhGia;
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

    public ArrayList<ProductDetal> getProductDetals() {
        return productDetals;
    }

    public void setProductDetals(ArrayList<ProductDetal> productDetals) {
        this.productDetals = productDetals;
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

    public ArrayList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<Size> sizes) {
        this.sizes = sizes;
    }

    public ArrayList<Color> getColors() {
        return colors;
    }

    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getCountFavor() {
        return CountFavor;
    }

    public void setCountFavor(int countFavor) {
        CountFavor = countFavor;
    }

    public int getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(int khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", ten='" + ten + '\'' +
                ", danhGia=" + danhGia +
                ", manhasanxuat='" + manhasanxuat + '\'' +
                ", maloai='" + maloai + '\'' +
                ", ngaynhap='" + ngaynhap + '\'' +
                ", ghichu='" + ghichu + '\'' +
                ", gia=" + gia +
                ", img='" + img + '\'' +
                ", productDetals=" + productDetals +
                ", ten_san_pham='" + ten_san_pham + '\'' +
                ", ten_loai_san_pham='" + ten_loai_san_pham + '\'' +
                ", sizes=" + sizes +
                ", colors=" + colors +
                ", comments=" + comments +
                ", CountFavor=" + CountFavor +
                ", khuyenmai=" + khuyenmai +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(ten);
        parcel.writeFloat(danhGia);
        parcel.writeString(manhasanxuat);
        parcel.writeString(maloai);
        parcel.writeString(ngaynhap);
        parcel.writeString(ghichu);
        parcel.writeDouble(gia);
        parcel.writeString(img);
        parcel.writeString(ten_san_pham);
        parcel.writeString(ten_loai_san_pham);
        parcel.writeTypedList(sizes);
        parcel.writeTypedList(colors);
        parcel.writeInt(CountFavor);
        parcel.writeInt(khuyenmai);
    }
}
