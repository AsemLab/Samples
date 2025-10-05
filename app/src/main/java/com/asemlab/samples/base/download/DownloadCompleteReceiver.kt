package com.asemlab.samples.base.download

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

// TODO If you want to listener when downloading has finished
class DownloadCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.DOWNLOAD_COMPLETE")
            Toast.makeText(context, "Download Completed!", Toast.LENGTH_SHORT).show()
    }
}