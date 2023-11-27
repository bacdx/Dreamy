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
import com.example.dreamy.UI.Activity.Model.History;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.LichSuHolder>{
    private  Context context;
    private List<History> list = new ArrayList<>();

    public LichSuAdapter(Context context, List<History> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LichSuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new LichSuHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuHolder holder, int position) {
            History history = list.get(position);
            if (history==null){
                return;
            }
            holder.item_name.setText(history.getTen());
        Picasso.get().load(history.getImg()).into(holder.item_img);
        holder.item_gia.setText(history.getGia()+"đ");
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class LichSuHolder extends RecyclerView.ViewHolder{
        private TextView item_name ,item_gia , item_mua;
        private ImageView item_img ;

        public LichSuHolder(@NonNull View itemView) {
            super(itemView);
            item_gia = itemView.findViewById(R.id.item_gia);
            item_name = itemView.findViewById(R.id.item_name);
            item_img = itemView.findViewById(R.id.item_img);

        }
    }
}
