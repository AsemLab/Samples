package com.asemlab.quakes.settings

import android.content.Context
import android.widget.Toast
import com.asemlab.samples.feature_delivery.interfaces.ToastUtils

class ToastUtilsImp: ToastUtils {

    override fun showToast(context: Context, msg: String) {
        Toast.makeText(context, "$msg from Settings!", Toast.LENGTH_SHORT).show()
    }

}