package com.asemlab.samples.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

// TODO Extend BroadcastReceiver class
class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context, "MyReceiver is working!", Toast.LENGTH_LONG).show()

        Log.d("MyReceiver", "MyReceiver is working")

    }
}