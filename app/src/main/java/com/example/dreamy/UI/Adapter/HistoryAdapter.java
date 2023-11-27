package com.example.dreamy.UI.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dreamy.UI.Fragment.ChoLayHangFragment;
import com.example.dreamy.UI.Fragment.DaGiaoFragment;
import com.example.dreamy.UI.Fragment.DaHuyFragment;
import com.example.dreamy.UI.Fragment.DangGiaoFragment;

public class HistoryAdapter extends FragmentStateAdapter {

    public HistoryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ChoLayHangFragment();
            case 1:
                return new DangGiaoFragment();
            case 2:
                return new DaGiaoFragment();
            case 3:
                return new DaHuyFragment();
            default:
                return new ChoLayHangFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
