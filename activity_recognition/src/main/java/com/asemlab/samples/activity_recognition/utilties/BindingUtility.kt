package com.asemlab.samples.activity_recognition.utilties

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.asemlab.samples.activity_recognition.R
import com.asemlab.samples.activity_recognition.model.ActivityEntry

@BindingAdapter("iconEntry")
fun ImageView.setIconEntry(entry: ActivityEntry) {
    when (ActivityType.getType(entry.type)) {
        ActivityType.DRIVING -> setImageResource(R.drawable.ic_car)
        ActivityType.WALKING, ActivityType.RUNNING -> setImageResource(R.drawable.ic_steps)
        ActivityType.STILL, ActivityType.UNKNOWN -> {
            // Not needed
        }
    }
}