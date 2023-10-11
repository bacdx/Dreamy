package com.example.dreamy.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Fragment.ImageSlideFragment;
import com.example.dreamy.UI.Activity.Model.PhotoSlide;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private RecyclerView mRecyclerViewColor;
    private CircleIndicator3 mCircleIndicator3;

    private List<PhotoSlide> mPhotoList;

    private Handler handler = new Handler(Looper.getMainLooper());
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

        mViewPager2 = findViewById(R.id.view_pager2);
        mCircleIndicator3 = findViewById(R.id.circle_indicator3);
        mRecyclerViewColor = findViewById(R.id.rcvColor);

        mPhotoList = getListPhoto();

        AdapterImageSlide adapterImageSlide = new AdapterImageSlide(this,mPhotoList);
        mViewPager2.setAdapter(adapterImageSlide);
        mCircleIndicator3.setViewPager(mViewPager2);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });

        AdapterColor adapterColor = new AdapterColor();
        adapterColor.setColors(getColor());
        mRecyclerViewColor.setAdapter(adapterColor);
    }

    private List<PhotoSlide> getListPhoto(){
        List<PhotoSlide> list = new ArrayList<>();
        list.add(new PhotoSlide(R.drawable.slide_img1));
        list.add(new PhotoSlide(R.drawable.slide_img1));
        list.add(new PhotoSlide(R.drawable.slide_img1));
        list.add(new PhotoSlide(R.drawable.slide_img1));
        list.add(new PhotoSlide(R.drawable.slide_img1));
        return list;
    }

    private List<String> getColor(){
        List<String> list = new ArrayList<>();
        list.add("#683F3F");
        list.add("#B1834D");
        list.add("#0F0E0E");
        return list;
    }

    public class AdapterImageSlide extends FragmentStateAdapter {

        private List<PhotoSlide> photoList;

        public AdapterImageSlide(FragmentActivity fragmentActivity, List<PhotoSlide> list) {
            super(fragmentActivity);
            this.photoList = list;
        }


        @Override
        public Fragment createFragment(int position) {

            PhotoSlide photo = photoList.get(position);
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

    public class AdapterColor extends RecyclerView.Adapter<AdapterColor.ViewHolder>{
        List<String> colors;

        public void setColors(List<String> colors){
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
            holder.view.setBackgroundColor(Color.parseColor(colors.get(position)));
        }

        @Override
        public int getItemCount() {
            return colors.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private View view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.item_view_color);
            }
        }
    }

}