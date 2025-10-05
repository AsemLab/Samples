package com.asemlab.samples.inventory.remote.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity("quantities")
data class ItemsQuantityResponse(
    @PrimaryKey
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("warehouse_id")
    val warehouseId: Int? = null,
    @SerializedName("quantity")
    val quantity: Double? = null
)