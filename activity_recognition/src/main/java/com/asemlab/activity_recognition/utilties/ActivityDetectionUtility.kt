package com.asemlab.activity_recognition.utilties

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.widget.Toast
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.ActivityTransitionEvent
import com.google.android.gms.location.ActivityTransitionRequest
import com.google.android.gms.location.ActivityTransitionResult
import com.google.android.gms.location.DetectedActivity

// TODO Use the Activity Transition API
object ActivityDetectionUtility {

    private lateinit var pendingIntent: PendingIntent
    private var activityTrackingEnabled: Boolean = false
    private var activityTransitionList = mutableListOf<ActivityTransition>()


    private fun getPendingIntent(context: Context): PendingIntent {
        if (::pendingIntent.isInitialized)
            return pendingIntent

        val intent = Intent(Constants.TRANSITIONS_RECEIVER_ACTION)

        pendingIntent =
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

        return pendingIntent
    }

    fun isTrackingEnabled() = activityTrackingEnabled

    fun switchDetecting(context: Context) {
        if (activityTrackingEnabled) {
            disableActivityTransitions(context)
        } else {
            enableActivityTransitions(context)
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableActivityTransitions(context: Context) {

        if (activityTransitionList.isEmpty())
            fillTransitionsList()

        val request = ActivityTransitionRequest(activityTransitionList)

        val task = ActivityRecognition.getClient(context)
            .requestActivityTransitionUpdates(request, getPendingIntent(context))

        task.addOnSuccessListener {
            activityTrackingEnabled = true
            Toast.makeText(
                context,
                "Transitions Api was successfully registered.",
                Toast.LENGTH_SHORT
            ).show()
        }

        task.addOnFailureListener { e ->
            Toast.makeText(
                context,
                "Transitions Api could NOT be registered: $e",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // TODO Add the desired activities to observe
    private fun fillTransitionsList() {
        activityTransitionList.apply {
            add(
                ActivityTransition.Builder()
                    .setActivityType(DetectedActivity.WALKING)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                    .build()
            )
            add(
                ActivityTransition.Builder()
                    .setActivityType(DetectedActivity.WALKING)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                    .build()
            )
            add(
                ActivityTransition.Builder()
                    .setActivityType(DetectedActivity.IN_VEHICLE)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                    .build()
            )
            add(
                ActivityTransition.Builder()
                    .setActivityType(DetectedActivity.IN_VEHICLE)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                    .build()
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun disableActivityTransitions(context: Context) {
        ActivityRecognition.getClient(context)
            .removeActivityTransitionUpdates(getPendingIntent(context))
            .addOnSuccessListener {
                activityTrackingEnabled = false
                Toast.makeText(
                    context,
                    "Transitions successfully unregistered.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Transitions could not be unregistered: $e",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    // The next two functions are for testing purposes only
    fun testSendAction(context: Context) {
        val intent2 = Intent()
        intent2.setAction(Constants.TRANSITIONS_RECEIVER_ACTION)
        val events: MutableList<ActivityTransitionEvent> = ArrayList()
        var transitionEvent = ActivityTransitionEvent(
            DetectedActivity.STILL,
            ActivityTransition.ACTIVITY_TRANSITION_EXIT, SystemClock.elapsedRealtimeNanos()
        )
        events.add(transitionEvent)
        transitionEvent = ActivityTransitionEvent(
            DetectedActivity.WALKING,
            ActivityTransition.ACTIVITY_TRANSITION_ENTER, SystemClock.elapsedRealtimeNanos()
        )
        events.add(transitionEvent)
        val result = ActivityTransitionResult(events)
        SafeParcelableSerializer.serializeToIntentExtra(
            result, intent2,
            "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT"
        )
        context.sendBroadcast(intent2)
    }
    fun testSendActionDriving(context: Context) {
        val intent2 = Intent()
        intent2.setAction(Constants.TRANSITIONS_RECEIVER_ACTION)
        val events: MutableList<ActivityTransitionEvent> = ArrayList()
        var transitionEvent = ActivityTransitionEvent(
            DetectedActivity.IN_VEHICLE,
            ActivityTransition.ACTIVITY_TRANSITION_EXIT, SystemClock.elapsedRealtimeNanos()
        )
        events.add(transitionEvent)
        transitionEvent = ActivityTransitionEvent(
            DetectedActivity.WALKING,
            ActivityTransition.ACTIVITY_TRANSITION_ENTER, SystemClock.elapsedRealtimeNanos()
        )
        events.add(transitionEvent)
        val result = ActivityTransitionResult(events)
        SafeParcelableSerializer.serializeToIntentExtra(
            result, intent2,
            "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT"
        )
        context.sendBroadcast(intent2)
    }
}