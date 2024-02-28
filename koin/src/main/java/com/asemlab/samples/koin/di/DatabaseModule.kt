package com.asemlab.samples.koin.di

import androidx.room.Room
import com.asemlab.samples.koin.database.CountryDatabase
import org.koin.dsl.module

// TODO Declare Modules
val DatabaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            CountryDatabase::class.java,
            "country_db"
        ).build().apply { init() }
    }


}

val DaoModule = module {
    single {
        // TODO Declare properties from injected classes
        get<CountryDatabase>().provideCountryDao()
    }
}
