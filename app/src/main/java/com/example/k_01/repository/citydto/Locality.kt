package com.example.k_01.repository.citydto

data class Locality(
    val DependentLocality: DependentLocality,
    val LocalityName: String,
    val Thoroughfare: Thoroughfare
)