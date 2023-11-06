package com.example.dreamy.UI.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Model.PhotoProducts;
import com.example.dreamy.UI.Activity.Model.PhotoSlide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SlideProductsAdapter extends PagerAdapter {
    Context context ;
    List<PhotoProducts> list ;

    public SlideProductsAdapter(Context context, List<PhotoProducts> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_img, container, false);
        ImageView imgSlider = view.findViewById(R.id.img_slider);
        PhotoProducts photo = list.get(position);
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_home);
        if (photo != null) {
            Picasso.get().load(photo.getImg()).into(imgSlider);
            //  Glide.with(context).load(photo.getResourceId()).into(imgSlider);
        }
        container.addView(view);
        view.startAnimation(animation);
        return view;
    }
    @Override
    public int getCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
