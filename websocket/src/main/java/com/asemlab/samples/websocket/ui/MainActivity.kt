package com.asemlab.samples.websocket.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.websocket.R
import com.asemlab.samples.websocket.databinding.ActivityMainBinding

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
            currency.observe(this@MainActivity) {

                // TODO: Send new currency to Socket

                Toast.makeText(this@MainActivity, "${it.title}, ${it.symbol}", Toast.LENGTH_SHORT)
                    .show()

                binding.currencyTV.text = it.title.capitalize()
            }

            prices.observe(this@MainActivity) {
                drawLineChart(binding.lineChart, it ?: emptyList())
            }

        }

    }
}