package com.example.k_01.repository

interface Repository {
    fun getWeatherFromServer():Weather
    fun getWorldWeatherFromLocalStorage():List<Weather>

    fun getRussionWeatherFromLocalStorage():List<Weather>
}