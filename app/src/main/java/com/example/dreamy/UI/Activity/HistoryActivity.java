package com.example.dreamy.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Adapter.HistoryAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HistoryActivity extends AppCompatActivity {
    private TabLayout mTablayout;
    private ViewPager2 mViewPager;
    private HistoryAdapter mHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mViewPager = findViewById(R.id.viewpager);
        mTablayout = findViewById(R.id.tablayout);

        mHistoryAdapter = new HistoryAdapter(this);
        mViewPager.setAdapter(mHistoryAdapter);

        new TabLayoutMediator(mTablayout, mViewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Chờ lấy hàng");
                    break;
                case 1:
                    tab.setText("Đang giao");
                    break;
                case 2:
                    tab.setText("Đã giao");
                    break;
                case 3:
                    tab.setText("Đã hủy");
                    break;

            }
        }).attach();

    }
}