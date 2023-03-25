package com.example.k_01.repository

import com.example.k_01.repository.citydto.GeoObjectCollection
import com.example.k_01.repository.dto.WeatherDTO
import com.example.k_01.utils.YANDEX_API_KEY
import com.example.k_01.utils.YANDEX_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CityAPI {
    @GET("1.x")// только endpoint
    fun getCity(
        @Query("geocode") geocode: String="Моск",
        @Query("bbox") bbox: String="38.7515,45.9959~49.0265,51.7342",
        @Query("apikey")apikey: String= "7d437612-78d3-42d2-91a1-8da53abb15b2",
        @Query("results") results:Int=100,
        @Query("format") format:String ="json",
    ): Call<GeoObjectCollection>
}