package com.example.dreamy;

import com.example.dreamy.Helper.AppInfo;
import com.example.dreamy.Helper.Helpers;
import com.example.dreamy.Helper.HttpProvider;
import com.google.gson.Gson;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class CreateOrder {
    private class CreateOrderData {
        String AppId;
        String AppUser;
        String AppTime;
        String Amount;
        String AppTransId;
        String EmbedData;
        String Items;
        String BankCode;
        String Description;
        String Mac;
        String DiaChi;
        String PhuongThuc;

        private CreateOrderData(String appUser,String amount,String item ,String DiaChi ,String PhuongThuc) throws Exception {
            long appTime = new Date().getTime();
            AppId = String.valueOf(AppInfo.APP_ID);
            AppUser = appUser;
            AppTime = String.valueOf(appTime);
            Amount = amount;
            AppTransId = Helpers.getAppTransId();
            EmbedData = "{}";
            Items = item;
            BankCode = "zalopayapp";
            this.DiaChi=DiaChi;
            this.PhuongThuc=PhuongThuc;
            Description = "Merchant pay for order #" + Helpers.getAppTransId();
            String inputHMac = String.format("%s|%s|%s|%s|%s|%s|%s",
                    this.AppId,
                    this.AppTransId,
                    this.AppUser,
                    this.Amount,
                    this.AppTime,
                    this.EmbedData,
                    this.Items);

            Mac = Helpers.getMac(AppInfo.MAC_KEY, inputHMac);
        }


    }

     public JSONObject createOrder(String appUser,String amount,String item,String DiaChi,String PhuongThuc) throws Exception {
        CreateOrderData input = new CreateOrderData(appUser,amount,item,DiaChi,PhuongThuc);

        RequestBody formBody = new FormBody.Builder()
                .add("app_id", input.AppId)
                .add("app_user", input.AppUser)
                .add("app_time", input.AppTime)
                .add("amount", input.Amount)
                .add("app_trans_id", input.AppTransId)
                .add("embed_data", input.EmbedData)
                .add("item", input.Items)
                .add("bank_code", input.BankCode)
                .add("description", input.Description)
                .add("mac", input.Mac)
                .add("address", input.DiaChi)
                .add("phuongthuc", input.PhuongThuc)
                .build();



        JSONObject data = HttpProvider.sendPost(AppInfo.URL_CREATE_ORDER, formBody);
        return data;
    }
}

