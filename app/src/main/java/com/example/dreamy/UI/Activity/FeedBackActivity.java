package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dreamy.R;
import com.example.dreamy.databinding.ActivityFeedBackBinding;

public class FeedBackActivity extends AppCompatActivity {

    ActivityFeedBackBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(view -> {
            finish();
        });

        initRatingBar();

        binding.tvSend.setOnClickListener(view -> {
            Float rate = binding.ratingBar.getRating();
            Float rateNguoiBan = binding.ratingBarNguoiban.getRating();
            Float rateShip = binding.ratingBarShip.getRating();

            String chatLieu = binding.edtDanhGia.getText().toString();
            String moTa = binding.edtMota.getText().toString();
            String chatLuong = binding.edtChatLuongSp.getText().toString();
            String material = binding.material.getText().toString();

            String kichThuoc = "Chuẩn";
            if (binding.radioChat.isChecked()){
                kichThuoc = "Chật";
            } else if (binding.radioRong.isChecked()) {
                kichThuoc = "Rộng";
            }

            //todo
        });
    }

    private void initRatingBar() {
        binding.ratingBar.setRating(5f);
        binding.ratingBarNguoiban.setRating(5f);
        binding.ratingBarShip.setRating(5f);
        binding.ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            if (b){
                if (v<=1f){
                    binding.ratingLabel.setText("Tệ");
                } else if (v<=2) {
                    binding.ratingLabel.setText("Không hài lòng");
                } else if (v<=3) {
                    binding.ratingLabel.setText("Bình thường");
                } else if (v<=4) {
                    binding.ratingLabel.setText("Hài lòng");
                }else {
                    binding.ratingLabel.setText("Tuyệt vời");
                }
            }
        });
        binding.ratingBarNguoiban.setOnRatingBarChangeListener((ratingBar, v, b) ->{
            if (b){
                if (v<=1f){
                    binding.ratingLabelNguoiban.setText("Tệ");
                } else if (v<=2) {
                    binding.ratingLabelNguoiban.setText("Không hài lòng");
                } else if (v<=3) {
                    binding.ratingLabelNguoiban.setText("Bình thường");
                } else if (v<=4) {
                    binding.ratingLabelNguoiban.setText("Hài lòng");
                }else {
                    binding.ratingLabelNguoiban.setText("Tuyệt vời");
                }
            }
        });
        binding.ratingBarShip.setOnRatingBarChangeListener((ratingBar, v, b) ->{
            if (b){
                if (v<=1f){
                    binding.ratingLabel1.setText("Tệ");
                } else if (v<=2) {
                    binding.ratingLabel1.setText("Không hài lòng");
                } else if (v<=3) {
                    binding.ratingLabel1.setText("Bình thường");
                } else if (v<=4) {
                    binding.ratingLabel1.setText("Hài lòng");
                }else {
                    binding.ratingLabel1.setText("Tuyệt vời");
                }
            }
        });
    }
}