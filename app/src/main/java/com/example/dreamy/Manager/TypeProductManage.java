package com.example.dreamy.Manager;

import com.example.dreamy.Model.Category;

import java.util.ArrayList;

public class TypeProductManage {
    private static TypeProductManage instance;
    private ArrayList<Category> categoryArrayList;


    private TypeProductManage() {
        categoryArrayList = new ArrayList<>();
    }


    public static TypeProductManage getInstance() {
        if (instance == null) {
            instance = new TypeProductManage();
        }
        return instance;
    }

    public Category getCategoryById( String id) {
        String maLoai="";
        for (int i=0;i<categoryArrayList.size();i++) {
            maLoai=categoryArrayList.get(i).getId();
            if(id.equals(maLoai)){
                return categoryArrayList.get(i);
            }
        }
        return null;
    }

    private void setCategoryArrayList(ArrayList<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }
}
