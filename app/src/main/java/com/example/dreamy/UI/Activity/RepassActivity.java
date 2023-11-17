package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.dreamy.R;
import com.example.dreamy.databinding.ActivityRepassBinding;

public class RepassActivity extends AppCompatActivity {

    ActivityRepassBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRepassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(view -> {
            finish();
        });

        binding.btnXacNhan.setOnClickListener(view -> {
            {
                int temp = 0;

            /*if (Common.getPassWord(this@DoiMatKhauActivity) != binding.edtMkCu.text.toString()){
                temp++;
                binding.layoutLoiMkc.setVisibility(View.VISIBLE);
            }else{
                binding.layoutLoiMkc.visibility = View.GONE
            }*/
                if (binding.edtMkMoi.getText().toString().length() < 6){
                    temp++;
                    binding.tvLoiMkm.setText("Mật khẩu phải >= 6 ký tự");
                    binding.layoutLoiMkm.setVisibility(View.VISIBLE);
                }else{
                    binding.layoutLoiMkm.setVisibility(View.GONE);
                }
                if (binding.edtNhapLaiMk.getText().toString().length() < 6){
                    temp++;
                    binding.tvLoiReMk.setText("Mật khẩu phải >= 6 ký tự");
                    binding.layoutLoiReMk.setVisibility(View.VISIBLE);
                }else{
                    binding.layoutLoiReMk.setVisibility(View.GONE);
                }
                if (temp == 0){
                    if (binding.edtNhapLaiMk.getText().toString().equals(binding.edtMkMoi.getText().toString()) ){
                        binding.layoutLoiMkc.setVisibility(View.GONE);
                        binding.layoutLoiMkm.setVisibility(View.GONE);
                        binding.layoutLoiReMk.setVisibility(View.GONE);

                    }else{
                        binding.layoutLoiMkm.setVisibility(View.VISIBLE);
                        binding.layoutLoiReMk.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


    }
}