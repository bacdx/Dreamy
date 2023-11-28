package com.example.dreamy.UI.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Model.Size;
import com.example.dreamy.R;

import java.util.ArrayList;

public class SmallIitemSize extends RecyclerView.Adapter<SmallIitemSize.ViewHolder>  implements Cloneable {
private  int selectPosition=-1;
private  int lastSelectPosition=-1;
    private ArrayList<Size> sizes;
    private Context context;
private  SizeOnClickItem sizeOnClickItem;
    public SmallIitemSize(ArrayList<Size> sizes, Context context,SizeOnClickItem sizeOnClickItem) {
        this.sizes = sizes;
        this.context = context;
        this.sizeOnClickItem=sizeOnClickItem;
    }

    @NonNull
    @Override
    public SmallIitemSize.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_select,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull SmallIitemSize.ViewHolder holder, int position) {
        Size size=sizes.get(position);

        holder.textView.setText(size.getTitle());
        holder.textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                    lastSelectPosition =selectPosition;
                    selectPosition=holder.getAdapterPosition();
                    sizeOnClickItem.onclick(selectPosition,lastSelectPosition);
            }
        });

    }
    public  Size getItemSelect(){
        if (selectPosition!=-1){
        return  sizes.get(selectPosition);
        }else return null;
    }

    @Override
    public int getItemCount() {
        return sizes.size();
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.item_sl);
        }
    }
        public interface SizeOnClickItem{
        public void onclick(int pos,int lastPos);
        }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object clone=null;
        clone=super.clone();
        return clone;
    }
}

