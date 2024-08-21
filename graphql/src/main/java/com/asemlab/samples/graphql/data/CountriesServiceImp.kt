package com.asemlab.samples.graphql.data

import com.apollographql.apollo.ApolloClient
import com.asemlab.samples.graphql.model.DetailedCountry
import com.asemlab.samples.graphql.model.SimpleCountry
import com.asemlab.samples.graphql.model.toDetailedCountry
import com.asemlab.samples.graphql.model.toSimpleCountry
import com.asemlab.samples.qraphql.CountriesQuery
import com.asemlab.samples.qraphql.CountryQuery

class CountriesServiceImp(val apolloClient: ApolloClient) : CountriesService {


    override suspend fun getCountries(): List<SimpleCountry> {
        // TODO Execute GraphQL query
        return apolloClient.query(CountriesQuery()).execute().data?.countries?.map {
            it.toSimpleCountry()
        }?.sortedBy { it.name } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient.query(CountryQuery(code)).execute().data?.country?.toDetailedCountry()
    }
}