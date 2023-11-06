package com.example.dreamy.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Adapter.CategoryAdapter;
import com.example.dreamy.UI.Activity.Adapter.ProductAdapter;
import com.example.dreamy.UI.Activity.Fragment.ImageSlideFragment;
import com.example.dreamy.UI.Activity.Interface.ColorInterface;
import com.example.dreamy.UI.Activity.Interface.ImgProductInterface;
import com.example.dreamy.UI.Activity.Interface.ProductsInterface;
import com.example.dreamy.UI.Activity.Interface.RetrofitService;
import com.example.dreamy.UI.Activity.Interface.SizeInterface;
import com.example.dreamy.UI.Activity.Model.ImageProduct;
import com.example.dreamy.UI.Activity.Model.PhotoSlide;
import com.example.dreamy.UI.Activity.Model.Product;
import com.example.dreamy.UI.Activity.Model.Size;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetailsActivity extends AppCompatActivity {
    public static final int BYLOAI=0;
    public static final int BYNSX=1;
private TextView title;
    private ViewPager2 mViewPager2;
    private ImageView btnAddCart;
    private RecyclerView mRecyclerViewColor;
    private RecyclerView mRecyclerViewSize;
    private RecyclerView mRecyclerViewlistProduct;
    private RecyclerView mRecyclerViewlistProductByKind;
    private CircleIndicator3 mCircleIndicator3;
    private  AdapterImageSlide adapterImageSlide;
    private ProductAdapter productAdapter;
    private List<ImageProduct> mPhotoList;
    private Product product;
    private AdapterColor adapterColor;
    private AdapterSize adapterSize;
    private SharedPreferences sharedPreferences;
    private Handler handler = new Handler(Looper.getMainLooper());
    private  Retrofit service=RetrofitService.getClient();
    View.OnClickListener addCardOnClick=new CategoryAdapter.OnItemClickListener() {
        @Override
        public void onClick(View view) {
            addFavorties(product.getId());
            Toast.makeText(ProductDetailsActivity.this,"Tem Thanh Cong ",Toast.LENGTH_LONG).show();
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mViewPager2.getCurrentItem();
            if (currentPosition == mPhotoList.size()-1){
                mViewPager2.setCurrentItem(0);
            }else {
                mViewPager2.setCurrentItem(currentPosition + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent intent=getIntent();
        product=(Product) intent.getSerializableExtra("Product");
        mViewPager2 = findViewById(R.id.view_pager2);
        mCircleIndicator3 = findViewById(R.id.circle_indicator3);
        mRecyclerViewColor = findViewById(R.id.rcvColor);
        mRecyclerViewSize = findViewById(R.id.rcvsize);
        mRecyclerViewlistProduct=findViewById(R.id.rcv_listsp);
        mRecyclerViewlistProductByKind=findViewById(R.id.rcv_listsp1);
        btnAddCart=findViewById(R.id.addCart);
        btnAddCart.setOnClickListener(addCardOnClick);
        title=findViewById(R.id.title);
//        title.setText(product.set);
        //setImg
        setlistImgs();
        //setcolor
        adapterColor = new AdapterColor();
        setColor();
        //setSize
        adapterSize=new AdapterSize();
        setSize();
        //set list sp cung loai
        setlistProduct(BYLOAI,mRecyclerViewlistProduct);
        setlistProduct(BYNSX,mRecyclerViewlistProductByKind);

        mCircleIndicator3.setViewPager(mViewPager2);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);

            }
        });



        mRecyclerViewColor.setAdapter(adapterColor);
        mRecyclerViewSize.setAdapter(adapterSize);
    }



    public class AdapterImageSlide extends FragmentStateAdapter {

        private List<ImageProduct> photoList;

        public AdapterImageSlide(FragmentActivity fragmentActivity, List<ImageProduct> list) {
            super(fragmentActivity);
            this.photoList = list;
        }


        @Override
        public Fragment createFragment(int position) {

            ImageProduct photo = photoList.get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("photo",photo);

            ImageSlideFragment imageSlideFragment = new ImageSlideFragment();
            imageSlideFragment.setArguments(bundle);

            return imageSlideFragment;
        }

        @Override
        public int getItemCount() {
            if (photoList!=null){
                return photoList.size();
            }
            return 0;
        }
    }



    private void setlistImgs(){
        ImgProductInterface imgProductInterface=service.create(ImgProductInterface.class);
        Call<List<ImageProduct>> call=imgProductInterface.getImgProduct(product.getId());

        call.enqueue(new Callback<List<ImageProduct>>() {
            @Override
            public void onResponse(Call<List<ImageProduct>> call, Response<List<ImageProduct>> response) {
                if (response.isSuccessful()){
                    mPhotoList=response.body();
                    adapterImageSlide = new AdapterImageSlide(ProductDetailsActivity.this,mPhotoList);
                    mViewPager2.setAdapter(adapterImageSlide);
                }

            }

            @Override
            public void onFailure(Call<List<ImageProduct>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG);
            }
        });
    }

    private  void setSize(){

        SizeInterface sizeInterface=service.create(SizeInterface.class);
        Call<List< Size >> call=sizeInterface.getSizes(product.getId());
        call.enqueue(new Callback<List<Size>>() {
            @Override
            public void onResponse(Call<List<Size>> call, Response<List<Size>> response) {
                if(response.isSuccessful()){
                    adapterSize.setSizes(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Size>> call, Throwable t) {

            }
        });
    }
    private  void setColor(){
        ColorInterface colorInterface=service.create(ColorInterface.class);
        Call<List<com.example.dreamy.UI.Activity.Model.Color>> call=colorInterface.getColor(product.getId());
        call.enqueue(new Callback<List<com.example.dreamy.UI.Activity.Model.Color>>() {
            @Override
            public void onResponse(Call<List<com.example.dreamy.UI.Activity.Model.Color>> call, Response<List<com.example.dreamy.UI.Activity.Model.Color>> response) {
                if(response.isSuccessful()){
                    adapterColor.setColors(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<com.example.dreamy.UI.Activity.Model.Color>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setlistProduct(int type,RecyclerView recyclerView) {


        ProductsInterface productsInterface=service.create(ProductsInterface.class);
        Call<List<Product>> call;
        if(type==BYLOAI){
        call=productsInterface.getList(product.getMaloai());
        }else  {
          call=productsInterface.getListbyMaNhaSanSuat(product.getManhasanxuat());
        }

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productAdapter=new ProductAdapter(ProductDetailsActivity.this,response.body());
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(ProductDetailsActivity.this,2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public void addFavorties(String id ){
        List<String> integers=getFavorties();
        integers.add(id);
        Type messageListType = new TypeToken<List<Integer>>() {
        }.getType();
        String json =new Gson().toJson(integers,messageListType);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("myIntegerList", json);
        editor.apply();
    }
    public   List<String>  getFavorties(){
        List<String> list1=new ArrayList<>();
        Type messageListType = new TypeToken<List<String>>() {
        }.getType();
        String json =new Gson().toJson(list1,messageListType);

        sharedPreferences= getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key),getApplicationContext().MODE_PRIVATE);
        String serializedList = sharedPreferences.getString("myIntegerList", "");
        if (serializedList.isEmpty()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("myIntegerList",json);
            return list1;
        } else {
            List<String> myList = new Gson().fromJson(serializedList, new TypeToken<List<Integer>>() {}.getType());
            return myList;
        }
    }



    public class AdapterColor extends RecyclerView.Adapter<AdapterColor.ViewHolder>{
        List<com.example.dreamy.UI.Activity.Model.Color> colors;

        public void setColors(List<com.example.dreamy.UI.Activity.Model.Color> colors){
            this.colors = colors;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_color,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.view.setBackgroundColor(Color.parseColor(colors.get(position).getMamau()));
        }

        @Override
        public int getItemCount() {
            if (colors != null) {
                return colors.size();
            }
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.item_view_color);
            }
        }
    }
    public class AdapterSize extends RecyclerView.Adapter<AdapterSize.ViewHolder>{
        List<Size> sizes;

        public void setSizes(List<Size> sizes){
            this.sizes = sizes;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_size,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Size size=sizes.get(position);
            holder.view.setText(size.getTitle());
        }

        @Override
        public int getItemCount() {
            if (sizes != null) {
                return sizes.size();
            }
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.bntXs);
            }
        }
    }
}