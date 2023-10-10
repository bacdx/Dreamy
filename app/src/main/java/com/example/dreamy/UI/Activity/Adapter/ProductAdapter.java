package com.example.dreamy.UI.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Model.Category;
import com.example.dreamy.UI.Activity.Model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private Context context;
    private List<Product> list= new ArrayList<>();
    private OnItemClickListener listener;

    public ProductAdapter(Context context, List<Product> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
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
            holder.item_gia.setText(product.getGia());
        Picasso.get().load(product.getImg()).into(holder.item_img);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                           Product product =list.get(position);
                            listener.onItemClick(product);
                        }
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
}
