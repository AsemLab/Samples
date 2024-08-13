package com.asemlab.samples.feature.home.ui

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.feature.home.databinding.FragmentCountriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : Fragment() {

    private lateinit var binding: FragmentCountriesBinding
    private val mainViewModel by viewModels<MainViewModel>()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountriesBinding.inflate(inflater, container, false)

        with(mainViewModel) {
            countries.observe(viewLifecycleOwner) {
                it?.let {
                    binding.countriesRV.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = CountryAdapter { country ->

                            // TODO Navigate between modules
                            val deepLink =
                                NavDeepLinkRequest.Builder.fromUri(Uri.parse("www.asemlab.dev/addHotel"))
                                    .setAction("Add")
                                    .build()
                            findNavController().navigate(deepLink)
                        }.apply {
                            addItems(it)
                        }
                    }
                }
            }

            getCountries()
        }

        return binding.root
    }
}