package com.example.dreamy.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Size implements Parcelable {
    String id;
    String masp;
    String title;

    public Size(String id, String masp, String title) {
        this.id = id;
        this.masp = masp;
        this.title = title;
    }

    protected Size(Parcel in) {
        id = in.readString();
        masp = in.readString();
        title = in.readString();
    }

    public static final Creator<Size> CREATOR = new Creator<Size>() {
        @Override
        public Size createFromParcel(Parcel in) {
            return new Size(in);
        }

        @Override
        public Size[] newArray(int size) {
            return new Size[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(masp);
        parcel.writeString(title);
    }
}
