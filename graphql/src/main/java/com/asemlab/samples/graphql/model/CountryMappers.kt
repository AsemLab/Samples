package com.asemlab.samples.graphql.model

import com.asemlab.samples.qraphql.CountriesQuery
import com.asemlab.samples.qraphql.CountryQuery

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        name = name,
        capital = capital,
        code = code,
        emoji = emoji
    )
}

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        name = name,
        code = code,
        emoji = emoji,
        continent = continent.name,
        languages = languages.map { it.name },
        currency = currency
    )
}