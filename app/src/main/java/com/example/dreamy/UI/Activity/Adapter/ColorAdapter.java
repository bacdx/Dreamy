package com.example.dreamy.UI.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Model.Color;
import com.example.dreamy.UI.Activity.ProductDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder>{
    Context context ;
    List<Color> colors = new ArrayList<Color>();

    public ColorAdapter(Context context, List<Color> colors) {
        this.context = context;
        this.colors = colors;
    }

    public void setColors(List<com.example.dreamy.UI.Activity.Model.Color> colors){
        this.colors = colors;
        notifyDataSetChanged();
    }

    @Override
    public ColorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_color,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.ViewHolder holder, int position) {
        com.example.dreamy.UI.Activity.Model.Color color = colors.get(position);
        if(color==null){
            return;
        }
        holder.view.setBackgroundColor(android.graphics.Color.parseColor(color.getMamau()));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.item_view_color);
        }
    }
}
