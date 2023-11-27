package com.example.dreamy.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    @SerializedName("app_user")
    public String idUser;

    @SerializedName("amount")
    public long tongTien;
    @SerializedName("item")
    List<OderDetal> list=new ArrayList();
    @SerializedName("description")
    public String moTa;

    String embed_data="{}";



    public Order() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }



    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public List<OderDetal> getList() {
        return list;
    }

    public void setList(List<OderDetal> list) {
        this.list = list;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getEmbed_data() {
        return embed_data;
    }

    public void setEmbed_data(String embed_data) {
        this.embed_data = embed_data;
    }

//    public List<CategoryPhake> getCategory() {
//        return Category;
//    }
//
//    public void setCategory(List<CategoryPhake> category) {
////        Category = category;
//    }

    //    public Order(String id, String code, String status, List<CategoryPhake> category) {
//        this.id = id;
//        this.code = code;
//        this.status = status;
//        Category = category;
//    }
    public class OdrerResresult implements Serializable{
        @SerializedName("return_code")
 private String returnCode;
    @SerializedName("return_message")
 private String returnMessage;
    @SerializedName("sub_return_code")
 private int subReturnCode;
    @SerializedName("sub_return_message")
 private String subReturnMessage;
    @SerializedName("zp_trans_token")
 private String orderUrl;
    @SerializedName("order_token")
 private String zpTransToken;
    @SerializedName("qr_code")
 private String orderToken;

    public OdrerResresult(String returnCode, String returnMessage, int subReturnCode, String subReturnMessage, String orderUrl, String zpTransToken, String orderToken) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.subReturnCode = subReturnCode;
        this.subReturnMessage = subReturnMessage;
        this.orderUrl = orderUrl;
        this.zpTransToken = zpTransToken;
        this.orderToken = orderToken;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public int getSubReturnCode() {
        return subReturnCode;
    }

    public void setSubReturnCode(int subReturnCode) {
        this.subReturnCode = subReturnCode;
    }

    public String getSubReturnMessage() {
        return subReturnMessage;
    }

    public void setSubReturnMessage(String subReturnMessage) {
        this.subReturnMessage = subReturnMessage;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getZpTransToken() {
        return zpTransToken;
    }

    public void setZpTransToken(String zpTransToken) {
        this.zpTransToken = zpTransToken;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }
}
}
