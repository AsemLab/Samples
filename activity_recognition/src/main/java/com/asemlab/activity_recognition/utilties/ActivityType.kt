package com.asemlab.activity_recognition.utilties

enum class ActivityType {
    WALKING, DRIVING;

    companion object {
        fun getType(activityType: String) = ActivityType.valueOf(activityType)
    }
}

