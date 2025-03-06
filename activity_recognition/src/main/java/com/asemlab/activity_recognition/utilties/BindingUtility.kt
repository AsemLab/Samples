package com.asemlab.activity_recognition.utilties

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.asemlab.activity_recognition.R
import com.asemlab.activity_recognition.model.ActivityEntry

@BindingAdapter("iconEntry")
fun ImageView.setIconEntry(entry: ActivityEntry) {
    when (ActivityType.getType(entry.type)) {
        ActivityType.DRIVING -> setImageResource(R.drawable.ic_car)
        ActivityType.WALKING -> setImageResource(R.drawable.ic_steps)
    }
}