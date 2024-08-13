package com.asemlab.samples.feature.hotel.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.feature.hotel.R
import com.asemlab.samples.feature.hotel.databinding.FragmentHomeBinding
import com.asemlab.samples.feature.hotel.model.Filter
import com.asemlab.samples.feature.hotel.model.SortBy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HotelsAdapter
    private lateinit var sortByMenu: PopupMenu
    private lateinit var filterMenu: PopupMenu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = HotelsAdapter(mutableListOf()) {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToHotelFragment(
                    it
                )
            )
        }

        with(binding) {
            hotelsRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = this@HomeFragment.adapter
            }

            addHotelButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addHotelFragment)
            }

            sortBy.setOnClickListener {
                sortByMenu.show()
            }
            filter.setOnClickListener {
                filterMenu.show()
            }
        }

        viewModel.hotels.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateData(it)
            }
        }

        sortByMenu = PopupMenu(requireContext(), binding.sortBy).apply {
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.nameMenu -> viewModel.sortHotels(SortBy.NAME)
                    R.id.starMenu -> viewModel.sortHotels(SortBy.STARS)
                    R.id.rateMenu -> viewModel.sortHotels(SortBy.RATE)
                }
                return@setOnMenuItemClickListener true
            }
            inflate(R.menu.sort_menu)
        }
        filterMenu = PopupMenu(requireContext(), binding.filter).apply {
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.fiveStarsFilter -> viewModel.filterHotels(Filter.STARS, 5)
                    R.id.poolFilter -> viewModel.filterHotels(Filter.HAVE_POOL, true)
                    R.id.wifiFilter -> viewModel.filterHotels(Filter.HAVE_WIFI, true)
                }
                return@setOnMenuItemClickListener true
            }
            inflate(R.menu.filter_menu)
        }

        return binding.root
    }


}