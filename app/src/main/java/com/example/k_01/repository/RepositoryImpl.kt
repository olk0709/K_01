package com.example.k_01.repository

class RepositoryImpl:Repository {
    override fun getWeatherFromServer() {
        Thread.sleep(2000L)
        return Weather()
    }

    override fun getWeatherFromLocalStorage() {
        return Weather()
    }
}