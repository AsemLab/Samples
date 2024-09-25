package com.asemlab.samples

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.coroutineScope
import com.asemlab.samples.utils.NotificationsUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotificationProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification_progress)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(NotificationsUtils) {
            createNotificationsChannel(this@NotificationProgressActivity)
            var builder = createNotificationBuilder(this@NotificationProgressActivity)

            lifecycle.coroutineScope.launch {
                var progress = 0
                while (progress < 10) {
                    progress += 1
                    delay(1000)

                    // TODO How to display progress within notification
                    builder.setProgress(10, progress, false)
                    sendNotification(1024, builder.build())
                }

                builder = createNotificationBuilderDone(this@NotificationProgressActivity)
                sendNotification(1024, builder.build())
            }

        }

    }
}