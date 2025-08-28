package com.asemlab.inventory.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asemlab.inventory.remote.models.ItemResponse
import com.asemlab.inventory.remote.models.ItemsQuantityResponse

@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllItems(items: List<ItemResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllItemsQuantity(items: List<ItemsQuantityResponse>)

    @Query("SELECT * FROM items")
    fun getAllItems(): List<ItemResponse>

    @Query("SELECT * FROM quantities")
    fun getAllItemsQuantity(): List<ItemsQuantityResponse>

}