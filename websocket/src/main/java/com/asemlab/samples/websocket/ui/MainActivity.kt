package com.asemlab.samples.websocket.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.websocket.R
import com.asemlab.samples.websocket.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            mainViewModel = viewModel
            lifecycleOwner = this@MainActivity
        }

        with(viewModel) {

            initLineChart(binding.lineChart)
            currency.observe(this@MainActivity) {
                prices.value = listOf()
                currentPrice.value = "0.0"
                highestPrice.value = "0.0"
                lowestPrice.value = "0.0"
                binding.currencyTV.text = it.title.capitalize()

                sendCurrency()
            }

            prices.observe(this@MainActivity) {
                updateLineChart(binding.lineChart, it ?: emptyList())
            }
        }

    }
}