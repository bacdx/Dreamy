package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamy.Manager.CommentManager;
import com.example.dreamy.Manager.DanhGiaManager;
import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Manager.ProfileManager;
import com.example.dreamy.Model.Comment;
import com.example.dreamy.Model.DanhGia;
import com.example.dreamy.Model.Product;
import com.example.dreamy.R;
import com.example.dreamy.databinding.ActivityFeedBackBinding;
import com.squareup.picasso.Picasso;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FeedBackActivity extends AppCompatActivity implements PropertyChangeListener {

    ActivityFeedBackBinding binding;
    private Intent intent;
    private Product product;
    private DanhGia danhGia;
    private Comment comment;


    private ProductManager productManager;
    private CommentManager commentManager;
    private DanhGiaManager danhGiaManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        comment=new Comment();
        danhGia=new DanhGia();
        productManager=ProductManager.getInstance(this);
        commentManager=CommentManager.getInstance();
        danhGiaManager=DanhGiaManager.getInstance();
        productManager.addListener(this);
        commentManager.addListener(this);
        danhGiaManager.getPropertyChangeSupport().addPropertyChangeListener(this);
        danhGia.setSoSao(DanhGia.FOUR);

        intent=getIntent();
        String maSp=intent.getStringExtra("idsp");
        productManager.getProduct(maSp);

        binding.btnBack.setOnClickListener(view -> {
            finish();
        });

        initRatingBar();

        binding.tvSend.setOnClickListener(view -> {
            String moTa = binding.edtMota.getText().toString();
            String chatLuong = binding.edtChatLuongSp.getText().toString();
            String material = binding.material.getText().toString();
            comment.setMaSanPham(product.getId());
            danhGia.setMaSanPham(product.getId());
            danhGia.setMaKhachHang(ProfileManager.getInstant().getMyAccount().getId());
            comment.setMaKhachHang(ProfileManager.getInstant().getMyAccount().getId());
            comment.setComment(material);
            commentManager.postComment(comment);
            danhGiaManager.postDanhGia(danhGia);
            if(check()){
                finish();
                intent =new Intent(this,MainActivity.class);
                startActivity(intent);
            }

//            Float rateNguoiBan = binding.ratingBarNguoiban.getRating();
//            Float rateShip = binding.ratingBarShip.getRating();

//            String chatLieu = binding.edtDanhGia.getText().toString();


            String kichThuoc = "Chuẩn";
//            if (binding.radioChat.isChecked()){
//                kichThuoc = "Chật";
//            } else if (binding.radioRong.isChecked()) {
//                kichThuoc = "Rộng";
//            }

            //todo
        });
    }

    private void initRatingBar() {
        binding.ratingBar.setNumStars(4);
        binding.ratingBar.setRating(4);


        binding.ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            if (b){
                if (v<=1f){
                    danhGia.getSoSao(DanhGia.ONE);
                    binding.ratingLabel.setText("Tệ");
                } else if (v<=2) {
                    danhGia.getSoSao(DanhGia.TWO);
                    binding.ratingLabel.setText("Không hài lòng");
                } else if (v<=3) {
                    danhGia.getSoSao(DanhGia.THREE);
                    binding.ratingLabel.setText("Bình thường");
                } else if (v<=4) {
                    danhGia.getSoSao(DanhGia.FOUR);
                    binding.ratingLabel.setText("Hài lòng");
                }
            }
        });

//        binding.ratingBarShip.setOnRatingBarChangeListener((ratingBar, v, b) ->{
//            if (b){
//                if (v<=1f){
//                    binding.ratingLabel1.setText("Tệ");
//                } else if (v<=2) {
//                    binding.ratingLabel1.setText("Không hài lòng");
//                } else if (v<=3) {
//                    binding.ratingLabel1.setText("Bình thường");
//                } else if (v<=4) {
//                    binding.ratingLabel1.setText("Hài lòng");
//                }else {
//                    binding.ratingLabel1.setText("Tuyệt vời");
//                }
//            }
//        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent pEvent) {
        if (pEvent.getPropertyName().equals("danhgiasp")){
            product=(Product) pEvent.getNewValue();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setUI();
                }
            });
        }


    }
    private void setUI(){
        Picasso.get().load(product.getImg()).into(binding.imgFeedback);
        binding.tvNameFeedback.setText(product.getTen());
    }
    private boolean check(){
        if(binding.material.getText().toString()==""){
            Toast.makeText(this,"Hãy Nhập Nội Dung !", Toast.LENGTH_LONG).show();
            return false;
        }
        else return true;
    }
}