package com.example.dreamy.UI.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamy.Model.CategoryPhake;
import com.example.dreamy.databinding.FragmentChoLayHangBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ChoLayHangFragment extends Fragment {

    FragmentChoLayHangBinding  binding;

    public ChoLayHangFragment() {
        // Required empty public constructor
    }
    public static ChoLayHangFragment newInstance(String param1, String param2) {
        ChoLayHangFragment fragment = new ChoLayHangFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChoLayHangBinding.inflate(getLayoutInflater());
//
//        HoaDonAdapter adapter = new HoaDonAdapter(getContext(),getOrders(), () ->{
//            Toast.makeText(requireContext(), "Do Something", Toast.LENGTH_SHORT).show();
////        });
//        binding.rcvDonHang.setAdapter(adapter);

        return binding.getRoot();
    }

//    private List<Order> getOrders(){
//        List<Order> lists = new ArrayList<>();
//        lists.add(new Order("","VN-2145",OrderStatus.CHO_VAN_CHUYEN,getRandom()));
//        lists.add(new Order("","VN-6585",OrderStatus.CHO_VAN_CHUYEN,getRandom()));
//        lists.add(new Order("","VN-5984",OrderStatus.CHO_VAN_CHUYEN,getRandom()));
//        return lists;
//    }

//    private List<CategoryPhake> getRandom(){
//        List<CategoryPhake> lists = new ArrayList<>();
//        List<CategoryPhake> defaulList = getCategoryPhakes();
//        Random random = new Random();
//
//        for (int i = 0; i < 3; i++) {
//            int position = random.nextInt(defaulList.size() - 1);
//            lists.add(defaulList.get(position));
//            defaulList.remove(position);
//        }
//
//        return lists;
//    }

//    private List<CategoryPhake> getCategoryPhakes(){
//        List<CategoryPhake> lists = new ArrayList<>();
//        lists.add(new CategoryPhake("","Váy tím đính viền ngọc cổ vuông","","","S","","1", "","https://thoitrangtadi.com/wp-content/uploads/2023/08/vay-tim-dinh-vien-ngoc-co-vuong-v3973-600x600.jpg"));
//        lists.add(new CategoryPhake("","Váy hoa nhí tay cánh tiên","","","M","","1","","https://thoitrangtadi.com/wp-content/uploads/2023/08/vay-hoa-nhi-tay-canh-tien-v3983-600x600.jpg"));
//        lists.add(new CategoryPhake("","Váy đen công sở cổ v dáng xòe sang trọng","","","L","","1","","https://thoitrangtadi.com/wp-content/uploads/2023/08/vay-den-cong-so-co-v-dang-xoe-sang-trong-v3980-600x600.jpg"));
//        lists.add(new CategoryPhake("","Váy xanh đai eo cổ vuông sang trọng","","","M","","2","","https://thoitrangtadi.com/wp-content/uploads/2023/08/vay-xanh-dai-eo-co-vuong-sang-trong-v4076-1-600x600.jpg"));
//        lists.add(new CategoryPhake("","Váy đen xòe cổ V tay bèo","","","M","","2","","https://thoitrangtadi.com/wp-content/uploads/2023/08/vay-den-xoe-co-v-tay-beo-v4011-600x600.jpg"));
//        lists.add(new CategoryPhake("","Váy kẻ đen trắng cổ sơ mi dáng dài","","","L","","1","","https://thoitrangtadi.com/wp-content/uploads/2023/08/vay-ke-den-trang-co-so-mi-dang-dai-v4018-600x600.jpg"));
//        return lists;
//    }
}