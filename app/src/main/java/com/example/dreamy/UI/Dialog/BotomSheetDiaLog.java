package com.example.dreamy.UI.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Database.SQL.Dao.CartDAO;
import com.example.dreamy.Database.SQL.Dao.FavoriteDAO;
import com.example.dreamy.Manager.CartManager;
import com.example.dreamy.Manager.PayController;
import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Manager.ProfileManager;
import com.example.dreamy.Model.Cart;
import com.example.dreamy.Model.Color;
import com.example.dreamy.Model.OderDetal;
import com.example.dreamy.Model.Order;
import com.example.dreamy.Model.ProductDetal;
import com.example.dreamy.Model.Size;
import com.example.dreamy.ProductDetalController;
import com.example.dreamy.R;
import com.example.dreamy.Model.Product;
import com.example.dreamy.UI.Activity.PayActivity;
import com.example.dreamy.UI.Adapter.SmallIitemSize;
import com.example.dreamy.UI.Adapter.SmallItemAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.play.core.integrity.p;
import com.squareup.picasso.Picasso;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPaySDK;

public class BotomSheetDiaLog extends BottomSheetDialogFragment implements SmallItemAdapter.IOnClick ,SmallIitemSize.SizeOnClickItem , ProductDetalController.Res , PropertyChangeListener {
//    private BottomSheetBehavior bottomSheetBehavior;
    private  TextView tvSum;
    private EditText edCount;
    private View view;
    private AppCompatButton btnCong, btnTru;
    private Button btnThanhToan;
    private RecyclerView recyclerViewColor,recyclerViewSize;
    private ArrayList<Color> colors=new ArrayList<>();
    private ArrayList<Size> sizes=new ArrayList<>();
    private  ArrayList<ProductDetal> productDetals;
    private SmallIitemSize smallIitemSizeAdapter;
    private SmallItemAdapter smallItemAdapter;
    private ImageView imageViewAvatar,imageViewClose;
    private  TextView tvCount;
    private PayController payController;
    private ProductDetalController productDetalController;
    int iCount=1;
    String tenSize="";
    String tenMau="";
    private  String err;
    private Product product;
    private ProductDetal productDetal=null;
    private OderDetal oderDetal;
    private double  amount=0;
    private Size size;
    private Color color;

    private int count=0;
    public static final String TYPE_BUY="buy";
    public static final String TYPE_CART="cart";
    private String type="";
    private CartDAO cartDAO;
    private CartManager cartManager;
    private ProfileManager profileManager=ProfileManager.getInstant();
    private ProductManager productManager;
    private View.OnClickListener addToCart=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            OderDetal oderDetal=new OderDetal();
            oderDetal.setId(product.getId());
            oderDetal.setSoLuong(String.valueOf(iCount));

            if(iCount>count){
                Toast.makeText(getContext(),"Hiện tại không có đủ hàng \n Xin vui lòng giảm số lượng",Toast.LENGTH_LONG).show();
            }
            else if (productDetal!=null){

                if(checkUpdate(product)){
                    Log.e("log1", productDetal.toString() );
                    if(cartDAO.update(String.valueOf(productDetal.getId()),Integer.valueOf(product.getId()),iCount,productDetal.getMaSize(), productDetal.getMaMau())>0){
                        Toast.makeText(getContext()," đổi thành công thành công",Toast.LENGTH_LONG).show();
                        cartManager.getListChiTiet();
                        cartManager.getListProduct();
                    }
                }else {
                    if( cartManager.addToCart(productDetal,iCount,productDetal.getMaSize(), productDetal.getMaMau())==true){
                        Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(),"Có lỗi với cơ sở dữ liệu",Toast.LENGTH_LONG).show();
                    }
                }



            }else {
                Toast.makeText(getContext(),"Vui lòng Kiểm Tra lại Các Lựa Chọn",Toast.LENGTH_LONG).show();
            }

        }
    };
    private View.OnClickListener buyProduct=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Order order=new Order();
            OderDetal  oderDetal=new OderDetal();
            oderDetal.setDonGia((long) product.getGia()*iCount);
            oderDetal.setMaCTSanPham(String.valueOf(productDetal.getId()));
            oderDetal.setMaSanPham(Integer.valueOf(product.getId()));
            order.setIdUser(profileManager.getMyAccount().getId());
            if(smallItemAdapter.getItemSelect().getTitle()!=null && smallIitemSizeAdapter.getItemSelect().getTitle()!=null ){
                tenSize=smallItemAdapter.getItemSelect().getTitle();
                tenMau=smallIitemSizeAdapter.getItemSelect().getTitle();
            }

            if(iCount>count){
                Toast.makeText(getContext(),"Hiện tại không có đủ hàng \n Xin vui lòng giảm số lượng",Toast.LENGTH_LONG).show();
            }
            else if (tenMau!=""&&tenSize!=""){
                thanhToan();
            }else {
                Toast.makeText(getContext(),"Vui lòng Kiểm Tra lại Các Lựa Chọn",Toast.LENGTH_LONG).show();
            }
        }
    };

    public BotomSheetDiaLog(String type) {
        this.type=type;
        productDetalController = new ProductDetalController(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet, container, false);
        cartManager=CartManager.getInstance(getContext());
        init();
        setUI();
        setType();
        smallIitemSizeAdapter=new SmallIitemSize(sizes,getContext(),this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),4, LinearLayoutManager.VERTICAL,false);
        recyclerViewSize.setLayoutManager(gridLayoutManager);
        recyclerViewSize.setAdapter(smallIitemSizeAdapter);
        smallItemAdapter=new SmallItemAdapter(colors,getContext(),this);
