package com.asemlab.samples

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


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



        // TODO Show notifications to see how LeakCanary works

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

    companion object {
        // TODO This object will leak the memory
        lateinit var context: Context
    }

}