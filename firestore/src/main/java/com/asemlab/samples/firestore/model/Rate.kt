package com.asemlab.samples.firestore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rate(
    val score: Int,
    var comment: String,
    val username: String = "Anonymous"
) : Parcelable
