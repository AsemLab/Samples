package com.asemlab.samples.activity_recognition.utilties

enum class ActivityType {
    STILL, WALKING, DRIVING, RUNNING, UNKNOWN;

    companion object {
        fun getType(activityType: String) = valueOf(activityType)
    }
}

