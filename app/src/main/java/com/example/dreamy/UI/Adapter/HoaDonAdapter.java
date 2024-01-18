package com.example.dreamy.UI.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.InterfaceRetrofit.ItemBillOnClick;
import com.example.dreamy.Manager.BillManager;
import com.example.dreamy.Model.Bill;
import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.FeedBackActivity;
import com.example.dreamy.UI.Utils.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {

    Context context;
    public List<Bill> list;
    OrderStatus status;
  ItemBillOnClick callBack;
  BillManager billManager;
private String trangThai;

    public HoaDonAdapter(Context context, ArrayList<Bill> list, ItemBillOnClick callBack,String trangThai) {
        this.context = context;
        this.trangThai=trangThai;
        this.list = list;
        this.callBack=callBack;
        billManager=BillManager.getInstant();
    }

    @Override
    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(context).inflate(R.layout.item_san_pham_don_hang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HoaDonAdapter.ViewHolder holder, int position) {
        Bill bill = list.get(position);
        holder.tvName.setText("ID: "+bill.id);
        holder.tvSoluong.setText("Địa chỉ giao: "+bill.DiaChi);
        holder.tvColor.setText("Thời gian đặt: "+bill.thoiGian);
        holder.tvSize.setText("Tổng tiền: "+bill.tongTien);
        holder.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn muốn hủy đơn");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                bill.setTrangThai(Bill.DANG_HUY);
                               billManager.HuyBills(bill);
                               notifyDataSetChanged();
                               dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        if (trangThai.equals(Bill.DANG_CHO_XAC_NHAN)){
            holder.btnHuy.setVisibility(View.VISIBLE);
            holder.progressBar.setVisibility(View.GONE);

        }
        if (trangThai.equals(Bill.DA_HOAN_THANH)){
            holder.progressBar.setVisibility(View.GONE);
            holder.btnDanhGia.setVisibility(View.VISIBLE);

        }
        if (trangThai.equals(Bill.DANG_HUY)){
            holder.btnHuy.setVisibility(View.GONE);
            holder.progressBar.setVisibility(View.VISIBLE);
        }
        holder.btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FeedBackActivity.class);
                context.startActivity(intent);
            }
        });
//        holder.tvPrice.setText("Trạng thái đơn: "+bill.trangThai);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onClick(bill);
            }
        });




//        SanPhamHoaDonAdapter adapter = new SanPhamHoaDonAdapter(context, obj., obj.status, () -> {
//            callBack.onBtnClick();
//        });

//        holder.binding.rcvItemSanPham.setAdapter(adapter);

//        if (position == lists.size()-1){
//            holder.binding.viewLine.setVisibility(View.GONE);
//        }else {
//            holder.viewLine.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvSize,tvColor,tvSoluong,tvPrice;
        Button btnHuy,btnDanhGia;
        View item;
        ImageView imgAvatar;
        ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item=(LinearLayout)itemView.findViewById(R.id.item_donhang);
            tvName=itemView.findViewById(R.id.tvTenSp);
            tvSize=itemView.findViewById(R.id.tvSize);
            tvColor=itemView.findViewById(R.id.tvColor);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvSoluong=itemView.findViewById(R.id.tvSoLuong);
            imgAvatar=itemView.findViewById(R.id.imgSp);
            btnHuy=itemView.findViewById(R.id.btn_huy_itemSpDonHang);
            btnDanhGia=itemView.findViewById(R.id.btn_DanhGia_itemSpDonHang);
            progressBar=itemView.findViewById(R.id.ProBag);
        }
    }

}
