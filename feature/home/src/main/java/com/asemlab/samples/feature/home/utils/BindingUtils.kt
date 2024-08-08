package com.asemlab.samples.feature.home.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    Glide.with(context).load(url).into(this)
}