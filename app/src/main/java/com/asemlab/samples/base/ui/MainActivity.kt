package com.asemlab.samples.base.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.asemlab.samples.base.R
import com.asemlab.samples.base.download.FileDownloaderImp


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 200)
        }

        // TODO Use DownloadManager
        FileDownloaderImp(this).download(
            "https://english.ahram.org.eg/Media/News/2011/7/1/2011-634451360987580451-758.jpg",
            "Download image",
            "Inter.jpg",
            true
        )

    }

    fun selectFragment(view: View) {

        val fr: Fragment = if (view == findViewById(R.id.frag1)) {
            TestFrag("Fragment 1")
        } else {
            TestFrag("Fragment 2")
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_section, fr)


        fragmentTransaction.commit()
    }

}