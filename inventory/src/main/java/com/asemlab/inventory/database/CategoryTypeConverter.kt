package com.asemlab.inventory.database


import androidx.room.TypeConverter
import com.asemlab.inventory.remote.models.CategoryResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryTypeConverter {

    @TypeConverter
    fun fromCategory(value: CategoryResponse): String? {
        val gson = Gson()
        val type = object : TypeToken<CategoryResponse>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCategory(value: String): CategoryResponse {
        val gson = Gson()
        val type = object : TypeToken<CategoryResponse>() {}.type
        return gson.fromJson(value, type)
    }
}