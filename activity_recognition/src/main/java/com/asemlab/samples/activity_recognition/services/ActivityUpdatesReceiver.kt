package com.asemlab.samples.activity_recognition.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.asemlab.samples.activity_recognition.utilties.ActivityDetectionUtility.toActivityType
import com.asemlab.samples.activity_recognition.utilties.ActivityType.DRIVING
import com.asemlab.samples.activity_recognition.utilties.ActivityType.RUNNING
import com.asemlab.samples.activity_recognition.utilties.ActivityType.STILL
import com.asemlab.samples.activity_recognition.utilties.ActivityType.UNKNOWN
import com.asemlab.samples.activity_recognition.utilties.ActivityType.WALKING
import com.asemlab.samples.activity_recognition.utilties.DataStoreUtils
import com.google.android.gms.location.ActivityRecognitionResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

                val steps = when (type) {
                    STILL, DRIVING, UNKNOWN -> 0
                    WALKING -> confidence / 10
                    RUNNING -> confidence / 10
                }

                CoroutineScope(Dispatchers.IO).launch {
                    DataStoreUtils.updateCurrentPoints(context, confidence / 10)
                }


                Toast.makeText(context, "$type : $confidence%", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
