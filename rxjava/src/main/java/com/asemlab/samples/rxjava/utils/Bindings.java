package com.asemlab.samples.rxjava.utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;

public class Bindings {

    @BindingAdapter("setFlag")
    public  static void setFlag(ImageView view, String url){
        Glide.with(view.getContext()).load(url).into(view);
    }

}
