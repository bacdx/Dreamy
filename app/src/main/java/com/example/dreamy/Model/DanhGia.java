package com.example.dreamy.Model;

public class DanhGia {

  private   String maKhachHang="";
    private String maSanPham="";
    private int soSao=-1;
    public static final int ONE=1;
    public static final int TWO=2;
    public static final int THREE=3;
    public static final int FOUR=4;
    public static final int FIVE=5;

    public DanhGia(String maKhachHang, String maSanPham, int soSao) {
        this.maKhachHang = maKhachHang;
        this.maSanPham = maSanPham;
        this.soSao = soSao;
    }

    public DanhGia() {
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

    public int getSoSao(int soSao) {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }
}
