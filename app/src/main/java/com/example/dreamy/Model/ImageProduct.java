package com.example.dreamy.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageProduct implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("img")
    private String source;
    @SerializedName("masanpham")
    private String masanpham;

    public ImageProduct(String id, String source, String masanpham) {
        this.id = id;
        this.source = source;
        this.masanpham = masanpham;
    }

    public ImageProduct(String source) {
        this.source = source;
    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
