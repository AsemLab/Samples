package com.asemlab.samples.appdistribution

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.appdistribution.InterruptionLevel
import com.google.firebase.appdistribution.appDistribution

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                permissionRequest.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                startAppDistributionNotification()
            }
        } else {
            startAppDistributionNotification()
        }
    }

    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            it?.let {
                if (it)
                    startAppDistributionNotification()
            }
        }

    private fun startAppDistributionNotification() {
        // TODO Display feedback notification
        Firebase.appDistribution.showFeedbackNotification(
            "Click to submit feedback",
            InterruptionLevel.HIGH
        )
    }
}