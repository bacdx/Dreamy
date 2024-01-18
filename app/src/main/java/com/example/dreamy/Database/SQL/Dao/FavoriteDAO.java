package com.example.dreamy.Database.SQL.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Dao;

import com.example.dreamy.Database.SQL.AppDatabase;
import com.example.dreamy.Model.Cart;
import com.example.dreamy.Model.Product;

import java.util.ArrayList;

@Dao
public class FavoriteDAO {
   private Context context;
   private AppDatabase appDatabase;
    SQLiteDatabase sqLiteDatabase;

    public FavoriteDAO(Context context) {
        this.context = context;
        this.appDatabase = new AppDatabase(context);
        sqLiteDatabase=appDatabase.getWritableDatabase();
    }

    public long insert(int id){
        ContentValues contentValues=new ContentValues();
        contentValues.put("masp",id);
        return sqLiteDatabase.insert("favorite",null,contentValues);
    }
    public long delete(int id){
        ContentValues contentValues=new ContentValues();
        contentValues.put("masp",id);
        return sqLiteDatabase.delete("favorite","masp=?",new String[]{String.valueOf(id)});
    }
    public ArrayList<Integer> select(){
        ArrayList<Integer>  list=new ArrayList<>();
        String sql="select * from favorite ";
        Product product=new Product();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getInt(1));
            } while (cursor.moveToNext());
        }
        cursor.close();

       return list;
    }




}
