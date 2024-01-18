package com.example.dreamy.UI.Activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.InterfaceRetrofit.ItemBillOnClick;
import com.example.dreamy.Model.InvoidDetals;

import java.util.ArrayList;

public class DitalItemAdapter extends RecyclerView.Adapter<DitalItemAdapter.ViewHolder> {
    ItemBillOnClick itemBillOnClick;
    ArrayList<InvoidDetals> invoidDetals=new ArrayList<>();
    Context context;

    public DitalItemAdapter(ItemBillOnClick itemBillOnClick, ArrayList<InvoidDetals> invoidDetals, Context context) {
        this.itemBillOnClick = itemBillOnClick;
        this.invoidDetals = invoidDetals;
        this.context = context;
    }

    @NonNull
    @Override
    public DitalItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DitalItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
