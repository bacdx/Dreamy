package com.example.dreamy.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.R;
import com.example.dreamy.Model.Category;
import com.example.dreamy.UI.Activity.ProductActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{
    private Context context;
    private List<Category> categoryList = new ArrayList<>();


    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;

    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category==null){
            return;
        }
        Picasso.get().load(category.getImg()).into(holder.item_img);
        holder.item_name.setText(category.getTen());
        holder.itemView.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("Category",category);
                context.startActivity(intent);
            }


        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        private TextView item_name;
        private CircleImageView item_img ;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_img = itemView.findViewById(R.id.item_img);

        }
    }
    public interface OnItemClickListener extends View.OnClickListener  {

    }

}
