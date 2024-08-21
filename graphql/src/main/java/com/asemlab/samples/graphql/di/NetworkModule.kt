package com.asemlab.samples.graphql.di

import com.apollographql.apollo.ApolloClient
import com.asemlab.samples.graphql.data.CountriesService
import com.asemlab.samples.graphql.data.CountriesServiceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://countries.trevorblades.com/graphql"

    // TODO Create Apollo Client, same as Retrofit
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl(BASE_URL).build()
    }


    @Provides
    @Singleton
    fun provideCountriesService(apolloClient: ApolloClient): CountriesService {
        return CountriesServiceImp(apolloClient)
    }

}