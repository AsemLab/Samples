package com.asemlab.inventory.repos

import com.asemlab.inventory.database.InventoryDao
import com.asemlab.inventory.remote.InventoryService
import com.asemlab.inventory.remote.models.ItemResponse
import com.asemlab.inventory.remote.models.ItemsQuantityResponse
import com.asemlab.inventory.remote.models.ServerResponse
import com.asemlab.inventory.utils.performRequest
import javax.inject.Inject

class InventoryRepositoryRetrofit @Inject constructor(
    private val inventoryService: InventoryService,
    private val inventoryDao: InventoryDao
) : InventoryRepository {

    override suspend fun getAllItemsOffline(): List<ItemResponse> = inventoryDao.getAllItems()

    override suspend fun getItemsQuantityOffline(): List<ItemsQuantityResponse> =
        inventoryDao.getAllItemsQuantity()

    override suspend fun fetchAllItems(): ServerResponse<List<ItemResponse>> {
        return performRequest {
            inventoryService.getAllItems()
        }
    }

    override suspend fun fetchItemsQuantity(): ServerResponse<List<ItemsQuantityResponse>> {
        return performRequest {
            inventoryService.getAllItemsQuantity()
        }
    }

    override suspend fun insertAllItems(items: List<ItemResponse>) {
        inventoryDao.addAllItems(items)
    }

    override suspend fun insertItemsQuantity(items: List<ItemsQuantityResponse>) {
        inventoryDao.addAllItemsQuantity(items)
    }
}