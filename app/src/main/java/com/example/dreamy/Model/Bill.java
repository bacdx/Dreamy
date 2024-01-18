package com.example.dreamy.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.dreamy.InterfaceRetrofit.Model.IBill;
import com.example.dreamy.InterfaceRetrofit.Model.InvoiceDetails;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Bill   implements IBill ,Parcelable {
    public static final String OK="0";
    public static final String CANCLE="1";

    public static String DANG_HUY="-1";
    public static String DANG_CHO_XAC_NHAN="0";
    public static String DA_XAC_NHAN="1";
    public static String DANG_GIAO="2";
    public static String DA_HOAN_THANH="3";
    public static String DA_HUY="4";
    public static String ADMIN_HUY="0";
    public static String KHACHHANG_HUY="1";


    public String id ="";
    @SerializedName("thoigian")
    public String thoiGian ="";
    @SerializedName("tongtien")

    public String tongTien ="";
    @SerializedName("trangthai")

    public String trangThai ="";
    @SerializedName("mavandon")

    public String maVanDon ="";
    @SerializedName("makhachhang")

    public String maKhachHang ="";
    @SerializedName("hoten")

    public String tenKhachHang="";
    @SerializedName("address")

    public String DiaChi="";
    public ArrayList<InvoidDetals> listInvoiceDetails =null;


    protected Bill(Parcel in) {
        id = in.readString();
        thoiGian = in.readString();
        tongTien = in.readString();
        trangThai = in.readString();
        maVanDon = in.readString();
        maKhachHang = in.readString();
        tenKhachHang = in.readString();
        DiaChi = in.readString();
    }

    public static final Creator<Bill> CREATOR = new Creator<Bill>() {
        @Override
        public Bill createFromParcel(Parcel in) {
            return new Bill(in);
        }

        @Override
        public Bill[] newArray(int size) {
            return new Bill[size];
        }
    };


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaVanDon() {
        return maVanDon;
    }

    public void setMaVanDon(String maVanDon) {
        this.maVanDon = maVanDon;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public ArrayList<InvoidDetals> getListInvoiceDetails() {
        return listInvoiceDetails;
    }

    public void setListInvoiceDetails(ArrayList<InvoidDetals> listInvoiceDetails) {
        this.listInvoiceDetails = listInvoiceDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", thoiGian='" + thoiGian + '\'' +
                ", tongTien='" + tongTien + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", maVanDon='" + maVanDon + '\'' +
                ", maKhachHang='" + maKhachHang + '\'' +
                ", tenKhachHang='" + tenKhachHang + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", listInvoiceDetails=" + listInvoiceDetails +
                '}';
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(thoiGian);
        parcel.writeString(tongTien);
        parcel.writeString(trangThai);
        parcel.writeString(maVanDon);
        parcel.writeString(maKhachHang);
        parcel.writeString(tenKhachHang);
        parcel.writeString(DiaChi);
    }
}
