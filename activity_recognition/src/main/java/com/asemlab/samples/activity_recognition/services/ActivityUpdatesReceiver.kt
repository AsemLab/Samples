package com.asemlab.samples.activity_recognition.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.asemlab.samples.activity_recognition.ActivityRecognitionApp
import com.asemlab.samples.activity_recognition.utilties.ActivityDetectionUtility.toActivityType
import com.google.android.gms.location.ActivityRecognitionResult


class ActivityUpdatesReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Log.d("ActivityUpdatesReceiver", "Start detecting")

        if (ActivityRecognitionResult.hasResult(intent)) {

            // TODO Receive updates and process them
            val result = ActivityRecognitionResult.extractResult(intent)
            val probableActivities = result?.probableActivities ?: return

            probableActivities.forEach { activity ->
                val type = toActivityType(activity.type)
                val confidence = activity.confidence

                with(context.applicationContext as ActivityRecognitionApp) {
                    activityType.value = type
                }

                Toast.makeText(context, "$type : $confidence%", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
