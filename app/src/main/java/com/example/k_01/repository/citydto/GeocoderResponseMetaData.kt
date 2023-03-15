package com.example.k_01.repository.citydto

data class GeocoderResponseMetaData(
    val Point: Point,
    val found: String,
    val request: String,
    val results: String
)