package com.example.dreamy.UI.Dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Interface.CreateOrder;
import com.example.dreamy.Interface.RetrofitService;
import com.example.dreamy.Manager.PayController;
import com.example.dreamy.Model.Cart;
import com.example.dreamy.Model.Color;
import com.example.dreamy.Model.OderDetal;
import com.example.dreamy.Model.Order;
import com.example.dreamy.Model.Size;
import com.example.dreamy.R;
import com.example.dreamy.Model.Product;
import com.example.dreamy.UI.Activity.MainActivity;
import com.example.dreamy.UI.Activity.PayActivity;
import com.example.dreamy.UI.Adapter.SmallIitemSize;
import com.example.dreamy.UI.Adapter.SmallItemAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class BotomSheetDiaLog extends BottomSheetDialogFragment implements SmallItemAdapter.IOnClick ,SmallIitemSize.SizeOnClickItem {
//    private BottomSheetBehavior bottomSheetBehavior;
    private  TextView tvCount,tvSum;
    private View view;
    private AppCompatButton btnCong, btnTru;
    private Button btnThanhToan;
    private RecyclerView recyclerViewColor,recyclerViewSize;
    private ArrayList<Color> colors=new ArrayList<>();
    private ArrayList<Size> sizes=new ArrayList<>();
    private SmallIitemSize smallIitemSizeAdapter;
    private SmallItemAdapter smallItemAdapter;
    private ImageView imageViewAvatar,imageViewClose;
    private PayController payController;
    int iCount=1;
    String tenSize="";
    String tenMau="";
    private Product product;
    private OderDetal oderDetal;
    private double  amount=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet, container, false);

        sizes= getArguments().getParcelableArrayList("sizes");
        colors= getArguments().getParcelableArrayList("colors");

        intit();
        smallIitemSizeAdapter=new SmallIitemSize(sizes,getContext(),this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),4, LinearLayoutManager.VERTICAL,false);
        recyclerViewSize.setLayoutManager(gridLayoutManager);
        recyclerViewSize.setAdapter(smallIitemSizeAdapter);
        smallItemAdapter=new SmallItemAdapter(colors,getContext(),this);
