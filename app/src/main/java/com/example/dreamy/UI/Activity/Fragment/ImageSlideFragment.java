package com.example.dreamy.UI.Activity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.Model.PhotoSlide;


public class ImageSlideFragment extends Fragment {

    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_slide, container, false);
        Bundle bundle = getArguments();
        PhotoSlide photo = (PhotoSlide) bundle.get("photo");

        imageView = view.findViewById(R.id.img_slide);

        imageView.setImageResource(photo.getResourceId());
        return view;
    }
}