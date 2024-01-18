package com.example.dreamy.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Random;

public class Comment {
    private static final String URI= "https://play-lh.googleusercontent.com/2l652ZLy6I_sz1hYKEKHxAlIt65gQfXqBSRLF3WCSxj_51_i_xUyxuRxY6r5ionKJHQ";
 private  String id;
 @SerializedName("avatar")
 private  String uriAvatar;
    @SerializedName("hoten")

    private String name;
    @SerializedName("content")

    private String Comment;
    @SerializedName("makhachhang")

    private String maKhachHang;
    @SerializedName("masanpham")

    private String maSanPham;

    public Comment(String id, String uriAvatar, String name, String comment) {
        this.id = id;
        this.uriAvatar = uriAvatar;
        this.name = name;
        Comment = comment;
    }

    public Comment() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUriAvatar() {
        return uriAvatar;
    }

    public void setUriAvatar(String uriAvatar) {
        this.uriAvatar = uriAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public static ArrayList<Comment> listFake(){
        ArrayList<Comment> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            Comment comment=new Comment(String.valueOf(i),URI, "Tran Xuan "+i,i+"asdafasd");
            list.add(comment);
        }
        return list;
    }
}