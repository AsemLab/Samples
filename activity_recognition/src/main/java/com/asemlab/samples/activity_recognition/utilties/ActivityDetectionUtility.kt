package com.asemlab.samples.activity_recognition.utilties

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.widget.Toast
import com.asemlab.samples.activity_recognition.services.ActivityUpdatesReceiver
import com.asemlab.samples.activity_recognition.services.TransitionsReceiver
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

    private const val resultKey = "com.google.android.location.internal.EXTRA_ACTIVITY_TRANSITION_RESULT"

    private fun getPendingIntent(context: Context): PendingIntent {
        if (::pendingIntent.isInitialized)
            return pendingIntent

        val intent = Intent(
            context,
            ActivityUpdatesReceiver::class.java  // OR TransitionsReceiver
        ).setAction(Constants.ACTIVITY_UPDATES_RECEIVER_ACTION) // OR TRANSITIONS_RECEIVER_ACTION

        pendingIntent =
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_MUTABLE
            )

        return pendingIntent
    }

    fun isTrackingEnabled() = activityTrackingEnabled

    fun switchTransitionsDetecting(context: Context) {
        if (activityTrackingEnabled) {
            disableActivityTransitions(context)
        } else {
            enableActivityTransitions(context)
        }
    }


    fun switchUpdatesDetecting(context: Context) {
        if (activityTrackingEnabled) {
            disableActivityUpdates(context)
        } else {
            enableActivityUpdates(context)
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
        }.addOnFailureListener { e ->
            Toast.makeText(
                context,
                "Transitions Api could NOT be registered: $e",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("MissingPermission")
    fun enableActivityUpdates(context: Context){
        val task = ActivityRecognition.getClient(context)
            .requestActivityUpdates(3000L, getPendingIntent(context))

        task.addOnSuccessListener {
            activityTrackingEnabled = true
            Toast.makeText(
                context,
                "Activity updates Api was successfully registered.",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener { e ->
            Toast.makeText(
                context,
                "Activity updates Api could NOT be registered: $e",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun disableActivityUpdates(context: Context) {
        ActivityRecognition.getClient(context)
            .removeActivityTransitionUpdates(getPendingIntent(context))
            .addOnSuccessListener {
                activityTrackingEnabled = false
                Toast.makeText(
                    context,
                    "Activity updates successfully unregistered.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Activity updates could not be unregistered: $e",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    // TODO Add the desired activities to observe
    private fun fillTransitionsList() {
        activityTransitionList.apply {
            add(
                ActivityTransition.Builder()
                    .setActivityType(DetectedActivity.STILL)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                    .build()
            )
            add(
                ActivityTransition.Builder()
                    .setActivityType(DetectedActivity.STILL)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                    .build()
            )
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

    fun toActivityType(activity: Int): ActivityType {
        return when (activity) {
            DetectedActivity.STILL -> ActivityType.STILL
            DetectedActivity.WALKING, DetectedActivity.ON_FOOT -> ActivityType.WALKING
            DetectedActivity.IN_VEHICLE -> ActivityType.DRIVING
            DetectedActivity.RUNNING -> ActivityType.RUNNING
            else -> ActivityType.UNKNOWN
        }
    }

    fun toTransitionType(transitionType: Int): String {
        return when (transitionType) {
            ActivityTransition.ACTIVITY_TRANSITION_ENTER -> "ENTER"
            ActivityTransition.ACTIVITY_TRANSITION_EXIT -> "EXIT"
            else -> "UNKNOWN"
        }
    }

    // The next three functions are for testing purposes only
    fun testEnterWalking(context: Context) {
        val intent2 = Intent(
            context,
            TransitionsReceiver::class.java
        ).setAction(Constants.TRANSITIONS_RECEIVER_ACTION)

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
            resultKey
        )
        context.sendBroadcast(intent2)
    }

    // The next two functions are for testing purposes only
    fun testWalkingToDriving(context: Context) {
        val intent2 = Intent(
            context,
            TransitionsReceiver::class.java
        ).setAction(Constants.TRANSITIONS_RECEIVER_ACTION)

        val events: MutableList<ActivityTransitionEvent> = ArrayList()
        var transitionEvent = ActivityTransitionEvent(
            DetectedActivity.WALKING,
            ActivityTransition.ACTIVITY_TRANSITION_EXIT, SystemClock.elapsedRealtimeNanos()
        )
        events.add(transitionEvent)
        transitionEvent = ActivityTransitionEvent(
            DetectedActivity.IN_VEHICLE,
            ActivityTransition.ACTIVITY_TRANSITION_ENTER, SystemClock.elapsedRealtimeNanos()
        )
        events.add(transitionEvent)

        val result = ActivityTransitionResult(events)
        SafeParcelableSerializer.serializeToIntentExtra(
            result, intent2,
            resultKey
        )
        context.sendBroadcast(intent2)
    }

    fun testDrivingToWalking(context: Context) {
        val intent2 = Intent(
            context,
            TransitionsReceiver::class.java
        ).setAction(Constants.TRANSITIONS_RECEIVER_ACTION)

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
            resultKey
        )
        context.sendBroadcast(intent2)
    }
}