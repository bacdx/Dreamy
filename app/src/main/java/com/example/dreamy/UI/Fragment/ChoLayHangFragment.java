package com.example.dreamy.UI.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamy.InterfaceRetrofit.ItemBillOnClick;
import com.example.dreamy.Manager.BillManager;
import com.example.dreamy.Model.Bill;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.HoaDonAdapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.prefs.PreferenceChangeListener;


public class ChoLayHangFragment extends Fragment implements PropertyChangeListener {

ItemBillOnClick itemBillOnClick ;
    private Button button;
    private String trangThai;
    private TextView tvTrangThai;
    private ImageView icTrangThai;
    private ArrayList<Bill> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private HoaDonAdapter hoaDonAdapter;
    private BillManager billManager;


    public ChoLayHangFragment() {
        // Required empty public constructor
    }
    public void addItemBillOnClick(ItemBillOnClick itemBillOnClick){
        this.itemBillOnClick=itemBillOnClick;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        billManager=BillManager.getInstant();
        billManager.resigListener(this);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        billManager.unResigListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_cho_lay_hang,container,false);
        Log.i("L","onCreateView");
        tvTrangThai=view.findViewById(R.id.tvTrangThai);
        icTrangThai=view.findViewById(R.id.icTrangThai);
        recyclerView=view.findViewById(R.id.rcv_don_hang);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        hoaDonAdapter=new HoaDonAdapter(getContext(),list,itemBillOnClick,trangThai);
        recyclerView.setAdapter(hoaDonAdapter);
        setLayout();
        return view ;
    }

    public void setLayout(){

        if(trangThai.equals(Bill.DANG_CHO_XAC_NHAN)){
            list=billManager.getBillsChoXacNhan();
            tvTrangThai.setText("Cho xac nhan");
            tvTrangThai.setTextColor(Color.parseColor("#AF393D"));
            icTrangThai.setImageResource(R.drawable.ic_trang_thai);

        }else if (trangThai.equals(Bill.DA_XAC_NHAN)){
            list=billManager.getBillsXacNhan();
            tvTrangThai.setText("Đã xác nhận");
            tvTrangThai.setTextColor(Color.parseColor("#AF393D"));
            icTrangThai.setImageResource(R.drawable.ic_trang_thai_dang_giao);
        }
        else if (trangThai.equals(Bill.DA_HOAN_THANH)){
            list=billManager.getBillHoanThanh();
            tvTrangThai.setText("Giao thành công");
            tvTrangThai.setTextColor(Color.parseColor("#219653"));
            icTrangThai.setImageResource(R.drawable.ic_trang_thai_da_giao);
        }
        else if (trangThai.equals(Bill.DA_HUY)){
            list=billManager.getBillHuy();
            tvTrangThai.setText("Đã Hủy");
            tvTrangThai.setTextColor(Color.parseColor("#AF393D"));
            icTrangThai.setImageResource(R.drawable.ic_trang_thai_da_huy);
        }



    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        trangThai=args.getString("trangthai");
        list=args.getParcelableArrayList("list");

    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("0")){
            Bill bill=(Bill) propertyChangeEvent.getNewValue();
            hoaDonAdapter.list.remove(bill);
            hoaDonAdapter.notifyDataSetChanged();
        }
        else if (propertyChangeEvent.getPropertyName().equals("1")){
            Toast.makeText(getContext(),"Đã Có sự thay đổi trên Server",Toast.LENGTH_LONG).show();
            hoaDonAdapter.notifyDataSetChanged();
        }else if(propertyChangeEvent.getPropertyName().equals("2")){
            Toast.makeText(getContext(),"Có lỗi kết nối",Toast.LENGTH_LONG).show();
            hoaDonAdapter.notifyDataSetChanged();
        }
    }
}