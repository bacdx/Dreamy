package com.example.dreamy.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.InterfaceRetrofit.Model.InvoiceDetails;
import com.example.dreamy.Model.Bill;
import com.example.dreamy.Model.InvoidDetals;
import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.FeedBackActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InvoiceDetailsAdapter extends RecyclerView.Adapter<InvoiceDetailsAdapter.ViewHolder> {

    private ArrayList<InvoidDetals> list;
    private Context context;
    private Bill bill;

    public InvoiceDetailsAdapter(Bill bill,ArrayList list, Context context) {
        this.bill=bill;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public InvoiceDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_bill_detal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceDetailsAdapter.ViewHolder holder, int position) {
        InvoidDetals invoidDetals=list.get(position);
        Picasso.get().load(invoidDetals.getImg()).into(holder.imgAvatar);
//        if (bill.trangThai.equals(Bill.DA_HOAN_THANH)){
            holder.button.setVisibility(View.VISIBLE);
        //}
        holder.tvName.setText(invoidDetals.getTenSanPham());
        holder.tvSize.setText("Size: "+invoidDetals.getSize());
        holder.tvNumber.setText("Số lượng: "+invoidDetals.getSoLuong());
        holder.tvPrice.setText("Tổng"+invoidDetals.getPrice());
        holder.tvColor.setText("Màu:"+invoidDetals.getColor());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FeedBackActivity.class);
                intent.putExtra("idsp",invoidDetals.getMaSP());
                context.startActivity(intent);
            }
        });

    }


    public ArrayList<InvoidDetals> getList() {
        return list;
    }

    public void setList(ArrayList<InvoidDetals> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return  list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView tvSize,tvNumber,tvPrice,tvName,tvColor;
        private Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar=itemView.findViewById(R.id.imgSp);
            tvNumber=itemView.findViewById(R.id.tvSoLuong);
            tvSize=itemView.findViewById(R.id.tvSize);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvColor=itemView.findViewById(R.id.tvColor);
            tvName=itemView.findViewById(R.id.tvTenSp);
            button=itemView.findViewById(R.id.btn_DanhGia_itemSpDonHang);

        }
    }

}
