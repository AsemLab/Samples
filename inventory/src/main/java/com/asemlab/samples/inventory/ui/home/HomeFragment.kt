package com.asemlab.samples.inventory.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.asemlab.samples.inventory.R
import com.asemlab.samples.inventory.databinding.FragmentHomeBinding
import com.asemlab.samples.inventory.utils.hideKeyboard
import com.asemlab.samples.inventory.utils.isConnected
import com.asemlab.samples.inventory.utils.showSnackbarShortly
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapter = ProductsAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        with(binding.productsRv) {
            adapter = this@HomeFragment.adapter
        }

        with(binding) {
            swipeRefresh.setOnRefreshListener {
                fetchData()
                hideKeyboard(requireContext(), homeMain)
                searchBar.editText?.text?.clear()
                searchBar.editText?.clearFocus()
            }
            swipeRefresh.setColorSchemeResources(R.color.refresh)

            searchBar.editText?.doOnTextChanged { text, _, _, _ ->
                homeViewModel.searchProducts(text.toString())
            }
        }

        fetchData()

        lifecycleScope.launch {
            homeViewModel.state.collect {

                binding.loadingIndicator.isVisible = it.isLoading
                binding.swipeRefresh.isRefreshing = false

                if (it.isLoading) {
                    binding.productsRv.isVisible = false
                    binding.emptyGroup.isVisible = false
                } else if (it.errorMessage.isNotEmpty()) {
                    showSnackbarShortly(binding.root, it.errorMessage, getString(R.string.retry)) {
                        fetchData()
                    }
                    binding.emptyGroup.isVisible = true
                } else {
                    binding.emptyGroup.isVisible = it.products.isEmpty()
                    binding.productsRv.isVisible = it.products.isNotEmpty()
                    adapter.updateList(it.products)
                }
            }
        }

        return binding.root
    }


    private fun fetchData() {
        if (isConnected(requireContext())) {
            homeViewModel.fetchAllItems()
            binding.noConnectionTv.isVisible = false
        } else {
            homeViewModel.getAllItemsOffline()
            binding.noConnectionTv.isVisible = true
        }
    }
}