package com.example.dreamy.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Color implements Parcelable {
    String id;
    String masp;
    String title;
    String mamau;
    String img;

    public Color(String id, String masp, String title, String mamau, String img) {
        this.id = id;
        this.masp = masp;
        this.title = title;
        this.mamau = mamau;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMamau() {
        return mamau;
    }

    public void setMamau(String mamau) {
        this.mamau = mamau;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }
}
