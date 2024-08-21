package com.asemlab.samples.graphql.model

data class DetailedCountry(
    val name: String,
    val code: String,
    val continent: String,
    val currency: String?,
    val emoji: String,
    val languages: List<String>,
)
