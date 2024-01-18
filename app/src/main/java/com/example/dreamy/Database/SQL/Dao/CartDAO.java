package com.example.dreamy.Database.SQL.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dreamy.Database.SQL.AppDatabase;
import com.example.dreamy.Model.Cart;

import java.util.ArrayList;

public class CartDAO {
    private Context context;
    private AppDatabase appDatabase;
    private SQLiteDatabase sqLiteDatabase;

    public CartDAO(Context context) {
        this.context = context;
        this.appDatabase = new AppDatabase(context);
        this.sqLiteDatabase = appDatabase.getReadableDatabase();
    }
    public long insert(@NonNull  int mactsp,int masp,@NonNull int soLuong,@NonNull int maSize,@NonNull int maColor){
        ContentValues contentValues=new ContentValues();
        contentValues.put("masp",masp);
        contentValues.put("mactsp",mactsp);
        contentValues.put("masize", maSize);
        contentValues.put("macolor",maColor);
        contentValues.put("soluong",soLuong);
        return sqLiteDatabase.insert("cart",null,contentValues);

    }
    public long update(@NonNull  String mactsp,int masp,@NonNull int soLuong,@NonNull int maSize,@NonNull int maColor){
        Log.i("add",mactsp+masp+soLuong+maSize+maColor);
        ContentValues contentValues=new ContentValues();
        contentValues.put("mactsp",String.valueOf(mactsp));
        contentValues.put("masp",masp);
        contentValues.put("masize", maSize);
        contentValues.put("macolor",maColor);
        contentValues.put("soluong",soLuong);
        return sqLiteDatabase.update("cart",contentValues,"masp=?",new String[]{String.valueOf(masp)});

    }
    public long updateSoluong(@NonNull  String masp,@NonNull int soLuong){

        ContentValues contentValues=new ContentValues();
        contentValues.put("masp",Integer.valueOf(masp));
        contentValues.put("soluong",soLuong);
        return sqLiteDatabase.update("cart",contentValues,"masp=?",new String[]{String.valueOf(masp)});

    }
    public long delete(int mactsp){

        return sqLiteDatabase.delete("cart","mactsp=?",new String[]{String.valueOf(mactsp)});

    }
    public ArrayList<Cart> select(){
        ArrayList<Cart> list=new ArrayList<>();
        String sql="select * from cart";

        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);

        cursor.moveToFirst();
        if(cursor.isAfterLast()){
            cursor.close();
            return new ArrayList<>();
        }

            do {
                Cart cart=new Cart();
                cart.setMaSanPham(cursor.getInt(1));
                cart.setMaChiTiet(cursor.getInt(2));
                cart.setMaSize(cursor.getInt(3));
                cart.setMaColor(cursor.getInt(4));
                cart.setSoLuong(cursor.getInt(5));
                Log.i("add",cart.toString());
               list.add(cart);
            }while (cursor.moveToNext());


        cursor.close();
        return list;
    }
}
