package com.asemlab.samples.firestore.ui.fragments.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.firestore.databinding.FragmentHotelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelFragment : Fragment() {

    private val viewModel by viewModels<HotelViewModel>()
    private val navArgs by navArgs<HotelFragmentArgs>()
    private lateinit var binding: FragmentHotelBinding
    private lateinit var adapter: RatesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(inflater, container, false)
        adapter = RatesAdapter(navArgs.hotel.rates?.toMutableList() ?: mutableListOf())

        with(binding) {
            hotel = navArgs.hotel
            ratingsRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = this@HotelFragment.adapter
            }

            emptyRatingTV.isVisible = adapter.itemCount == 0

            ratings.text =
                "${ratings.text}  ${(navArgs.hotel.rates?.sumOf { it.score } ?: 5) / (navArgs.hotel.rates?.size ?: 1)} of 5"

            back.setOnClickListener {
                findNavController().navigateUp()
            }
            delete.setOnClickListener {
                viewModel.deleteHotel(hotel!!)
                findNavController().navigateUp()
            }
            addRate.setOnClickListener {
                RateBottomSheet.getInstance {
                    viewModel.addRate(it)
                }.show(parentFragmentManager, "RateBottomSheet")
            }
        }

        return binding.root
    }


}