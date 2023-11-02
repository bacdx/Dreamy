package com.example.dreamy.UI.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Model.Cart;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder>{
    private Context context;
    private List<Cart> cartList = new ArrayList<>();

    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        Cart cart = cartList.get(position);
        if (cart==null){
            return;
        }

        holder.item_gia.setText(cart.getGia()+"d");
        holder.item_name.setText(cart.getTen());
        Picasso.get().load(cart.getImg()).into(holder.item_img);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,cart.getSize());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinSize.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        private CheckBox itemcheck;
        private TextView item_name ,item_gia;
        private Spinner spinSize ;
        private ImageView item_img ;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            item_gia = itemView.findViewById(R.id.item_gia);
            item_name = itemView.findViewById(R.id.item_name);
            item_img = itemView.findViewById(R.id.item_img);
            spinSize = itemView.findViewById(R.id.item_size);
            itemcheck = itemView.findViewById(R.id.item_checkout);

        }
    }
}
