package com.asemlab.samples.viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asemlab.samples.viewpager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // TODO Add adapter to ViewPager2
        val adapter = PagerAdapter(this)
        binding.pager.adapter = adapter
    }
}