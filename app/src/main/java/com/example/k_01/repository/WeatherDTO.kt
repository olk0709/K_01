package com.example.k_01.repository

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("factDTO")
    val factDTO: FactDTO,
    @SerializedName("forecastDTO")
    val forecastDTO: ForecastDTO,
    @SerializedName("infoDTO")
    val infoDTO: InfoDTO,
    @SerializedName("now")
    val now: Int,
    @SerializedName("now_dt")
    val now_dt: String
)