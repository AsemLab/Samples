package com.asemlab.samples.ktor.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.ktor.R
import com.asemlab.samples.ktor.databinding.ActivityMainBinding
import com.asemlab.samples.ktor.models.PostResponse
import com.asemlab.samples.ktor.remote.CountryService
import com.asemlab.samples.ktor.remote.PostService
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> {
        val countryService by inject<CountryService>()
        val postService by inject<PostService>()
        MainViewModelFactory(countryService, postService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}