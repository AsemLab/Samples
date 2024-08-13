package com.asemlab.samples.navigation_component

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.asemlab.samples.navigation_component.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNotificationPermission()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val controller =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

        binding.bottomNav.setupWithNavController(controller)

        // TODO Pass data to start destination
        controller.setGraph(R.navigation.main_graph, bundleOf("pageTitle" to "Fake News"))

//        binding.bottomNav.setOnItemReselectedListener {
//            when (it.itemId) {
//                R.id.news_nav -> {
//                    controller.navController.popBackStack(R.id.news_nav, false)
//                }
//            }
//        }

        // TODO Receive results in the host activity
        supportFragmentManager
            .setFragmentResultListener("has_open_sports", this) { _, bundle ->
                val result = bundle.getBoolean("open_sports")
                Toast.makeText(
                    this,
                    "Result in Activity\nSports page has opened: $result",
                    Toast.LENGTH_LONG
                ).show()
            }

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