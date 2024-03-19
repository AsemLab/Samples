package com.asemlab.samples.unittesting.utils

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.asemlab.samples.unittesting.R

@BindingAdapter("priorityColor")
fun View.setPriorityColor(priority: Int) {

    val color = when (priority) {
        1 -> R.color.green
        2 -> R.color.orange
        3 -> R.color.red
        else -> R.color.gray
    }

    val stateList = ColorStateList(
        arrayOf(
            intArrayOf()
        ),
        intArrayOf(ResourcesCompat.getColor(resources, color, null))
    )

    backgroundTintList = stateList

}