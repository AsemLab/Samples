package com.asemlab.samples.inventory.remote

import com.asemlab.samples.inventory.remote.models.ItemResponse
import com.asemlab.samples.inventory.remote.models.ItemsQuantityResponse
import retrofit2.Response
import retrofit2.http.GET

interface InventoryService {

    @GET("products")
    suspend fun getAllItems(): Response<List<ItemResponse>>


    @GET("quantities")
    suspend fun getAllItemsQuantity(): Response<List<ItemsQuantityResponse>>

}