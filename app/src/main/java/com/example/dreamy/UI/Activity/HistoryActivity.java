package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dreamy.InterfaceRetrofit.IBillsInterface;
import com.example.dreamy.InterfaceRetrofit.ItemBillOnClick;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Manager.BillManager;
import com.example.dreamy.Manager.ProfileManager;
import com.example.dreamy.Model.Bill;
import com.example.dreamy.Model.InvoidDetals;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.HistoryAdapter;
import com.example.dreamy.UI.Dialog.BotomSheetDaiLogHistory;
import com.example.dreamy.UI.Fragment.ChoLayHangFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HistoryActivity extends AppCompatActivity implements ItemBillOnClick, PropertyChangeListener {

    private TabLayout mTablayout;
    private ImageView imageView;
    ProgressBar progressBar;
    private ViewPager2 mViewPager;
    private HistoryAdapter mHistoryAdapter;
    private FragmentManager fragmentManager;
    private BotomSheetDaiLogHistory botomSheetDaiLogHistory;
    private   ArrayList<Bill>arrayList=new ArrayList<>();
    Bundle bundle=new Bundle();

    private BillManager billManager;



//    private TabLayout.OnTabSelectedListener onTabSelectedListener=new TabLayout.OnTabSelectedListener() {
//        @Override
//        public void onTabSelected(TabLayout.Tab tab) {
//            switch (tab.getPosition()){
//                case 0:
//                    arrayList=getListByTrangThai(Bill.DANG_CHO_XAC_NHAN);
//                    bundle.putString("trangthai",Bill.DANG_CHO_XAC_NHAN);
//                    bundle.putParcelableArrayList("list", arrayList);
//                    mHistoryAdapter.createFragment(0).setArguments(bundle);
//                    (ChoLayHangFragment)mHistoryAdapter.createFragment(0).;
//                    break;
//
//                case 1:
//                    arrayList=getListByTrangThai(Bill.DA_XAC_NHAN);
//                    bundle.putString("trangthai",Bill.DA_XAC_NHAN);
//                    bundle.putParcelableArrayList("list", arrayList);
//                    mHistoryAdapter.setBundle(bundle);
//                    break;
//                case 2:
//                    arrayList=getListByTrangThai(Bill.DA_HOAN_THANH);
//                    bundle.putString("trangthai",Bill.DA_HOAN_THANH);
//                    bundle.putParcelableArrayList("list", arrayList);
//                    mHistoryAdapter.setBundle(bundle);
//                    break;
//                case 3:
//                    arrayList=getListByTrangThai(Bill.DA_HUY);
//                    bundle.putString("trangthai",Bill.DA_HUY);
//                    bundle.putParcelableArrayList("list", arrayList);
//                    mHistoryAdapter.setBundle(bundle);
//                    break;
//
//            }
//        }

//        @Override
//        public void onTabUnselected(TabLayout.Tab tab) {
//
//        }
//
//        @Override
//        public void onTabReselected(TabLayout.Tab tab) {
//
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        billManager=BillManager.getInstant();
        billManager.resigListener(this);
        mViewPager = findViewById(R.id.viewpager);
        mTablayout = findViewById(R.id.tablayout);
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        progressBar=findViewById(R.id.pgbar);
        fragmentManager=getSupportFragmentManager();
        mHistoryAdapter = new HistoryAdapter(this,this::onClick);
        billManager.getBills("1");
        billManager.resigListener(this);
//        mTablayout.addOnTabSelectedListener(onTabSelectedListener);
        botomSheetDaiLogHistory=new BotomSheetDaiLogHistory(this);

        imageView=findViewById(R.id.btn);



        findViewById(R.id.btnBack).setOnClickListener(view -> {
            finish();
        });
        getListBill();

    }



    private void getListBill(){
        Retrofit retrofit= RetrofitService.getClient();
        IBillsInterface iBillsInterface=retrofit.create(IBillsInterface.class);
        Call<ArrayList<Bill>> call=iBillsInterface.getBills("1");

        call.enqueue(new Callback<ArrayList<Bill>>() {
            @Override
            public void onResponse(Call<ArrayList<Bill>> call, Response<ArrayList<Bill>> response) {
                arrayList=response.body();
                mViewPager.setAdapter(mHistoryAdapter);
                mViewPager.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                new TabLayoutMediator(mTablayout, mViewPager, (tab, position) -> {

                    switch (position){
                        case 0:

                            tab.setText("Chờ xác nhận");
                            break;
                        case 1:
                            tab.setText("Đã Xác Nhận");
                            break;
                        case 2:
                            tab.setText("Đang Giao");
                            break;
                        case 3:
                            tab.setText("Đã Giao");
                            break;
                        case 4:
                            tab.setText("Đã Hủy");
                            break;
                        default:
                            tab.setText("Chờ xác nhận");
                            break;

                    }
                }).attach();
            }

            @Override
            public void onFailure(Call<ArrayList<Bill>> call, Throwable t) {

            }
        });




    }



    @Override
    public void onClick(Bill bill) {
        billManager.getBillDetal(bill);
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentByTag("bottom_history")==null){
            Fragment fragment=fragmentManager.findFragmentByTag("bottom_history");
            botomSheetDaiLogHistory.show(fragmentManager,"bottom_history");


        }else {

            fragmentTransaction.show(fragmentManager.findFragmentByTag("bottom_history"));

            fragmentTransaction.commit();
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTablayout.clearOnTabSelectedListeners();
        billManager.unResigListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("bill")){
            getListBill();
        }
        if (propertyChangeEvent.getPropertyName().equals("detalBill")){
            Bill bill=(Bill) propertyChangeEvent.getNewValue();
            Bundle bundle1=new Bundle();
            bundle1.putParcelable("Bill",bill);
            botomSheetDaiLogHistory.setArguments(bundle1);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    botomSheetDaiLogHistory.setData();
                }
            });


        }
    }

}