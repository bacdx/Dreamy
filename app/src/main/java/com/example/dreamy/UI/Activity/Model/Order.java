package com.example.dreamy.UI.Activity.Model;

import com.example.dreamy.UI.Activity.Utils.OrderStatus;

import java.util.List;

public class Order {
    public String id;
    public String code;
    public String status;
    public List<CategoryPhake> Category;

    public Order() {
    }

    public Order(String id, String code, String status, List<CategoryPhake> category) {
        this.id = id;
        this.code = code;
        this.status = status;
        Category = category;
    }
}
