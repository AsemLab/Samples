package com.asemlab.samples.inventory.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.inventory.di.RepositoryModule
import com.asemlab.samples.inventory.remote.models.ItemsQuantityResponse
import com.asemlab.samples.inventory.remote.models.performOnError
import com.asemlab.samples.inventory.remote.models.performOnSuccess
import com.asemlab.samples.inventory.repos.InventoryRepository
import com.asemlab.samples.inventory.ui.models.HomeState
import com.asemlab.samples.inventory.ui.models.ItemUI
import com.asemlab.samples.inventory.ui.models.toItemUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@RepositoryModule.RetrofitRepository private val inventoryRepository: InventoryRepository) :
    ViewModel() {

    private val items = mutableListOf<ItemUI>()
    private val quantities = mutableListOf<ItemsQuantityResponse>()
    private val _state = MutableStateFlow(HomeState())
    val state = _state

    fun fetchAllItems() {
        launchCoroutine {
            inventoryRepository
                .fetchAllItems()
                .performOnSuccess { response ->

                    inventoryRepository.insertAllItems(response)

                    val mapped = response.map { it.toItemUI() }
                    items.clear()
                    items.addAll(mapped)

                    _state.update {
                        it.copy(products = items, isLoading = false)
                    }

                    fetchItemsQuantity()

                }.performOnError { e ->
                    _state.update {
                        it.copy(errorMessage = e, isLoading = false)
                    }
                }

        }
    }

    private fun fetchItemsQuantity() {
        launchCoroutine {
            inventoryRepository
                .fetchItemsQuantity()
                .performOnSuccess { response ->

                    val quantityResponses = response

                    inventoryRepository.insertItemsQuantity(quantityResponses)

                    updateQuantities(quantityResponses)

                    _state.update {
                        it.copy(products = items, isLoading = false)
                    }

                }.performOnError { e ->
                    _state.update {
                        it.copy(errorMessage = e, isLoading = false)
                    }
                }
        }
    }

    fun getAllItemsOffline() {
        launchCoroutine {
            val mapped = inventoryRepository.getAllItemsOffline().map { it.toItemUI() }

            items.clear()
            items.addAll(mapped)

            _state.update {
                it.copy(products = items, isLoading = false)
            }

            getItemsQuantityOffline()
        }
    }

    private fun getItemsQuantityOffline() {
        launchCoroutine {
            val quantityResponses = inventoryRepository
                .getItemsQuantityOffline()

            updateQuantities(quantityResponses)

            _state.update {
                it.copy(products = items, isLoading = false)
            }

        }
    }

    private fun updateQuantities(quantityResponses: List<ItemsQuantityResponse>) {
        quantities.clear()
        quantities.addAll(quantityResponses)

        quantityResponses.forEach { q ->
            items.find {
                it.id == q.productId
            }?.quantity = q.quantity ?: 0.0
        }
    }

    fun searchProducts(query: String) {
        launchCoroutine {
            delay(250)
            state.update { state ->
                val filtered =
                    items.filter { p ->
                        p.name?.contains(query, true) == true
                    }

                state.copy(products = filtered, isLoading = false)
            }
        }
    }

    fun launchCoroutine(block: suspend () -> Unit) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            withContext(Dispatchers.IO) {
                block()
            }
        }

    }
}