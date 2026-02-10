package com.asemlab.samples.activity_recognition.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.asemlab.samples.activity_recognition.ActivityRecognitionApp
import com.asemlab.samples.activity_recognition.utilties.ActivityDetectionUtility.toActivityType
import com.asemlab.samples.activity_recognition.utilties.ActivityDetectionUtility.toTransitionType
import com.asemlab.samples.activity_recognition.utilties.DetectingMode
import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.ActivityTransitionResult
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TransitionsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Log.d("TransitionsReceiver", "Start detecting")
        Toast.makeText(context, "Start detecting", Toast.LENGTH_SHORT).show()

        if (ActivityTransitionResult.hasResult(intent)) {
            val result = ActivityTransitionResult.extractResult(intent)

            // TODO Receive transitions and process them
            result?.transitionEvents?.forEach { event ->
                val time = SimpleDateFormat("HH:mm:ss", Locale.US).format(Date())
                val info =
                    "${toTransitionType(event.transitionType)} ${toActivityType(event.activityType)} at $time"

                Toast.makeText(context, info, Toast.LENGTH_SHORT).show()

                when (event.transitionType) {
                    ActivityTransition.ACTIVITY_TRANSITION_ENTER -> {
                        with(context.applicationContext as ActivityRecognitionApp) {
                            activityType.value = toActivityType(event.activityType)
                            detectingMode.value = DetectingMode.ENTER
                        }
                    }

                    ActivityTransition.ACTIVITY_TRANSITION_EXIT -> {
                        with(context.applicationContext as ActivityRecognitionApp) {
                            activityType.value = toActivityType(event.activityType)
                            detectingMode.value = DetectingMode.EXIT
                        }
                    }
                }
            }
        }
    }


}