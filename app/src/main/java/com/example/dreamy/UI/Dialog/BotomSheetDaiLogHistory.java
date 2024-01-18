package com.example.dreamy.UI.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamy.InterfaceRetrofit.Model.InvoiceDetails;
import com.example.dreamy.Manager.BillManager;
import com.example.dreamy.Model.Bill;
import com.example.dreamy.Model.InvoidDetals;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.InvoiceDetailsAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

public class BotomSheetDaiLogHistory extends BottomSheetDialogFragment implements PreferenceChangeListener {
private Context context;
private ArrayList<InvoidDetals> list;
private InvoiceDetailsAdapter invoiceDetailsAdapter ;
private Bill bill;
RecyclerView recyclerView;
private TextView textView;
BillManager billManager;

    public BotomSheetDaiLogHistory( Context context) {
        this.context=context;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle bundle = getArguments();
//        bill= bundle.getParcelable("Bill");
//        list=bill.getListInvoiceDetails();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(context).inflate(R.layout.dialog_bottomsheet_history,container,false);
        recyclerView=view.findViewById(R.id.rcv_detal_history);
        textView=view.findViewById(R.id.tv_address_Hisbotom);

        intit();
        return view;
    }

    private void intit(){

        invoiceDetailsAdapter=new InvoiceDetailsAdapter(bill,list,context);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(invoiceDetailsAdapter);


    }


    @Override
    public void preferenceChange(PreferenceChangeEvent preferenceChangeEvent) {

    }
    public void setData(){
        Bundle bundle=getArguments();
        bill= bundle.getParcelable("Bill");
        list=bill.getListInvoiceDetails();
        invoiceDetailsAdapter.setList(list);
        invoiceDetailsAdapter.notifyDataSetChanged();
        textView.setText("Dia chi: "+bill.getDiaChi());
    }
}
