package com.asemlab.inventory.remote.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Entity("items")
data class ItemResponse(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("category")
    val category: CategoryResponse? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("quantity")
    val quantity: Double? = null
)