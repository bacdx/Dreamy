package com.example.dreamy.UI.Activity.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dreamy.UI.Activity.Fragment.ChoLayHangFragment;
import com.example.dreamy.UI.Activity.Fragment.DaGiaoFragment;
import com.example.dreamy.UI.Activity.Fragment.DaHuyFragment;
import com.example.dreamy.UI.Activity.Fragment.DangGiaoFragment;
import com.example.dreamy.UI.Activity.HistoryActivity;

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
