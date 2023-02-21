package com.example.k_01.repository

data class Weather(val сity: City= getDefaultCity(), val temperature:Int=0, val feelsLike:Int=0)

fun getDefaultCity() = City("МОСКВА", 55.75, 37.61)

data class City(val name: String, val lat:Double, val lon:Double)