//      recyclerViewColor.setLayoutManager(gridLayoutManager);
        recyclerViewColor.setAdapter(smallItemAdapter);
        recyclerViewColor.findViewHolderForAdapterPosition(smallItemAdapter.getLastSelectPosition());
        onClick();
        return view;



    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        productManager=ProductManager.getInstance(getContext());
        productManager.addListener(this);

        product=(Product) bundle.get("Product");
        productManager.getColor(product);
        productManager.getSize(product);
        productDetalController=new ProductDetalController(this);
        productDetalController.getResounce(product);
        productDetalController.addLisent(this);
        oderDetal=new OderDetal();
        payController=PayController.getInstance();
        amount= product.getGia();
        productDetals=product.getProductDetals();



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productDetalController.disLisent(this);
        productManager.removeListener(this);

    }

    private void init() {
        btnTru = view.findViewById(R.id.btn_tru);
        btnCong = view.findViewById(R.id.btn_cong);
        edCount = view.findViewById(R.id.tv_count);
        tvCount=view.findViewById(R.id.tv_count_btns);
        tvSum=view.findViewById(R.id.tv_sum_price);
        imageViewAvatar=view.findViewById(R.id.avatar);
        imageViewClose=view.findViewById(R.id.close);
        btnThanhToan=view.findViewById(R.id.btnMua);
        recyclerViewColor=view.findViewById(R.id.rv_color_bottom_sheet);
        recyclerViewSize=view.findViewById(R.id.ryc_size_bottomsheet);
        Picasso.get().load(product.getImg()).into(imageViewAvatar);
        cartDAO =new CartDAO(getContext());


    }
    private void setUI(){
        tvSum.setText(String.valueOf(amount));
    }
    private void onClick(){
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DecimalFormat df = new DecimalFormat("#.##");
                iCount++;
                amount= iCount*product.getGia();
                edCount.setText(String.valueOf(iCount));
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
                    edCount.setText(String.valueOf(iCount));
                    tvSum.setText(df.format(amount)+"VND");

                }
            }
        });


        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(BotomSheetDiaLog.this);
                transaction.commit();
                BotomSheetDiaLog.this.onDestroy();
            }



        });


    }

    private void thanhToan() {
        oderDetal.setTenProduct(product.getTen());
        oderDetal.setSoLuong(String.valueOf(iCount));
        oderDetal.setTenMau(tenMau);
        oderDetal.setTenSize(tenSize);
        oderDetal.setMaCTSanPham(String.valueOf(productDetal.getId()));
        oderDetal.setMaSanPham(Integer.valueOf(productDetal.getMaSanPham()));
        Log.d("ph20234", String.valueOf(oderDetal.getMaSanPham()));
        oderDetal.setDonGia(amount);
        oderDetal.setUriImg(color.getImg());
        oderDetal.setMamau(color.getId());
        oderDetal.setMasize(size.getId());
        Intent intent=new Intent(getContext(), PayActivity.class);
        Bundle bundle=new Bundle();
        ArrayList<OderDetal> oderDetals=new ArrayList<>();
        oderDetals.add(oderDetal);
        bundle.putParcelableArrayList("oderDetals",oderDetals);
        intent.putExtra("data",bundle);
        getActivity().startActivity(intent);

//

    }



    @Override
    public void onClickItem(int position, int lastPosition) {
        SmallItemAdapter.ViewHolder viewHolder=(SmallItemAdapter.ViewHolder) recyclerViewColor.findViewHolderForAdapterPosition(position);
        SmallItemAdapter.ViewHolder lastViewHolder=(SmallItemAdapter.ViewHolder) recyclerViewColor.findViewHolderForAdapterPosition(lastPosition);
        if(position==lastPosition){
            viewHolder.textView.setBackgroundResource(R.drawable.bg_item_select);
            smallItemAdapter.setSelectPosition(-1);
            color=null;
        }

        else {
            if (lastPosition!=-1){
                lastViewHolder.textView.setBackgroundResource(R.drawable.bg_item_select);

            }


                viewHolder.textView.setBackgroundResource(R.drawable.bg_item_selected);
                color=smallItemAdapter.getItemSelect();

        }

        if(size!=null&&color!=null){
            setCount(Integer.valueOf(size.getId()),Integer.valueOf(color.getId()));
        }
        if(color==null||color.getImg()==null){
            Picasso.get().load(R.drawable.img_1).into(imageViewAvatar);
        }else {
            Picasso.get().load(color.getImg()).into(imageViewAvatar);

        }


    }

    @Override
    public void onclick(int pos, int lastPos) {
        SmallIitemSize.ViewHolder viewHolder=(SmallIitemSize.ViewHolder) recyclerViewSize.findViewHolderForAdapterPosition(pos);
        SmallIitemSize.ViewHolder lastViewHolder=(SmallIitemSize.ViewHolder) recyclerViewSize.findViewHolderForAdapterPosition(lastPos);
        if(pos==lastPos){
            viewHolder.textView.setBackgroundResource(R.drawable.bg_item_select);
            smallItemAdapter.setSelectPosition(-1);
            size=null;

        }
        else {
            if (lastPos!=-1){
                lastViewHolder.textView.setBackgroundResource(R.drawable.bg_item_select);
            }


                 viewHolder.textView.setBackgroundResource(R.drawable.bg_item_selected);
                size=smallIitemSizeAdapter.getItemSelect();

        }

        if(size!=null&&color!=null){
            setCount(Integer.valueOf(size.getId()),Integer.valueOf(color.getId()));
        }
    }


    @Override
    public void error(String t) {
            err=t;
            Toast.makeText(getContext(),t,Toast.LENGTH_LONG).show();
    }

    @Override
    public void resOK(ArrayList<ProductDetal> list) {

    }

    private  void setCount(int masize,int maMau){

        Log.i("setcount",String.valueOf(masize)+String.valueOf(maMau));
        if(smallItemAdapter.getItemSelect()!=null&&smallIitemSizeAdapter.getItemSelect()!=null){
            productDetals=product.getProductDetals();
            
            for (int i=0;i<productDetals.size();i++){
                ProductDetal productDetal1=productDetals.get(i);
                Log.i("detal",productDetals.get(i).toString());
                if(productDetal1.getMaMau()==maMau&&productDetal1.getMaSize()==masize){
                    productDetal=productDetal1;
                    productDetal.setSize(size);
                    productDetal.setColor(color);

                    break;
                }else {
                    productDetal=null;
                }
            }
            if(productDetal!=null){
                count=productDetal.getSoLuong();
            }else {
                count=0;
            }
            
            tvCount.setText("Kho: "+count);

        }
    }
    private void setType(){
        if (type.equals(BotomSheetDiaLog.TYPE_CART)){
            btnThanhToan.setText("THÊM GIỎ HÀNG");
            btnThanhToan.setOnClickListener(addToCart);
        }else if(type.equals(BotomSheetDiaLog.TYPE_BUY)){
            btnThanhToan.setOnClickListener(buyProduct);

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("listdetal")){
            Log.e("log",product.toString() );
            productDetals=product.getProductDetals();
        }
        if(propertyChangeEvent.getPropertyName().equals("size")){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    smallIitemSizeAdapter.setSizes(product.getSizes());
                }
            });


        }
        if(propertyChangeEvent.getPropertyName().equals("color")){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    smallItemAdapter.setColors(product.getColors());
                }
            });

        }
    }
    public boolean checkUpdate(Product product){
        ArrayList<Cart> carts=cartDAO.select();
        for (int i=0;i<carts.size();i++){
            if(carts.get(i).getMaSanPham()==Integer.valueOf(product.getId())){
                return true;
            }
        }
        return false;

    }
}
