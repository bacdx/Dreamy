package com.example.dreamy.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.dreamy.InterfaceRetrofit.Model.InvoiceDetails;
import com.google.gson.annotations.SerializedName;

public class InvoidDetals implements Parcelable {
    private String id;
    @SerializedName("soluong")
    String soLuong;
    @SerializedName("size")
    String size;
    @SerializedName("mau")
    String color;
    @SerializedName("masanpham")
    String maSP;
    @SerializedName("mahoadon")
    String maHD;
    @SerializedName("img")
    String Img;
    @SerializedName("dongia")
    String price;
    @SerializedName("ten")
    String tenSanPham;
    @SerializedName("name")
    String tenNhaSanXuat;
    @SerializedName("mactsanpham")
    String maCT;


    protected InvoidDetals(Parcel in) {
        id = in.readString();
        soLuong = in.readString();
        size = in.readString();
        color = in.readString();
        maSP = in.readString();
        maHD = in.readString();
        Img = in.readString();
        price = in.readString();
        tenSanPham = in.readString();
        tenNhaSanXuat = in.readString();
        maCT = in.readString();
    }

    public static final Creator<InvoidDetals> CREATOR = new Creator<InvoidDetals>() {
        @Override
        public InvoidDetals createFromParcel(Parcel in) {
            return new InvoidDetals(in);
        }

        @Override
        public InvoidDetals[] newArray(int size) {
            return new InvoidDetals[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenNhaSanXuat() {
        return tenNhaSanXuat;
    }

    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public String getMaCT() {
        return maCT;
    }

    public void setMaCT(String maCT) {
        this.maCT = maCT;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(soLuong);
        parcel.writeString(size);
        parcel.writeString(color);
        parcel.writeString(maSP);
        parcel.writeString(maHD);
        parcel.writeString(Img);
        parcel.writeString(price);
        parcel.writeString(tenSanPham);
        parcel.writeString(tenNhaSanXuat);
        parcel.writeString(maCT);
    }
}
