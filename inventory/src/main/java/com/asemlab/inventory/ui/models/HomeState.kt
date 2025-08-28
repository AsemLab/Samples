package com.asemlab.inventory.ui.models

data class HomeState(
    val products: List<ItemUI> = emptyList(),
    var isLoading: Boolean = false,
    val errorMessage: String = "",
)