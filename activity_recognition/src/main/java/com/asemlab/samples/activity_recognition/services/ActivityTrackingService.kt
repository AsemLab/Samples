package com.asemlab.samples.activity_recognition.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.asemlab.samples.activity_recognition.R
import com.asemlab.samples.activity_recognition.ui.MainActivity
import com.asemlab.samples.activity_recognition.utilties.ActivityDetectionUtility
import com.asemlab.samples.activity_recognition.utilties.Constants

class ActivityTrackingService : Service() {


    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                Constants.SERVICE_NOTIFICATION_ID,
                createNotification(),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_HEALTH
            )
        } else
            startForeground(Constants.SERVICE_NOTIFICATION_ID, createNotification())

        ActivityDetectionUtility.switchUpdatesDetecting(this)
//        ActivityDetectionUtility.switchTransitionsDetecting(this)
        return START_STICKY
    }

    override fun onDestroy() {
        ActivityDetectionUtility.switchUpdatesDetecting(this)
//        ActivityDetectionUtility.switchTransitionsDetecting(this)
        super.onDestroy()
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.NOTIFICATION_CHANNEL_ID,
                Constants.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
            val manager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        createNotificationChannel(this)

        val mainIntent = Intent(this, MainActivity::class.java)
        val mainPendingIntent = PendingIntent.getActivity(
            this, Constants.SERVICE_NOTIFICATION_INTENT_ID,
            mainIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, Constants.NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Activity tracking enabled")
            .setContentText("Detecting your activity")
            .setSmallIcon(R.drawable.ic_steps)
            .setContentIntent(mainPendingIntent)
            .setShowWhen(false)
            .setOngoing(true)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}