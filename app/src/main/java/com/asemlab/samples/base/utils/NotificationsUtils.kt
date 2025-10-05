package com.asemlab.samples.base.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.asemlab.samples.base.R

object NotificationsUtils {

    private lateinit var notificationManager: NotificationManager

    fun createNotificationsChannel(context: Context) {
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "ProgressChannel",
                "Progress Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotificationBuilder(context: Context): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, "ProgressChannel")
            .setSmallIcon(R.drawable.ic_download)
            .setContentTitle("File Download")
            .setContentText("Downloading...")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
    }

    fun createNotificationBuilderDone(context: Context): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, "ProgressChannel")
            .setSmallIcon(R.drawable.ic_download_done)
            .setContentTitle("Download Complete")
            .setContentText("Test.jpg downloaded successfully")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setProgress(0, 0, false)
    }

    fun cancelNotification(notificationId: Int) {
        notificationManager.cancel(notificationId)
    }

    fun sendNotification(notificationId: Int, notification: Notification) {
        notificationManager.notify(notificationId, notification)
    }


}