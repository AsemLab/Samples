package com.asemlab.samples.feature.home.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.feature.home.R
import com.asemlab.samples.feature.home.databinding.ActivityCountriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountriesBinding
    private val mainViewModel by viewModels<MainViewModel>()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_countries)

        with(mainViewModel) {
            countries.observe(this@CountriesActivity) {
                it?.let {
                    binding.countriesRV.apply {
                        layoutManager = LinearLayoutManager(this@CountriesActivity)
                        adapter = CountryAdapter { country ->
                            Toast.makeText(
                                this@CountriesActivity,
                                "${country.name} clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        }.apply {
                            addItems(it)
                        }
                    }
                }
            }

            getCountries()
        }

    }
}