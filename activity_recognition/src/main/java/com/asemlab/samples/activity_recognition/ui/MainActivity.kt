package com.asemlab.samples.activity_recognition.ui

// Developed by: Asem Abu alrub // AsemLab © 2025

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.asemlab.samples.activity_recognition.R
import com.asemlab.samples.activity_recognition.databinding.ActivityMainBinding
import com.asemlab.samples.activity_recognition.services.ActivityUpdatesReceiver
import com.asemlab.samples.activity_recognition.services.TransitionsReceiver
import com.asemlab.samples.activity_recognition.utilties.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // TODO Create an instance from the custom receiver and un/register it
    private var receiver = ActivityUpdatesReceiver()
//    private var receiver: TransitionsReceiver = TransitionsReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val navView: BottomNavigationView = binding.navView

        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment).navController

        navView.setupWithNavController(navController)

    }

    // TODO Register and unregister with suitable filter
    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }

    override fun onStart() {
        super.onStart()
        ContextCompat.registerReceiver(
            this,
            receiver,
            IntentFilter(Constants.ACTIVITY_UPDATES_RECEIVER_ACTION), // OR TRANSITIONS_RECEIVER_ACTION
            ContextCompat.RECEIVER_EXPORTED
        )
    }

}