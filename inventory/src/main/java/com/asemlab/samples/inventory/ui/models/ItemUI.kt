package com.asemlab.samples.inventory.ui.models

import com.asemlab.samples.inventory.remote.models.ItemResponse

data class ItemUI(
    val id: String = "",
    val name: String? = null,
    val category: String? = null,
    var quantity: Double = 0.0,
    val image: String = ""
)

fun ItemResponse.toItemUI() = ItemUI(
    id = this.id,
    name = this.name,
    category = this.category?.name,
    image = this.image ?: ""
)
