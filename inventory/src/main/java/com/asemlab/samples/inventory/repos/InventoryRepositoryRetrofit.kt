package com.asemlab.samples.inventory.repos

import com.asemlab.samples.inventory.database.InventoryDao
import com.asemlab.samples.inventory.remote.InventoryService
import com.asemlab.samples.inventory.remote.models.ItemResponse
import com.asemlab.samples.inventory.remote.models.ItemsQuantityResponse
import com.asemlab.samples.inventory.remote.models.ServerResponse
import com.asemlab.samples.inventory.utils.performRequest
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