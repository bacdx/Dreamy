package com.example.dreamy.UI.Activity.Model;

public class Size {
    private int id ;
    private int masp ;
    private String title ;

    public Size() {
    }

    public Size(int id, int masp, String title) {
        this.id = id;
        this.masp = masp;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
