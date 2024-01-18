package com.example.dreamy.UI.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.Database.SQL.Dao.CartDAO;
import com.example.dreamy.Model.Cart;
import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.ProductDetal;
import com.example.dreamy.R;
import com.example.dreamy.UI.Dialog.BotomSheetDiaLog;
import com.example.dreamy.UI.Dialog.BottomSheetDialogVoucher;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.play.core.integrity.p;
import com.squareup.picasso.Picasso;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder>{
    private Context context;
    private ArrayList<Cart> cartList = new ArrayList<>();
    private BotomSheetDiaLog botomSheetDiaLog;
    private FragmentManager fragmentManager;
    private CartDAO cartDAO;
    private ChangePrice changePrice;
    private DecimalFormat df = new DecimalFormat("#.##");
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    public CartAdapter(Context context, ArrayList<Cart> cartList,ChangePrice changePrice) {
        this.context = context;
        this.cartList = cartList;
        this.changePrice=changePrice;
        cartDAO=new CartDAO(context);
        botomSheetDiaLog=new BotomSheetDiaLog(BotomSheetDiaLog.TYPE_CART);
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        Cart cart=cartList.get(position);

        Product product = cart.getProduct();
        ProductDetal detal = cart.getProductDetal();

        holder.item_gia.setText(df.format(product.getGia()*cart.getSoLuong())+" VND");
        holder.item_name.setText(product.getTen());
        holder.edItemCart.setText(String.valueOf(cart.getSoLuong()));
        if (cart.getTrangThai()==0){
            holder.itemcheck.setChecked(false);
        }else {
            holder.itemcheck.setChecked(true);
        }

        holder.itemcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.itemcheck.isChecked()){
                    cart.setTrangThai(1);
                }else {
                    cart.setTrangThai(0);
                }
            }
        });
        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.setSoLuong(cart.getSoLuong()+1);
                cart.setGia(cart.getSoLuong()*(float)product.getGia());
                holder.edItemCart.setText(String.valueOf(cart.getSoLuong()));
                holder.item_gia.setText(df.format(product.getGia()*cart.getSoLuong())+" VND");
                cartDAO.updateSoluong(product.getId(),cart.getSoLuong());
                changePrice.change();
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cart.getSoLuong()>0){
                    cart.setSoLuong(cart.getSoLuong()-1);
                    cart.setGia(cart.getSoLuong()*(float)product.getGia());
                    holder.edItemCart.setText(String.valueOf(cart.getSoLuong()));
                    holder.item_gia.setText(df.format(product.getGia()*cart.getSoLuong())+" VND");
                    cartDAO.updateSoluong(product.getId(),cart.getSoLuong());
                    changePrice.change();
                }

            }
        });
        holder.tvOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("Product",product);
                botomSheetDiaLog.setArguments(bundle);
                botomSheetDiaLog.show(fragmentManager,"bottom_sheet_addCart");
            }
        });
        Picasso.get().load(product.getImg()).into(holder.item_img);


    }

    public void setCartList(ArrayList<Cart> cartList) {
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        private CheckBox itemcheck;
        private TextView item_name ,item_gia;
        private AppCompatButton btnCong,btnTru;
        private TextView edItemCart;
        private TextView tvOption ;
        private ImageView item_img ;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            itemcheck=itemView.findViewById(R.id.item_checkout);
            item_gia = itemView.findViewById(R.id.item_gia);
            item_name = itemView.findViewById(R.id.item_name);
            item_img = itemView.findViewById(R.id.item_img);
            btnCong=itemView.findViewById(R.id.btn_cong_itemCart);
            btnTru=itemView.findViewById(R.id.btn_tru_itemCart);
            tvOption=itemView.findViewById(R.id.tv_option);
            itemcheck = itemView.findViewById(R.id.item_checkout);
            edItemCart=itemView.findViewById(R.id.tv_count_itemCart);

        }
    }
    public interface ChangePrice{

        public void change();
    }

}
