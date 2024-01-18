package com.example.dreamy.UI.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dreamy.InterfaceRetrofit.ItemBillOnClick;
import com.example.dreamy.Manager.BillManager;
import com.example.dreamy.Model.Bill;
import com.example.dreamy.UI.Fragment.ChoLayHangFragment;

import java.util.ArrayList;

public class HistoryAdapter extends FragmentStateAdapter {
    Fragment fragment;
    ItemBillOnClick itemBillOnClick;
    Bundle bundle;
    private ArrayList<Bill> list;
    private BillManager billManager;


    public HistoryAdapter(@NonNull FragmentActivity fragmentActivity, ItemBillOnClick itemBillOnClick) {
        super(fragmentActivity);
        this.itemBillOnClick=itemBillOnClick;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        billManager=BillManager.getInstant();
        switch (position){
            case 0:
                ChoLayHangFragment choLayHangFragment=new ChoLayHangFragment();
                bundle=new Bundle();
                bundle.putString("trangthai",Bill.DANG_CHO_XAC_NHAN);
                bundle.putParcelableArrayList("list",getListByTrangThai(Bill.DANG_CHO_XAC_NHAN));
                choLayHangFragment.setArguments(bundle);
                choLayHangFragment.addItemBillOnClick(itemBillOnClick);
                return choLayHangFragment;
            case 1:
                ChoLayHangFragment daxacNhanFragment= new ChoLayHangFragment();
                bundle=new Bundle();
                bundle.putString("trangthai",Bill.DA_XAC_NHAN);
                bundle.putParcelableArrayList("list",getListByTrangThai(Bill.DA_XAC_NHAN));
                daxacNhanFragment.setArguments(bundle);
                daxacNhanFragment.addItemBillOnClick(itemBillOnClick);
                return daxacNhanFragment;
            case 2:
                ChoLayHangFragment dangGiaoFragment= new ChoLayHangFragment();
                bundle=new Bundle();
                bundle.putString("trangthai",Bill.DANG_GIAO);
                bundle.putParcelableArrayList("list",getListByTrangThai(Bill.DANG_GIAO));
                dangGiaoFragment.setArguments(bundle);
                dangGiaoFragment.addItemBillOnClick(itemBillOnClick);
                return dangGiaoFragment;
            case 3:
                ChoLayHangFragment daGiaoHangFragment= new ChoLayHangFragment();
                bundle=new Bundle();
                bundle.putString("trangthai",Bill.DA_HOAN_THANH);
                bundle.putParcelableArrayList("list",getListByTrangThai(Bill.DA_HOAN_THANH));
                daGiaoHangFragment.setArguments(bundle);
                daGiaoHangFragment.addItemBillOnClick(itemBillOnClick);
                return daGiaoHangFragment;
            case 4:
                ChoLayHangFragment daHuyFragment= new ChoLayHangFragment();
                bundle=new Bundle();
                bundle.putString("trangthai",Bill.DA_HUY);
                bundle.putParcelableArrayList("list",getListByTrangThai(Bill.DA_HUY));
                daHuyFragment.setArguments(bundle);
                daHuyFragment.addItemBillOnClick(itemBillOnClick);
                return daHuyFragment;
            default:
                ChoLayHangFragment choLayHangFragment1 =new ChoLayHangFragment();
                bundle=new Bundle();
                bundle.putString("trangthai",Bill.DANG_CHO_XAC_NHAN);
                bundle.putParcelableArrayList("list",getListByTrangThai(Bill.DANG_CHO_XAC_NHAN));
                choLayHangFragment1.setArguments(bundle);
                choLayHangFragment1.addItemBillOnClick(itemBillOnClick);
                return choLayHangFragment1;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
    private ArrayList<Bill> getListByTrangThai(String trangThai){
       return billManager.getBillByState(trangThai);
    }


    public void setList(ArrayList<Bill> list) {
        this.list = list;
    }
}
