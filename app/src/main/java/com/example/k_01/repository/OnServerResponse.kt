package com.example.k_01.repository

fun interface OnServerResponse {
    fun onResponse(weatherDTO: WeatherDTO)
}