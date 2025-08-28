package com.asemlab.inventory.utils

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.asemlab.inventory.R
import com.bumptech.glide.Glide

@RequiresApi(Build.VERSION_CODES.M)
@BindingAdapter("formatQuantity")
fun TextView.formatQuantity(quantity: Double) {

    if (quantity.toInt() == 0) {
        text = context.getString(R.string.out_of_stock)
        setTextColor(context.getColor(R.color.light_red))
    } else {
        text = context.getString(R.string.quantity_in_stock, quantity)
        setTextColor(context.getColor(R.color.black))
    }
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    val urls = arrayOf(
        "https://static.vecteezy.com/system/resources/previews/047/420/976/non_2x/trendy-water-caps-and-bottles-elevate-your-hydration-game-free-png.png",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFNqM11DYlL2oxIknJu6iE0VMpI41Pr-RAzg&s",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJYTtHpyB1UrwPwmU-Tk5ucqquqWtuupz6nA&s",
        "https://jebnalak.com/cdn/shop/files/Jebnalak-2024-05-17T122523.830_800x.png?v=1715937937",
    )
    Glide.with(context)
        .load(url?.takeIf { it.isNotEmpty() } ?: urls.random())
        .placeholder(R.drawable.ic_temp_product)
        .into(this)
}