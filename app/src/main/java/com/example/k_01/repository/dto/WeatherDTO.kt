package com.example.k_01.repository.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
):Parcelable