package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import com.example.dreamy.InterfaceRetrofit.ILogin;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Manager.ProfileManager;
import com.example.dreamy.Model.User;
import com.example.dreamy.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity   {
    private static final  String ACOUNT="com.example.dreamyversion2.save_data";
    private ProfileManager profileManager=ProfileManager.getInstant();
   private EditText edEmail,edPassWord;
   private CheckBox ckReMember;
   private TextView tvCheckErr,btnsignIn;
   private Button buttonLogin;
   private String sEmail,sPassWord;
   private View.OnClickListener loginOnClick=new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           sEmail=edEmail.getText().toString().trim();
           sPassWord=edPassWord.getText().toString().trim();
           if(validate()==true){

               AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
               builder.setTitle("Thong bao")
                       .setMessage("Vui Long Doi")
                       .setCancelable(false);
               AlertDialog dialog=builder.create();
               dialog.show();
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           Thread.sleep(11000);
                           dialog.dismiss();
                       } catch (InterruptedException e) {
                           throw new RuntimeException(e);
                       }
                   }
               }).start();

               retrofit2.Retrofit retrofit= RetrofitService.getClient();
               ILogin iLogin=retrofit.create(ILogin.class);
               User user=new User();
               user.setEmail(sEmail);
               user.setPassword(sPassWord);
               Call<User> call=iLogin.logIn(user);
               call.timeout();
               call.enqueue(new Callback<User>() {
                   @Override
                   public void onResponse(Call<User> call, Response<User> response) {
                       if(response.code()==200){
                            dialog.setMessage("Dang nhap thanh cong");
                            dialog.dismiss();
                            User userRep=(User) response.body();
                            profileManager.setMyAccount(userRep);
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            if(ckReMember.isChecked()==true){
                                saveData(sEmail,sPassWord);
                            }else {
                                saveData("","");
                            }

                            startActivity(intent);

                       }else if (response.code()==400){
                           dialog.setMessage("Tài khoản hoặc mật khẩu không chính xác");
                       }

                   }

                   @Override
                   public void onFailure(Call<User> call, Throwable t) {

                       dialog.setMessage(t.getMessage());
                   }
               });

           }
       }

   };
   private View.OnClickListener signInOnclick=new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent intent=new Intent(LoginActivity.this,SignInActivity.class);
           startActivity(intent);
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //innit
        edEmail=(EditText) findViewById(R.id.tv_user_login);
        edPassWord=(EditText) findViewById(R.id.tv_pasword_login);
        ckReMember=(CheckBox) findViewById(R.id.ck_remember_login);
        buttonLogin=(Button) findViewById(R.id.btn_login_login);
        tvCheckErr=(TextView) findViewById(R.id.check_err_login);
        btnsignIn=(TextView) findViewById(R.id.tv_signin_login);
        buttonLogin.setOnClickListener(loginOnClick);
        btnsignIn.setOnClickListener(signInOnclick);
        ckReMember.setChecked(true);
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        edEmail.setText(sharedPref.getString("email",""));
        edPassWord.setText(sharedPref.getString("password",""));
    }
    private  boolean validate(){

        if(sEmail==null|| sEmail.equals("")){
            tvCheckErr.setText("Không được để trống Email ");
            return  false;
        } else if (sPassWord==null|| sPassWord.equals("")) {
            tvCheckErr.setText("Không được để trống PassWord  ");
            return  false;
        }else {
            tvCheckErr.setText("");
            return true;
        }
    }

    private void saveData(String a,String b){

        SharedPreferences sharedPref = getApplication().getSharedPreferences(
               getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", a);
        editor.putString("password", b);
        editor.apply();
    }


}