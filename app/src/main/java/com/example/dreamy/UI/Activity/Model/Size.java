package com.example.dreamy.UI.Activity.Model;

public class Size {
    String id;
    String masp;
    String title;

    public Size(String id, String masp, String title) {
        this.id = id;
        this.masp = masp;
        this.title = title;
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
}
