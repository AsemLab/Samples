package com.asemlab.samples.graphql.ui

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.graphql.R
import com.asemlab.samples.graphql.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter: CountriesAdapter


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
            countriesRV.apply {
                addItemDecoration(
                    DividerItemDecoration(
                        this@MainActivity,
                        LinearLayout.VERTICAL
                    )
                )
                layoutManager = LinearLayoutManager(this@MainActivity)
                this@MainActivity.adapter = CountriesAdapter(mutableListOf()) {
                    mainViewModel.getCountry(it.code)
                }
                adapter = this@MainActivity.adapter
            }
        }

        with(mainViewModel) {
            lifecycleScope.launch {
                state.flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED).collect {
                    binding.loadingIndicator.isVisible = it.isLoading
                    adapter.updateData(it.countries)
                }
            }
            lifecycleScope.launch {
                countryState.collect {
                    binding.loadingIndicator.isVisible = it.isLoading

                    it.selectedCountry?.let { country ->
                        val msg = buildString {
                            append("Continent: ${country.continent}\n")
                            append("Currency: ${country.currency}\n")
                            append("Code: ${country.code}\n")
                            append("Languages: ${country.languages.joinToString(", ")}\n")
                        }
                        AlertDialog.Builder(this@MainActivity)
                            .setTitle("${country.emoji} ${country.name}")
                            .setMessage(msg)
                            .setOnDismissListener {
                                dismissDialog()
                            }
                            .show()
                    }
                }
            }
        }

    }
}