package com.asemlab.samples.feature_delivery.settings

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, msg: String){
    Toast.makeText(context, "$msg from Settings!", Toast.LENGTH_SHORT).show()
}