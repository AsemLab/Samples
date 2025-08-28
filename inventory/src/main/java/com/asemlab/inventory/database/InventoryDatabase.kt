package com.asemlab.inventory.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.asemlab.inventory.remote.models.ItemResponse
import com.asemlab.inventory.remote.models.ItemsQuantityResponse

@Database(
    version = 1,
    exportSchema = false,
    entities = [ItemResponse::class, ItemsQuantityResponse::class],
)
@TypeConverters(value = [CategoryTypeConverter::class])
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun provideInventoryDao(): InventoryDao


}