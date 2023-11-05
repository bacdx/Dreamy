package com.example.dreamy.UI.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.FeedBackActivity;
import com.example.dreamy.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());

        binding.btnDanhGia.setOnClickListener(view -> {
            startActivity(new Intent(requireActivity(), FeedBackActivity.class));
        });

        return binding.getRoot();
    }
}