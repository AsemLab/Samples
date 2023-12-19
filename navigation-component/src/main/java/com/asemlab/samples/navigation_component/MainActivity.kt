package com.asemlab.samples.navigation_component

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.asemlab.samples.navigation_component.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNotificationPermission()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val controller =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

        binding.bottomNav.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, controller)
            true
        }

        // TODO Pass data to start destination
        controller.setGraph(R.navigation.main_graph, bundleOf("pageTitle" to "Fake News"))

//        binding.bottomNav.setOnItemReselectedListener {
//            when (it.itemId) {
//                R.id.news_nav -> {
//                    controller.navController.popBackStack(R.id.news_nav, false)
//                }
//            }
//        }

    }

    private fun checkNotificationPermission() {
        if (packageManager.checkPermission(
                Manifest.permission.POST_NOTIFICATIONS,
                packageName
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 55)
            }
        }
    }

    // TODO implement when Activity has launchMode rather than standard
//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        val controller =
//            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
//        controller.handleDeepLink(intent)
//    }

}