package com.asemlab.samples.broadcast_receiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO Register Broadcast Receiver dynamically
        // Can receive actions as this app is running
        ContextCompat.registerReceiver(
            this,
            MyReceiver(),
            IntentFilter("com.asemlab.samples.broadcastreceiver.MyCode"),
            ContextCompat.RECEIVER_EXPORTED
        )

        // TODO to test Broadcast Receiver, can be used from other apps while this app is open
        lifecycleScope.launch {
            delay(3000)
            sendBroadcast(Intent("com.asemlab.samples.broadcastreceiver.MyCode"))
        }
    }
}