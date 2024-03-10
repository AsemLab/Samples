package com.asemlab.samples.chuncker.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.chuncker.R
import com.asemlab.samples.chuncker.databinding.ActivityMainBinding
import com.asemlab.samples.chuncker.models.PostResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(mainViewModel) {
            countries.observe(this@MainActivity) {
                it?.let {
                    binding.countriesRV.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = CountryAdapter { country ->
                            createPost(PostResponse(country.name!!.common, country.region, 1))
                        }.apply {
                            addItems(it)
                        }
                    }
                }
            }

            newPost.observe(this@MainActivity) {
                it?.let {
                    Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            getCountries()
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 200)
        }
    }
}