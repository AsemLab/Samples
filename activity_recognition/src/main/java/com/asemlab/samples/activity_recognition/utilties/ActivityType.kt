package com.asemlab.samples.activity_recognition.utilties

enum class ActivityType {
    WALKING, DRIVING;

    companion object {
        fun getType(activityType: String) = valueOf(activityType)
    }
}

