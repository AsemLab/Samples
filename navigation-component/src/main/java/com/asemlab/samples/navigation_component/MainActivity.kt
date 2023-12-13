package com.asemlab.samples.navigation_component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.asemlab.samples.navigation_component.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val controller =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

        binding.bottomNav.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, controller)
            true
        }

//        binding.bottomNav.setOnItemReselectedListener {
//            when (it.itemId) {
//                R.id.news_nav -> {
//                    controller.navController.popBackStack(R.id.news_nav, false)
//                }
//            }
//        }


    }
}