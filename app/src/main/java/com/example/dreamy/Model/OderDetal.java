package com.example.dreamy.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class OderDetal implements Serializable, Parcelable {
   private  String tenProduct;
    private String id;
    private String masize;

    private String mamau;
    private boolean state;

    private  String maHoaDon;
    private int maSanPham;
    private String maCTSanPham;
    private String soLuong;
    private double donGia;
    private  String tenSize;
    private  String tenMau;
    private String uriImg;

    public OderDetal(String tenProduct, String id, String maHoaDon, String maCTSanPham, String soLuong, double donGia, String tenSize, String tenMau) {
        this.tenProduct = tenProduct;
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.maCTSanPham = maCTSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tenSize = tenSize;
        this.tenMau = tenMau;
    }


    protected OderDetal(Parcel in) {
        tenProduct = in.readString();
        id = in.readString();
        masize = in.readString();
        mamau = in.readString();
        state = in.readByte() != 0;
        maHoaDon = in.readString();
        maSanPham = in.readInt();
        maCTSanPham = in.readString();
        soLuong = in.readString();
        donGia = in.readDouble();
        tenSize = in.readString();
        tenMau = in.readString();
        uriImg = in.readString();
    }

    public static final Creator<OderDetal> CREATOR = new Creator<OderDetal>() {
        @Override
        public OderDetal createFromParcel(Parcel in) {
            return new OderDetal(in);
        }

        @Override
        public OderDetal[] newArray(int size) {
            return new OderDetal[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getCTMaSanPham() {
        return maCTSanPham;
    }

    public void setMaCTSanPham(String maCTSanPham) {
        this.maCTSanPham = maCTSanPham;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getTenProduct() {
        return tenProduct;
    }

    public void setTenProduct(String tenProduct) {
        this.tenProduct = tenProduct;
    }

    public String getUriImg() {
        return uriImg;
    }

    public void setUriImg(String uriImg) {
        this.uriImg = uriImg;
    }

    public String getMasize() {
        return masize;
    }

    public void setMasize(String masize) {
        this.masize = masize;
    }

    public String getMamau() {
        return mamau;
    }

    public void setMamau(String mamau) {
        this.mamau = mamau;
    }

    public int getMaSanPham() {
        return this.maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public OderDetal() {


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(tenProduct);
        parcel.writeString(id);
        parcel.writeString(masize);
        parcel.writeString(mamau);
        parcel.writeByte((byte) (state ? 1 : 0));
        parcel.writeString(maHoaDon);
        parcel.writeInt(maSanPham);
        parcel.writeString(maCTSanPham);
        parcel.writeString(soLuong);
        parcel.writeDouble(donGia);
        parcel.writeString(tenSize);
        parcel.writeString(tenMau);
        parcel.writeString(uriImg);
    }
}
