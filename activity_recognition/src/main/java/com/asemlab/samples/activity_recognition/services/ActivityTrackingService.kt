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
import com.asemlab.samples.activity_recognition.utilties.NotificationUtils

class ActivityTrackingService : Service() {


    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        val notification = NotificationUtils.createNotification(this, "0")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                Constants.SERVICE_NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_HEALTH
            )
        } else
            startForeground(Constants.SERVICE_NOTIFICATION_ID, notification)

        ActivityDetectionUtility.switchUpdatesDetecting(this)
//        ActivityDetectionUtility.switchTransitionsDetecting(this)
        return START_STICKY
    }

    override fun onDestroy() {
        ActivityDetectionUtility.switchUpdatesDetecting(this)
//        ActivityDetectionUtility.switchTransitionsDetecting(this)
        super.onDestroy()
    }


    override fun onBind(intent: Intent?): IBinder? = null
}