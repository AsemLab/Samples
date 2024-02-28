package com.asemlab.samples.koin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.koin.database.CountryDao
import com.asemlab.samples.koin.databinding.ActivityMainBinding
import com.asemlab.samples.koin.di.BING_NAME
import com.asemlab.samples.koin.model.Country
import com.asemlab.samples.koin.model.SearchEngine
import com.asemlab.samples.koin.model.performOnError
import com.asemlab.samples.koin.model.performOnSuccess
import com.asemlab.samples.koin.remote.CountryService
import com.asemlab.samples.koin.utils.performRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named


class MainActivity : AppCompatActivity() {

    // TODO Inject from Koin
    private val countryDao by inject<CountryDao>()

    // TODO Inject from Koin using qualifiers
    private val searchEngine by inject<SearchEngine>(named(BING_NAME)) // OR YOUTUBE_NAME

    private val countryService by inject<CountryService>()

    private lateinit var binding: ActivityMainBinding
    private val historyItems = MutableLiveData<List<Country>>(emptyList())
    private val historyAdapter = HistoryAdapter(historyItems.value!!.toMutableList()) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${searchEngine.url}$it"))
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getHistory()

        with(binding) {

            historyRV.layoutManager = LinearLayoutManager(this@MainActivity)
            historyRV.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayout.VERTICAL
                )
            )
            historyRV.adapter = historyAdapter

            getCapital.setOnClickListener {
                lifecycleScope.launch {
                    val country = binding.countryET.text.toString()
                    getCapital(country)
                    binding.countryET.text.clear()
                }
            }
            defaultSearch.text = searchEngine.name
        }

        historyItems.observe(this) {
            historyAdapter.updateData(it)
        }

    }

    private suspend fun getCapital(country: String) {
        withContext(Dispatchers.IO) {
            performRequest {
                countryService.getCountryCapital(country)
            }.performOnSuccess {
                insertCountry(Country(country, it[0].toString()))
                getHistory()
            }.performOnError {
                lifecycleScope.launch {
                    insertCountry(Country(country, "Unknown"))
                    getHistory()
                }
            }
        }
    }

    private fun getHistory() {
        lifecycleScope.launch {
            historyItems.value = lifecycleScope.async {
                withContext(Dispatchers.IO) {
                    countryDao.getAll()
                }
            }.await()
        }
    }

    private suspend fun insertCountry(country: Country) {
        lifecycleScope.async {
            withContext(Dispatchers.IO) {
                countryDao.insert(country)
            }
        }.await()
    }
}