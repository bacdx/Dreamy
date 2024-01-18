package com.example.dreamy.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("id")
    String id ;
    String cccd;
    String magioitinh;
    @SerializedName("hoten")
    String hoten;
    @SerializedName("username")
    String name ;

    @SerializedName("address")
    String email;
    @SerializedName("matkhau")
    String password;
    @SerializedName("numberphone")
    String numberphone;
    @SerializedName("avatar")
    String avatar;


    public User() {
    }

    public User(String id, String name, String email, String password, String numberphone,String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberphone = numberphone;
        this.avatar=avatar;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getMagioitinh() {
        return magioitinh;
    }

    public void setMagioitinh(String magioitinh) {
        this.magioitinh = magioitinh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", cccd='" + cccd + '\'' +
                ", magioitinh='" + magioitinh + '\'' +
                ", hoten='" + hoten + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", numberphone='" + numberphone + '\'' +
                '}';
    }

}
