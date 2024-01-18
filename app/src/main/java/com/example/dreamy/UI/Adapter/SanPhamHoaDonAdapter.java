package com.example.dreamy.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Model.CategoryPhake;
import com.example.dreamy.UI.Utils.OrderStatus;
import com.example.dreamy.databinding.ItemSanPhamDonHangBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamHoaDonAdapter extends RecyclerView.Adapter<SanPhamHoaDonAdapter.ViewHolder> {

    Context context;
    public List<CategoryPhake> lists;
    String status;
    SanPhamCallBack callBack;

    public interface SanPhamCallBack{
        void onBtnClick();
    }

    public SanPhamHoaDonAdapter(Context context, List<CategoryPhake> lists, String status,SanPhamCallBack callBack) {
        this.context = context;
        this.lists = lists;
        this.status = status;
        this.callBack = callBack;
    }

    @Override
    public SanPhamHoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemSanPhamDonHangBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(SanPhamHoaDonAdapter.ViewHolder holder, int position) {
        CategoryPhake obj = lists.get(position);
//        Picasso.get().load(obj.img).into(holder.binding.imgSp);
//
//        holder.binding.tvTenSp.setText(obj.name);
//        holder.binding.tvSize.setText(obj.size);
//        holder.binding.tvSoLuong.setText("x"+obj.much);

//        if (status.equals(OrderStatus.CHO_VAN_CHUYEN)){
//            holder.binding.btnLienHe.setText("Liên Hệ Shop");
//        } else if (status.equals(OrderStatus.DANG_GIAO)) {
//            holder.binding.btnLienHe.setText("Đã Nhận Được Hàng");
//        }else{
//            holder.binding.btnLienHe.setText("Mua Lại");
//        }
//
//        holder.binding.btnLienHe.setOnClickListener(view -> {
//            callBack.onBtnClick();
//        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(ItemSanPhamDonHangBinding binding) {
            super(binding.getRoot());

        }
    }
}
