package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dreamy.R;

public class FeedBackActivity extends AppCompatActivity {

    private EditText ed_material;

    private TextView tv_ratingsp;
    private RadioButton rdo_chuan, rdo_chat, rdo_rong;
    private RatingBar rtb_quanao, rtb_nguoiban, rtb_ship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        ed_material = findViewById(R.id.material);
        rtb_quanao = findViewById(R.id.ratingBar);
        rtb_nguoiban = findViewById(R.id.ratingBar_nguoiban);
        rtb_ship = findViewById(R.id.ratingBar_ship);

        rdo_chuan = findViewById(R.id.radio_chuan);
        rdo_chat = findViewById(R.id.radio_chat);
        rdo_rong = findViewById(R.id.radio_rong);


        // Đặt đánh giá mặc định là 5 sao
        rtb_quanao.setRating(5);
        rtb_nguoiban.setRating(5);
        rtb_ship.setRating(5);


    }

    public void onSendClick(View view) {
        // lấy ánh giá của người dùng
        float rating_quanao = rtb_quanao.getRating();
        String description = tv_ratingsp.getText().toString();

    }


    public void onCameraClick(View view) {
        //Bắt sk camera
    }


    public void onVideoClick(View view) {
        //Bắt sk video
    }
}