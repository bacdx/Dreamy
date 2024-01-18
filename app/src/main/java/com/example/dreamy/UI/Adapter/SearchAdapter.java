package com.example.dreamy.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dreamy.Model.Product;
import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.ProductActivity;
import com.example.dreamy.UI.Activity.ProductDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class SearchAdapter extends ArrayAdapter<Product> {
    CustomFilter customFilter;

    private ArrayList<Product> list;
    private ArrayList<Product> listRoot;

    Context mContext;

    public SearchAdapter(ArrayList<Product> list, Context context) {
        super(context, R.layout.searchview, list);
        this.list = list;
        listRoot=list;
        this.mContext=context;
        customFilter=new CustomFilter(listRoot,this);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product =getItem(position);
        if(convertView==null){
            convertView=LayoutInflater.from(mContext).inflate(R.layout.searchview,parent,false);

        }
        TextView tvSearch=convertView.findViewById(R.id.txtsearch);
        tvSearch.setText(product.getTen());
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, ProductDetailsActivity.class);
                ArrayList<Product> list1=new ArrayList<>();
                list1.add(product);
                intent.putParcelableArrayListExtra("Product",list1);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }


    public void setList(ArrayList<Product> list) {
        this.list = list;
        customFilter.setList(list);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public Filter getFilter() {
        return customFilter;
    }
    public  static interface SearchItemOnclick{
        public void onClick (Product product);
    }
    public static class CustomFilter extends Filter{
        ArrayList<Product> list= new ArrayList<>();
        ArrayList<Product> newList= new ArrayList<>();
        SearchAdapter arrayAdapter;
        public CustomFilter(ArrayList<Product> list,SearchAdapter arrayAdapter) {
            this.list=new ArrayList<>(list);
            this.arrayAdapter=arrayAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if (charSequence == null || charSequence.length() == 0) {
                // No filter, return original data
                newList.clear();
                results.values=newList;
                results.count = newList.size();
            } else {
                // Filter data based on constraint


                    // Check if object matches constraint (e.g., contains search query)

                    newList=filter(list,charSequence);

                results.values = newList;
                results.count = newList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            arrayAdapter.clear();
            arrayAdapter.addAll(newList);
            arrayAdapter.notifyDataSetChanged();

        }

        public void setList(ArrayList<Product> list) {
            this.list = list;
        }

        private ArrayList<Product> filter(ArrayList<Product> list, CharSequence query){
            ArrayList<Product> newList=new ArrayList<>();
            for(int i=0;i<list.size();i++){
                Product product=list.get(i);
                String name=product.getTen().toLowerCase();
                if(product.getTen().contains(query)){
                    newList.add(product);

                }
            }
            return newList;
        }
    }
}
