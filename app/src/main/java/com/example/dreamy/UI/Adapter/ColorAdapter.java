package com.example.dreamy.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Model.Color;
import com.example.dreamy.R;

import java.util.List;

public class ColorAdapter  extends RecyclerView.Adapter<ColorAdapter.ViewHolder>{

        List<Color> colors;
        private ColorAdapter.OnClickListener onClickListener;
        private  int pos=-1;

        public void setColors(List<com.example.dreamy.Model.Color> colors){
            this.colors = colors;
            notifyDataSetChanged();
        }


        public List<com.example.dreamy.Model.Color> getColors() {
            return colors;
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_color,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.view.setText(colors.get(position).getTitle());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(pos!=holder.getAdapterPosition()){
                        pos= holder.getAdapterPosition();
                        if(onClickListener!=null){
                            onClickListener.onClick(pos,colors.get(pos));
                        }

                    }
                }
            });
        }

        public Color getColor(){

            return colors.get(pos);
        }
        @Override
        public int getItemCount() {
            if (colors != null) {
                return colors.size();
            }
            return 0;
        }
        public void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.item_view_color);
            }
        }
        public interface OnClickListener {
            void onClick(int position, Color color);
        }
    }


