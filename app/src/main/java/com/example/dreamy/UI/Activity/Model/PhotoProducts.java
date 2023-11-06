package com.example.dreamy.UI.Activity.Model;

public class PhotoProducts {
    private int id ;
    private int masanpham;
    private String img ;

    public PhotoProducts() {
    }

    public PhotoProducts(int id, int masanpham, String img) {
        this.id = id;
        this.masanpham = masanpham;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
