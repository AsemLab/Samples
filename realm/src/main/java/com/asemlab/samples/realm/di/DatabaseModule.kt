package com.asemlab.samples.realm.di

import com.asemlab.samples.realm.database.PersonRepoImp
import com.asemlab.samples.realm.database.PersonRepository
import com.asemlab.samples.realm.model.Person
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRealm(): Realm {
        // TODO Create Realm database file
        val config = RealmConfiguration.Builder(schema = setOf(Person::class))
            .initialData {
                copyToRealm(Person().apply {
                    name = "Emergency"
                    phoneNumber = "911"
                })
            }
            .build()
        // TODO Open Realm database
        return Realm.open(config)
    }

    @Provides
    @Singleton
    fun providePersonRepository(realm: Realm): PersonRepository {
        return PersonRepoImp(realm)
    }

}