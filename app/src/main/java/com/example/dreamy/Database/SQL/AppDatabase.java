package com.example.dreamy.Database.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public   class AppDatabase extends SQLiteOpenHelper {
     public static final String DATABASE_NAME="favorite";
     public static final String CREATE_TABLE_CART="create table cart" +
             "( id  integer not null primary key autoincrement," +
            " masp integer not null,"+
            " mactsp integer not null,"+
            " masize integer not null,"+
            " macolor integer not null,"+
            " soluong integer not null);";
     public static final String CREATE_TABLE_FAVORITE="create table favorite" +
             "( id  integer not null primary key autoincrement," +
             "masp integer not null)";
     private static final String SQL_DELETE_ENTRIES =
             "DROP TABLE IF EXISTS " +DATABASE_NAME;
     public AppDatabase(@Nullable Context context) {
         super(context, DATABASE_NAME, null,2);
     }

     @Override
     public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_FAVORITE);
            sqLiteDatabase.execSQL(CREATE_TABLE_CART);
     }

     @Override
     public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
         sqLiteDatabase.execSQL("drop table if exists favorite");
         sqLiteDatabase.execSQL("drop table if exists cart");
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);

         onCreate(sqLiteDatabase);

     }
 }
