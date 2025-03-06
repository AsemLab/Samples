package com.asemlab.activity_recognition.utilties

import java.text.SimpleDateFormat
import java.util.Date


fun Long.toDate()  = Date(this)

fun Date.formatDate(): String {
    val formatter = SimpleDateFormat.getDateInstance()

    return formatter.format(this)
}

fun Date.formatDateWithPattern(format: String): String {
    val formatter = SimpleDateFormat(format)

    return formatter.format(this)
}