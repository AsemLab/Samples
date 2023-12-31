package com.asemlab.samples.koin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asemlab.samples.koin.model.User

// TODO Used for explanation
@Database(version = 1, exportSchema = false, entities = [User::class])
abstract class UserDatabase : RoomDatabase() {

    abstract fun provideUserDao(): UserDao

    fun init() {
        with(provideUserDao()) {
            clearUsers()
            repeat(5) {
                insert(User(name = "User ${it + 1}"))
            }
        }
    }
}