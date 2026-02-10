package com.asemlab.samples.activity_recognition.utilties

object Constants {

    const val TODAY_POINTS_KEY = "TODAY_POINTS_KEY"
    const val TRANSITIONS_RECEIVER_ACTION = "TRANSITIONS_RECEIVER_ACTION"
    const val ACTIVITY_UPDATES_RECEIVER_ACTION = "ACTIVITY_UPDATES_RECEIVER_ACTION"
    const val DATE_FORMAT = "dd/MM/yyyy"
    const val TIMER_INTERVAL = 1000 * 3L
    const val WALKING_POINTS_FACTOR = 4
    const val DRIVING_POINTS_FACTOR = 2
    const val STILL_POINTS_FACTOR = 0

    const val NOTIFICATION_CHANNEL_ID = "activity_recognition_channel"
    const val NOTIFICATION_CHANNEL_NAME = "Activity Tracking"
    const val SERVICE_NOTIFICATION_ID = 1001
    const val SERVICE_NOTIFICATION_INTENT_ID = 1002
}