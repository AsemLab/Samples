package com.asemlab.samples.graphql.model

data class SimpleCountry(
    val name: String,
    val capital: String?,
    val code: String,
    val emoji: String
)