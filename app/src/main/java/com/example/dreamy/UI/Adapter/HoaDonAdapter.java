//package com.example.dreamy.UI.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.dreamy.Model.Order;
//import com.example.dreamy.UI.Utils.OrderStatus;
//import com.example.dreamy.databinding.ItemDonHangBinding;
//
//import java.util.List;
//
//public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
//
//    Context context;
//    public List<Order> lists;
//    OrderStatus status;
//    HoaDonCallBack callBack;
//
//    public interface HoaDonCallBack{
//        void onBtnClick();
//    }
//
//    public HoaDonAdapter(Context context, List<Order> lists, HoaDonCallBack callBack) {
//        this.context = context;
//        this.lists = lists;
//        this.callBack = callBack;
//    }
//
//    @Override
//    public HoaDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ViewHolder(ItemDonHangBinding.inflate(LayoutInflater.from(parent.getContext()),
//                parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(HoaDonAdapter.ViewHolder holder, int position) {
//        Order obj = lists.get(position);
//
//        holder.binding.tvMaDon.setText("Mã đơn hàng: "+obj.code);

//        if (obj.status.equals(status.CHO_VAN_CHUYEN)){
//            holder.binding.tvTrangThai.setText("Chờ vận chuyển");
//            holder.binding.tvTrangThai.setTextColor(Color.parseColor("#FC891E"));
//            holder.binding.icTrangThai.setImageResource(R.drawable.ic_trang_thai);
//        } else if (obj.status.equals(status.DANG_GIAO)) {
//            holder.binding.tvTrangThai.setText("Đang giao");
//            holder.binding.tvTrangThai.setTextColor(Color.parseColor("#F77797"));
//            holder.binding.icTrangThai.setImageResource(R.drawable.ic_trang_thai_dang_giao);
//        }else if (obj.status.equals(status.GIAO_THANH_CONG)) {
//            holder.binding.tvTrangThai.setText("Giao thành công");
//            holder.binding.tvTrangThai.setTextColor(Color.parseColor("#219653"));
//            holder.binding.icTrangThai.setImageResource(R.drawable.ic_trang_thai_da_giao);
//        }else{
//            holder.binding.tvTrangThai.setText("Đã Hủy");
//            holder.binding.tvTrangThai.setTextColor(Color.parseColor("#AF393D"));
//            holder.binding.icTrangThai.setImageResource(R.drawable.ic_trang_thai_da_huy);
//        }
//
//        SanPhamHoaDonAdapter adapter = new SanPhamHoaDonAdapter(context, obj.Category, obj.status, () -> {
//            callBack.onBtnClick();
//        });
//
//        holder.binding.rcvItemSanPham.setAdapter(adapter);

//        if (position == lists.size()-1){
//            holder.binding.viewLine.setVisibility(View.GONE);
//        }else {
//            holder.binding.viewLine.setVisibility(View.VISIBLE);
//        }
//    }

//    @Override
//    public int getItemCount() {
//        return lists.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private ItemDonHangBinding binding;
//
//        public ViewHolder(ItemDonHangBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//    }
//}
