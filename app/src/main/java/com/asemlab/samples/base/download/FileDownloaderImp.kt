package com.asemlab.samples.base.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class FileDownloaderImp(private val context: Context) {

    private val downloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    // TODO Download any file with DownloadManager
    fun download(url: String, title: String, fileName: String, requireLongRun: Boolean): Long {
        val request = DownloadManager.Request(url.toUri())
            .setTitle(title)
//            .setMimeType("")
//            .setDestinationInExternalFilesDir() // Use to download in Android/Data/packageName
//            .setRequiresCharging(false)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                fileName
            )
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        if (requireLongRun)
            request.setShowRunningNotification(true)

        return downloadManager.enqueue(request)
    }
}