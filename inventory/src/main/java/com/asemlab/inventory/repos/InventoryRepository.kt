package com.asemlab.inventory.repos

import com.asemlab.inventory.remote.models.ItemResponse
import com.asemlab.inventory.remote.models.ItemsQuantityResponse
import com.asemlab.inventory.remote.models.ServerResponse

interface InventoryRepository {

    suspend fun getAllItemsOffline(): List<ItemResponse>
    suspend fun getItemsQuantityOffline(): List<ItemsQuantityResponse>
    suspend fun fetchAllItems(): ServerResponse<List<ItemResponse>>
    suspend fun fetchItemsQuantity(): ServerResponse<List<ItemsQuantityResponse>>
    suspend fun insertAllItems(items: List<ItemResponse>)
    suspend fun insertItemsQuantity(items: List<ItemsQuantityResponse>)

}