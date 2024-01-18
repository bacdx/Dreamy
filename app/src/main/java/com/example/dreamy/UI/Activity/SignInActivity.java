package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dreamy.InterfaceRetrofit.IUser;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;

import com.example.dreamy.Model.User;
import com.example.dreamy.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    private User user;
    private Button btnSigin;
    private TextInputEditText edName,edEmail,edPass,edRePass;
    private TextView tvCheckErr,tvLogin;
    private String rePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
        btnSigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    user=new User(edName.getText().toString(),edEmail.getText().toString(),edPass.getText().toString());
                    rePass=edRePass.getText().toString();
               if(validate(user)==false) return;

                AlertDialog.Builder builder=new AlertDialog.Builder(SignInActivity.this);
                builder.setTitle("Thong bao")
                        .setMessage("Vui Long Doi")
                        .setCancelable(false);
                AlertDialog dialog=builder.create();
                dialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            dialog.dismiss();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
                retrofit2.Retrofit retrofit= RetrofitService.getClient();
                IUser userInterface=retrofit.create(IUser.class);
                Call<User> call=userInterface.signIn(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.code()==400){
                            dialog.setMessage("Email da duoc su dung");

                        }else if (response.code()==200) {
                            dialog.setMessage("Tạo tài khoản thành công");
                            dialog.dismiss();
                            Intent intent=new Intent(SignInActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }



                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        dialog.setMessage(t.getMessage());
                        Log.e("log",t.getMessage());
                    }
                });

            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignInActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    public boolean validate(User user){
        Log.e("check",user.getName());
        if(user.getName()==null||user.getName().equals("")){
            tvCheckErr.setText("Không được để trống name");
            return false;
        } else if (user.getEmail()==null||user.getEmail().equals("")) {
            tvCheckErr.setText("Không được để trống Email");
            return false;
        } else if (user.getPassword()==null||user.getPassword().equals("")) {
            tvCheckErr.setText("Không được để trống pass");
            return false;
        } else if (!rePass.equals(user.getPassword())) {
            tvCheckErr.setText("Không được để 2 mat khau khong khop");
            return false;
        }
        else {
            tvCheckErr.setText("");
            return true;
        }


    }
    private void init(){
        edName=findViewById(R.id.ed_username_sigin);
        edEmail=findViewById(R.id.ed_email_sigin);
        edPass=findViewById(R.id.ed_passsword_sigin);
        edRePass=findViewById(R.id.ed_repasssword_sigin);
         tvCheckErr=findViewById(R.id.tv_checkerr_signin) ;
        btnSigin=findViewById(R.id.btn_signin_signin);
        tvLogin=findViewById(R.id.tv_login_signin);

    }

}