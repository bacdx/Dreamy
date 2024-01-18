package com.example.dreamy.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Model.OderDetal;
import com.example.dreamy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.Holder> {

    private Context context;
    private ArrayList<OderDetal> oderDetals;

    public PayAdapter(Context context, ArrayList<OderDetal> oderDetals) {
        this.context = context;
        this.oderDetals = oderDetals;
    }

    @NonNull
    @Override
    public PayAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_pay,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PayAdapter.Holder holder, int position) {
        OderDetal oderDetal=oderDetals.get(position);
        holder.tvName.setText(oderDetal.getTenProduct());
        Picasso.get().load(oderDetal.getUriImg()).into(holder.imageView);
        holder.tvGia.setText("Price: "+String.valueOf(oderDetal.getDonGia())+" VND");
        holder.tvSize.setText("Size: "+oderDetal.getTenSize());
        holder.tvSoLuong.setText("SL: "+oderDetal.getSoLuong());
        holder.tvMau.setText("Color: "+oderDetal.getTenMau());
    }

    @Override
    public int getItemCount() {
        return oderDetals.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvName,tvGia,tvSoLuong,tvMau,tvSize;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.avatar_pay);
            tvMau=itemView.findViewById(R.id.tv_color_pay);
            tvSize=itemView.findViewById(R.id.tv_size_pay);
            tvSoLuong=itemView.findViewById(R.id.tv_soluong_pay4);
            tvGia=itemView.findViewById(R.id.tv_sum_price);
            tvName=itemView.findViewById(R.id.tv_namepr_pay);

        }
    }
}
