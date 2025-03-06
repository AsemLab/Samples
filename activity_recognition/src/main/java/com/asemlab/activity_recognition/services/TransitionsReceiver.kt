package com.asemlab.activity_recognition.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.asemlab.activity_recognition.ActivityRecognitionApp
import com.asemlab.activity_recognition.utilties.ActivityType
import com.asemlab.activity_recognition.utilties.DetectingMode
import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.ActivityTransitionResult
import com.google.android.gms.location.DetectedActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TransitionsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (ActivityTransitionResult.hasResult(intent)) {
            val result = ActivityTransitionResult.extractResult(intent)

            // TODO Receive transitions and process them
            for (event in result!!.transitionEvents) {
                val time = SimpleDateFormat("HH:mm:ss", Locale.US).format(Date())
                val info =
                    "${toTransitionType(event.transitionType)} ${toActivityString(event.activityType)} at $time"

                when (event.transitionType) {
                    ActivityTransition.ACTIVITY_TRANSITION_ENTER -> {
                        with(context.applicationContext as ActivityRecognitionApp) {
                            activityType.value = ActivityType.getType(toActivityString(event.activityType))
                            detectingMode.value = DetectingMode.ENTER
                        }
                    }

                    ActivityTransition.ACTIVITY_TRANSITION_EXIT -> {
                        with(context.applicationContext as ActivityRecognitionApp) {
                            activityType.value = ActivityType.getType(toActivityString(event.activityType))
                            detectingMode.value = DetectingMode.EXIT
                        }
                    }
                }
            }
        }
    }

    private fun toActivityString(activity: Int): String {
        return when (activity) {
//            DetectedActivity.STILL -> "STILL"
            DetectedActivity.WALKING -> "WALKING"
            DetectedActivity.IN_VEHICLE -> "DRIVING"
            else -> "WALKING"
        }
    }

    private fun toTransitionType(transitionType: Int): String {
        return when (transitionType) {
            ActivityTransition.ACTIVITY_TRANSITION_ENTER -> "ENTER"
            ActivityTransition.ACTIVITY_TRANSITION_EXIT -> "EXIT"
            else -> "UNKNOWN"
        }
    }
}