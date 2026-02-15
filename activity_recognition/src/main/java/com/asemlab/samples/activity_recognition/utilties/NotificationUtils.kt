package com.asemlab.samples.activity_recognition.utilties

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.asemlab.samples.activity_recognition.R
import com.asemlab.samples.activity_recognition.ui.MainActivity

object NotificationUtils {

    fun createNotificationChannel(context: Context) {
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


    fun createNotification(context: Context, newContent: String): Notification {
        createNotificationChannel(context)

        val mainIntent = Intent(context, MainActivity::class.java)
        val mainPendingIntent = PendingIntent.getActivity(
            context, Constants.SERVICE_NOTIFICATION_INTENT_ID,
            mainIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Activity tracking enabled")
            .setContentText(newContent)
            .setSmallIcon(R.drawable.ic_steps)
            .setContentIntent(mainPendingIntent)
            .setCustomContentView(createStepsNotificationLayout(context, newContent))
            .setCustomBigContentView(createStepsNotificationLayout(context, newContent))
            .setShowWhen(false)
            .setOngoing(true)
            .build()
    }

    fun createStepsNotificationLayout(context: Context, steps: String): RemoteViews {
        return RemoteViews(context.packageName, R.layout.steps_notification).apply {
            setTextViewText(R.id.steps_tv, steps)
        }
    }

    fun updateNotification(context: Context, id: Int, newContent: String) {
        val manager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(id, createNotification(context, newContent))
    }

}