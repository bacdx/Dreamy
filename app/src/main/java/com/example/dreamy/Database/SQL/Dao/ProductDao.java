package com.example.dreamy.Database.SQL.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dreamy.Database.SQL.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Insert
    void insertAll(Product product);


    @Delete
    void delete(Product product);
}
