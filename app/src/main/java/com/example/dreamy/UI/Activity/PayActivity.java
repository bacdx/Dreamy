package com.example.dreamy.UI.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamy.Database.SQL.Dao.CartDAO;
import com.example.dreamy.InterfaceRetrofit.IBillsInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Manager.CartManager;
import com.example.dreamy.Manager.PayController;
import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Manager.ProfileManager;
import com.example.dreamy.Model.Bill;
import com.example.dreamy.Model.Cart;
import com.example.dreamy.Model.OderDetal;
import com.example.dreamy.Model.User;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.PayAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class PayActivity extends AppCompatActivity implements PropertyChangeListener {
    private TextInputEditText textInputEditText;
    private ArrayList<OderDetal>  oderDetals  ;
    private RecyclerView recyclerView;
    private PayAdapter payAdapter;
    private PayController  payController=PayController.getInstance();
    private TextView tvSum;
    private Button button;
    private CartManager cartManager;
    private CartDAO cartDAO;
    private ProfileManager profileManager;
    private String PhuongThuc="0";
    private final View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                payController.Pay(oderDetals,textInputEditText.getText().toString(),PhuongThuc);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payController.addPropertyChangeListener(this);
        oderDetals=new ArrayList<>();
        cartManager=CartManager.getInstance(this);
        cartDAO=new CartDAO(this);
        Bundle bundle=getIntent().getBundleExtra("data");
        oderDetals= bundle.getParcelableArrayList("oderDetals");

        setContentView(R.layout.activity_pay);
        tvSum=findViewById(R.id.tvSum_Pay);
        tvSum.setText("Tổng tiền : ");
        recyclerView=findViewById(R.id.rcy_pay);
        button=findViewById(R.id.btn_pay_pay);
        button.setOnClickListener(onClickListener);
        textInputEditText=findViewById(R.id.ed_address_sum);
        profileManager=ProfileManager.getInstant();
        setDiaChi();

      ZaloPaySDK.init(553, Environment.SANDBOX);


      //setrcyView
        setRecyclerView();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);

    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("Pay")){
                String[] rep =(String[]) propertyChangeEvent.getNewValue();
                if(!rep.equals("error")){
                    PayZalo(rep[0],rep[1]);
                }
        }
    }

    @Override
    protected void onDestroy() {
        payController.removePropertyChangeListener(this);
        super.onDestroy();
    }
    private void setDiaChi(){
        if (profileManager.getMyAccount()==null){
            Log.i("profileManager","null");
        }
        User user=profileManager.getMyAccount();
        if (user!=null){
            if(user.getEmail()!=""){
                textInputEditText.setText(user.getEmail());

            }
        }
    }

    private void setRecyclerView(){
        payAdapter=new PayAdapter(getApplicationContext(),oderDetals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(payAdapter);
    }



    private void PayZalo(String token,String id){
        ZaloPaySDK.getInstance().payOrder(PayActivity.this, token, "demozpdk://app", new PayOrderListener() {
            @Override
            public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Retrofit retrofit=RetrofitService.getClient();
                        IBillsInterface iBillsInterface=retrofit.create(IBillsInterface.class);
                       Call<Void> call=iBillsInterface.sendRes(Bill.OK,id);
                       call.enqueue(new Callback<Void>() {
                           @Override
                           public void onResponse(Call<Void> call, Response<Void> response) {

                           }

                           @Override
                           public void onFailure(Call<Void> call, Throwable t) {

                           }
                       });

                        new AlertDialog.Builder(PayActivity.this)
                                .setTitle("Payment Success")
                                .setMessage(String.format("TransactionId: %s - TransToken: %s", transactionId, transToken))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();

                                ArrayList<Cart> carts=cartManager.getCarts();
                                for (int i=0;i<carts.size();i++){
                                    Cart cart=carts.get(i);
                                    if(cart.getTrangThai()==1){
                                        cartDAO.delete(cart.getMaChiTiet());
                                        carts.remove(i);

                                    }
                                }

                        try {
                            Thread.sleep(2);
                            Intent intent=new Intent(PayActivity.this, HistoryActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }


                    }

                });

            }

            @Override
            public void onPaymentCanceled(String zpTransToken, String appTransID) {
                new AlertDialog.Builder(PayActivity.this)
                        .setTitle("Thanh toán thành công")


                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });


                Retrofit retrofit=RetrofitService.getClient();
                IBillsInterface iBillsInterface=retrofit.create(IBillsInterface.class);
                Call call= iBillsInterface.sendRes(Bill.CANCLE,id);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                new AlertDialog.Builder(PayActivity.this)
                        .setTitle("Đã hủy thao tác thanh toán")
                        .setMessage(String.format("zpTransToken: %s \n", zpTransToken))

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Cancel", null).show();
            }

            @Override
            public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {

                Retrofit retrofit=RetrofitService.getClient();
                IBillsInterface iBillsInterface=retrofit.create(IBillsInterface.class);
                Call call= iBillsInterface.sendRes(Bill.CANCLE,id);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                new AlertDialog.Builder(PayActivity.this)
                        .setTitle("Giao Dịch Thất Bại")
                        .setMessage(String.format("ZaloPayErrorCode: %s \nTransToken: %s", zaloPayError.toString(), zpTransToken))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Cancel", null).show();
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

}