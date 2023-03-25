package com.example.k_01.repository

import com.example.k_01.viewmodel.DetailsViewModel


interface DetailsRepositoryOne {
    fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback)
}