package com.example.k_01.utils

import com.example.k_01.domain.room.HistoryEntity
import com.example.k_01.repository.City
import com.example.k_01.repository.Weather
import com.example.k_01.repository.dto.FactDTO
import com.example.k_01.repository.dto.WeatherDTO
import com.example.k_01.repository.getDefaultCity

const val KEY_BUNDLE_WEATHER = "weather"
const val YANDEX_DOMAIN = "https://api.weather.yandex.ru/"
const val YANDEX_PATH = "v2/informers?"
const val YANDEX_ENDPOINT = "v2/informers?"
const val YANDEX_API_KEY = "X-Yandex-API-Key"
const val KEY_BUNDLE_LAT = "lat1"
const val KEY_BUNDLE_LON = "lon1"
const val KEY_BUNDLE_SERVICE_BROADCAST_WEATHER = "weather_s_b"
const val KEY_WAVE_SERVICE_BROADCAST = "myaction"

const val KEY_BUNDLE_SERVICE_MESSAGE = "key2"
const val KEY_BUNDLE_ACTIVITY_MESSAGE = "key1"
const val KEY_WAVE = "myaction"

const val KEY_SP_FILE_NAME_1 = "filename1"
const val KEY_SP_FILE_NAME_1_KEY_IS_RUSSIAN = "is_russian"

class Utils {
}

fun convertDtoModel(weatherDTO: WeatherDTO): Weather{
    val fact: FactDTO = weatherDTO.factDTO
    return (Weather(getDefaultCity(),fact.temperature, fact.feels_like, fact.icon))
}

fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>):List<Weather>{
    return entityList.map {
        Weather(City(it.city,0.0,0.0), it.temperature,it.feelsLike,it.icon)
    }
}
fun convertWeatherToEntity(weather: Weather):HistoryEntity{
    return HistoryEntity(0, weather.сity.name, weather.temperature, weather.feelsLike, weather.icon)
}