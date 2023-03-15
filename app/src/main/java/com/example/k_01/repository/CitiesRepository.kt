package com.example.k_01.repository

import com.example.k_01.repository.dto.WeatherDTO
import com.example.k_01.viewmodel.DetailsViewModel


interface CitiesRepository {
    fun getCityList(city: City, callback: DetailsViewModel.Callback)
}