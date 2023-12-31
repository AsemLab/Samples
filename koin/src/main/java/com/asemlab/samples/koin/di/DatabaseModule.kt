package com.asemlab.samples.koin.di

import androidx.room.Room
import com.asemlab.samples.koin.database.UserDatabase
import org.koin.dsl.module

// TODO Declare Modules
val DatabaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            "user_db"
        ).build().apply { init() }
    }


}

val DaoModule = module {
    single {
        // TODO Declare properties from injected classes
        get<UserDatabase>().provideUserDao()
    }
}
