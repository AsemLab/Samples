package com.asemlab.quakes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.asemlab.quakes.databinding.ActivityMainBinding
import com.asemlab.samples.feature_delivery.interfaces.ToastUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.navHost)
        navView.setupWithNavController(navController)


        // TODO Access feature module code using reflection
        val toast =
            Class.forName("com.asemlab.quakes.settings.ToastUtilsImp").getDeclaredConstructor()
                .newInstance()

        // TODO Access feature module code using reflection as singleton object
//        val toast = Class.forName("com.asemlab.quakes.settings.ToastUtilsImp").kotlin.objectInstance
        (toast as ToastUtils).showToast(this, "Toast from settings module")


        // TODO Access feature module resources
        val hello = getString(
            resources.getIdentifier(
                "hello_blank_fragment",
                "string",
                "com.asemlab.quakes.settings"
            )
        )
        (toast as ToastUtils).showToast(this, hello)


    }
}