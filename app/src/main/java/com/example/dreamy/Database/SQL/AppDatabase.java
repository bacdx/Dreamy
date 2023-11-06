package com.example.dreamy.Database.SQL;

import androidx.room.RoomDatabase;

import com.example.dreamy.Database.SQL.Dao.ProductDao;
import com.example.dreamy.Database.SQL.model.Product;

@androidx.room.Database(entities ={Product.class},version =1 )
 public  abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}
