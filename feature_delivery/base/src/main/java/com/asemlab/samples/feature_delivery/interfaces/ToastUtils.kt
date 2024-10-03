package com.asemlab.samples.feature_delivery.interfaces

import android.content.Context

// TODO Serve as proxy between base & other modules
interface ToastUtils {

    fun showToast(context: Context, msg: String)
}