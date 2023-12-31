package com.asemlab.samples.koin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.asemlab.samples.koin.model.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("DELETE FROM users")
    fun clearUsers()
}