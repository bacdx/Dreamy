package com.example.dreamy.UI.Activity.Model;

public class Color {
    private int id ;
    private int masp ;
    private String title ;
    private String mamau ;

    public Color() {
    }

    public Color(int id, int masp, String title, String mamau) {
        this.id = id;
        this.masp = masp;
        this.title = title;
        this.mamau = mamau;
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

    public String getMamau() {
        return mamau;
    }

    public void setMamau(String mamau) {
        this.mamau = mamau;
    }
}
