package com.asemlab.samples.feature.hotel.ui.fragments.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.feature.hotel.databinding.FragmentHotelBinding
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
        adapter = RatesAdapter(viewModel.rates.value?.toMutableList() ?: mutableListOf())

        with(binding) {
            hotel = navArgs.hotel
            ratingsRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = this@HotelFragment.adapter
            }

            back.setOnClickListener {
                findNavController().navigateUp()
            }
            delete.setOnClickListener {
                viewModel.deleteHotel(hotel!!, {
                    requireActivity().runOnUiThread {
                        findNavController().navigateUp()
                    }
                }, {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                })
            }

            addRate.setOnClickListener {
                RateBottomSheet.getInstance(navArgs.hotel.id) {
                    viewModel.addRate(it) { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }.show(parentFragmentManager, "RateBottomSheet")
            }
        }

        with(viewModel) {

            getRates(navArgs.hotel.id)

            rates.observe(viewLifecycleOwner) {
                binding.emptyRatingTV.isVisible = it?.isEmpty() ?: true

                it?.let {
                    val count = it.size.takeIf { s -> s > 0 } ?: 1
                    val sum = if (it.isEmpty()) 5 else it.sumOf { r -> r.score }
                    binding.ratings.text = "Ratings ${sum / count} of 5"

                    adapter.updateData(it)
                }
            }
        }

        return binding.root
    }


}