//        recyclerViewColor.setLayoutManager(gridLayoutManager);
        recyclerViewColor.setAdapter(smallItemAdapter);



        recyclerViewColor.findViewHolderForAdapterPosition(smallItemAdapter.getLastSelectPosition());
        onClick();
        return view;



    }
    public static BotomSheetDiaLog newInstance() {
        BotomSheetDiaLog fragment = new BotomSheetDiaLog();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        product=(Product) bundle.get("Product");
        oderDetal=new OderDetal();
        payController=PayController.getInstance();

        ZaloPaySDK.init(2553, Environment.SANDBOX);

    }

    private void intit() {
        btnTru = view.findViewById(R.id.btn_tru);
        btnCong = view.findViewById(R.id.btn_cong);
        tvCount=view.findViewById(R.id.tv_count);
        tvSum=view.findViewById(R.id.tv_sum_price);
        imageViewAvatar=view.findViewById(R.id.avatar);
        imageViewClose=view.findViewById(R.id.close);
        btnThanhToan=view.findViewById(R.id.btnMua);
        recyclerViewColor=view.findViewById(R.id.rv_color_bottom_sheet);
        recyclerViewSize=view.findViewById(R.id.ryc_size_bottomsheet);
        Picasso.get().load(product.getImg()).into(imageViewAvatar);


    }
    private void onClick(){
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat df = new DecimalFormat("#.##");
                iCount++;
                amount= iCount*product.getGia();
                tvCount.setText(String.valueOf(iCount));
                tvSum.setText(df.format(amount)+"VND");
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat df = new DecimalFormat("#.##");
                if(iCount>1) {
                    iCount--;
                    amount= iCount*product.getGia();
                    tvCount.setText(String.valueOf(iCount));
                    tvSum.setText(df.format(amount)+"VND");

                }
            }
        });
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissAllowingStateLoss();
                dismiss();
            }



        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order order=new Order();
                OderDetal  oderDetal=new OderDetal();
                oderDetal.setDonGia((long) product.getGia()*iCount);
                oderDetal.setMaSanPham(product.getId());

                order.setIdUser("1");
                if(smallItemAdapter.getItemSelect().getTitle()!=null && smallIitemSizeAdapter.getItemSelect().getTitle()!=null ){
                    tenSize=smallItemAdapter.getItemSelect().getTitle();
                    tenMau=smallIitemSizeAdapter.getItemSelect().getTitle();
                }

                if (tenMau!=""&&tenSize!=""){
                    thanhToan();
                }else {
                    Toast.makeText(getContext(),"Vui lòng Kiểm Tra lại Các Lựa Chọn",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void thanhToan() {
        oderDetal.setSoLuong(String.valueOf(iCount));
        oderDetal.setTenMau(tenMau);
        oderDetal.setTenSize(tenSize);
        oderDetal.setMaSanPham(product.getId());
        oderDetal.setDonGia(amount);

        Intent intent=new Intent(getContext(), PayActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("oderDetal",oderDetal);
        intent.putExtra("data",bundle);
        getActivity().startActivity(intent);

//        try {
//            payController.Pay(String.valueOf(amount),oderDetal);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        String token = "sfdsfggfhdfgxd";
        ZaloPaySDK.getInstance().payOrder(getActivity(), token, "demozpdk://app", new PayOrderListener() {
            @Override
            public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Payment Success")
                                .setMessage(String.format("TransactionId: %s - TransToken: %s", transactionId, transToken))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();
                    }

                });

            }

            @Override
            public void onPaymentCanceled(String zpTransToken, String appTransID) {
                new AlertDialog.Builder(getContext())
                        .setTitle("User Cancel Payment")
                        .setMessage(String.format("zpTransToken: %s \n", zpTransToken))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNegativeButton("Cancel", null).show();
            }

            @Override
            public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Payment Fail")
                        .setMessage(String.format("ZaloPayErrorCode: %s \nTransToken: %s", zaloPayError.toString(), zpTransToken))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNegativeButton("Cancel", null).show();
            }
        });


    }



    @Override
    public void onClickItem(int position, int lastPosition) {
       String uriColor=smallItemAdapter.getItemSelect().getImg();
        SmallItemAdapter.ViewHolder viewHolder=(SmallItemAdapter.ViewHolder) recyclerViewColor.findViewHolderForAdapterPosition(position);
        SmallItemAdapter.ViewHolder lastViewHolder=(SmallItemAdapter.ViewHolder) recyclerViewColor.findViewHolderForAdapterPosition(lastPosition);
        if(position==lastPosition){
            viewHolder.textView.setBackgroundResource(R.drawable.bg_item_select);

        }
        else {
            if (lastPosition!=-1){
                lastViewHolder.textView.setBackgroundResource(R.drawable.bg_item_select);
            }

                viewHolder.textView.setBackgroundResource(R.drawable.bg_item_selected);
                 Picasso.get().load(uriColor).into(imageViewAvatar);

        }


    }

    @Override
    public void onclick(int pos, int lastPos) {
        SmallIitemSize.ViewHolder viewHolder=(SmallIitemSize.ViewHolder) recyclerViewSize.findViewHolderForAdapterPosition(pos);
        SmallIitemSize.ViewHolder lastViewHolder=(SmallIitemSize.ViewHolder) recyclerViewSize.findViewHolderForAdapterPosition(lastPos);
        if(pos==lastPos){
            viewHolder.textView.setBackgroundResource(R.drawable.bg_item_select);
        }
        else {
            if (lastPos!=-1){
                lastViewHolder.textView.setBackgroundResource(R.drawable.bg_item_select);
            }

                 viewHolder.textView.setBackgroundResource(R.drawable.bg_item_selected);

        }
    }
}
