package com.example.dreamy.UI.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Model.Color;
import com.example.dreamy.R;

import java.util.ArrayList;

public class SmallItemAdapter extends RecyclerView.Adapter<SmallItemAdapter.ViewHolder> {
    private IOnClick iOnClick;
    private int selectPosition=-1;
    private int lastSelectPosition=-1;

    ArrayList<Color> colors=new ArrayList<>();

    public int getLastSelectPosition() {
        return lastSelectPosition;
    }

    public void setLastSelectPosition(int lastSelectPosition) {
        this.lastSelectPosition = lastSelectPosition;
    }

    private Context context;



    public SmallItemAdapter(ArrayList<Color> colors, Context context,IOnClick iOnClick) {
        this.colors = colors;
        this.context = context;
        this.iOnClick=iOnClick;
    }

    @NonNull
    @Override
    public SmallItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_select,parent,false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull SmallItemAdapter.ViewHolder holder, int position) {

        Color color=colors.get(position);
        holder.textView.setText(color.getTitle());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastSelectPosition=selectPosition;
                selectPosition=holder.getAdapterPosition();
                iOnClick.onClickItem(selectPosition,getLastSelectPosition());

            }
        });
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public Color getItemSelect(){
        if(selectPosition==-1){
            return null;
        }
        return  colors.get(selectPosition);

    }
    private SmallItemAdapter.ViewHolder getViewHolder( SmallItemAdapter.ViewHolder holder){
        return holder;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }




    public class ViewHolder extends RecyclerView.ViewHolder  {

        public  TextView textView;
        public ViewHolder(@NonNull View itemView)  {
            super(itemView);
            textView=itemView.findViewById(R.id.item_sl);

        }




    }

        public interface  IOnClick{
        public void  onClickItem(int position,int lastPosition);
        }


}

