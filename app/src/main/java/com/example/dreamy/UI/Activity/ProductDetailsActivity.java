package com.example.dreamy.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamy.Model.Color;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.ColorAdapter;
import com.example.dreamy.UI.Adapter.ProductAdapter;
import com.example.dreamy.UI.Fragment.ImageSlideFragment;
import com.example.dreamy.Interface.ColorInterface;
import com.example.dreamy.Interface.ImgProductInterface;
import com.example.dreamy.Interface.ProductsInterface;
import com.example.dreamy.Interface.RetrofitService;
import com.example.dreamy.Interface.SizeInterface;
import com.example.dreamy.Model.ImageProduct;
import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.Size;
import com.example.dreamy.UI.Dialog.BotomSheetDiaLog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetailsActivity extends AppCompatActivity implements PropertyChangeListener {
    //tool
    DecimalFormat df = new DecimalFormat("#.##");
    public static final int BYLOAI=0;
    public static final int BYNSX=1;

    // view
private TextView title;
private Button btnOpenBottomSheet;
    private ViewPager2 mViewPager2;
    private ImageView btnAddCart;
    private RecyclerView mRecyclerViewColor;
    private RecyclerView mRecyclerViewSize;
    private RecyclerView mRecyclerViewlistProduct;
    private RecyclerView mRecyclerViewlistProductByKind;
    private CircleIndicator3 mCircleIndicator3;
    private TextView tvName,tvPrice;
    private  AdapterImageSlide adapterImageSlide;
    private ProductAdapter productAdapter;

    // data


    private List<ImageProduct> mPhotoList;
    private Product product;
    private ColorAdapter adapterColor;
    private AdapterSize adapterSize;
    private SharedPreferences sharedPreferences;
    BotomSheetDiaLog fragobj = BotomSheetDiaLog.newInstance();
    private Handler handler = new Handler(Looper.getMainLooper());
    private  Retrofit service=RetrofitService.getClient();


    FragmentManager fragmentManager = getSupportFragmentManager();
    LinearLayout bottomSheet;
    View.OnClickListener addCardOnClick= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(ProductDetailsActivity.this,"Tem Thanh Cong ",Toast.LENGTH_LONG).show();
        }
    };
    View.OnClickListener btnBottonSheetOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {


                Bundle bundle=new Bundle();

            int i=adapterSize.getSizes().size();
                bundle.putParcelableArrayList("sizes",(ArrayList<Size>) adapterSize.getSizes());
                bundle.putParcelableArrayList("colors",(ArrayList<com.example.dreamy.Model.Color>) adapterColor.getColors());
                bundle.putSerializable("Product",product );
                fragobj.setArguments(bundle);
                fragobj.show(fragmentManager,"bottom_sheet");


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
        tvName=findViewById(R.id.tvName);
        tvPrice=findViewById(R.id.tvPrice);
        btnAddCart=findViewById(R.id.addCart);
        btnOpenBottomSheet=findViewById(R.id.btnMua);
        btnAddCart.setOnClickListener(addCardOnClick);
        btnOpenBottomSheet.setOnClickListener(btnBottonSheetOnClick);

        tvName.setText(product.getTen());
        tvPrice.setText(df.format(product.getGia())+ "VND");







//        fragmentManager.beginTransaction()
//                .replace(R.id.title_fragment, fragobj, null)
//                .setReorderingAllowed(true)
//                .addToBackStack("name") // Name can be null
//                .commit();
        title=findViewById(R.id.title);
//        title.setText(product.set);
        //setImg
        setlistImgs();
        //setcolor
        adapterColor = new ColorAdapter();
        setColor();
        //setSize
        adapterSize=new AdapterSize();
        setSize();
        //set list sp cung loai
//        setlistProduct(BYLOAI,mRecyclerViewlistProduct);
//        setlistProduct(BYNSX,mRecyclerViewlistProductByKind);

        mCircleIndicator3.setViewPager(mViewPager2);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);

            }
        });


        adapterColor.setOnClickListener(new ColorAdapter.OnClickListener() {
            @Override
            public void onClick(int position, Color color) {
                int pos =mPhotoList.size()-1;
                mPhotoList.add(new ImageProduct(color.getImg()));
                adapterImageSlide.createFragment(pos);
                adapterImageSlide.notifyDataSetChanged();
                mViewPager2.setCurrentItem(pos);

            }
        });

        mRecyclerViewColor.setAdapter(adapterColor);
        mRecyclerViewSize.setAdapter(adapterSize);





    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("productById")){

        }
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
        Call<List<com.example.dreamy.Model.Color>> call=colorInterface.getColor(product.getId());
        call.enqueue(new Callback<List<com.example.dreamy.Model.Color>>() {
            @Override
            public void onResponse(Call<List<com.example.dreamy.Model.Color>> call, Response<List<com.example.dreamy.Model.Color>> response) {
                if(response.isSuccessful()){
                    adapterColor.setColors(response.body());
                }


            }

            @Override
            public void onFailure(Call<List<com.example.dreamy.Model.Color>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }






    public class AdapterSize extends RecyclerView.Adapter<AdapterSize.ViewHolder>{
        List<Size> sizes;

        public List<Size> getSizes() {
            return sizes;
        }

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