package com.asemlab.samples.unittesting.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var state: String
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        state = "Created"
        binding.button.setOnClickListener {
            binding.titleTV.setText(R.string.new_title)
        }

        binding.permissionButton.setOnClickListener {
            permissionRequest.launch(Manifest.permission.CALL_PHONE)
        }
    }

    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            it?.let {
                val result = if (it) R.string.permission_allow else R.string.permission_deny

                binding.titleTV.setText(result)
            }
        }


    override fun onPause() {
        super.onPause()
        state = "Paused"
    }

    override fun onStop() {
        super.onStop()
        state = "Stopped"
    }


}