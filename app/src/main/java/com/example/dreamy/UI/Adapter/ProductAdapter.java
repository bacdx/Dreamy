package com.example.dreamy.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Model.Color;
import com.example.dreamy.ProductController;
import com.example.dreamy.R;
import com.example.dreamy.Model.Product;
import com.example.dreamy.UI.Activity.ProductDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private Context context;
    private List<Product> list= new ArrayList<>();

    private ProductController productController=new ProductController();
   public void updateData(ArrayList<Product> newProducts){
            this.list = newProducts;
            notifyDataSetChanged();
    }
    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products,parent,false);
        return new ProductHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
            Product product = list.get(position);
            if (product==null){
                return;
            }
            holder.item_name.setText(product.getTen());
            holder.item_gia.setText(product.getGia()+"đ");
        Picasso.get().load(product.getImg()).into(holder.item_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("Product",product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;

    }



    public class ProductHolder extends RecyclerView.ViewHolder{
        private TextView item_name ,item_gia;
        private ImageView item_img ;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            item_gia = itemView.findViewById(R.id.item_gia);
            item_name = itemView.findViewById(R.id.item_name);
            item_img = itemView.findViewById(R.id.item_img);

        }
    }



}
