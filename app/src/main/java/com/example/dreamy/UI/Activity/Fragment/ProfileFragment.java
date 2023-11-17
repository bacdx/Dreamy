package com.example.dreamy.UI.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.ChinhSachBaoMatActivity;
import com.example.dreamy.UI.Activity.FeedBackActivity;
import com.example.dreamy.UI.Activity.HoTroActivity;
import com.example.dreamy.UI.Activity.MainActivity;
import com.example.dreamy.UI.Activity.ProfileActivity;
import com.example.dreamy.UI.Activity.RepassActivity;
import com.example.dreamy.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        ((MainActivity)requireActivity()).linearLayout.setVisibility(View.GONE);

        binding.btnHoSo.setOnClickListener(view -> {
            startActivity(new Intent(requireActivity(), ProfileActivity.class));
        });

        binding.btnDoiMK.setOnClickListener(view -> {
            startActivity(new Intent(requireActivity(), RepassActivity.class));
        });

        binding.btnCSBaoMat.setOnClickListener(view -> {
            startActivity(new Intent(requireActivity(), ChinhSachBaoMatActivity.class));
        });

        binding.btnHoTro.setOnClickListener(view -> {
            startActivity(new Intent(requireActivity(), HoTroActivity.class));
        });

        binding.btnDanhGia.setOnClickListener(view -> {
            startActivity(new Intent(requireActivity(), FeedBackActivity.class));
        });

        binding.btnDangXuat.setOnClickListener(view -> {
            Toast.makeText(requireContext(), "Đăng Xuất", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }
}