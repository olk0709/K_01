package com.example.k_01.repository

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {

    fun loadWeather(lat:Double, lon:Double){

        Thread{
        // получил адрес
        val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"

        // создал коннект
        val uri = URL(urlText)
        val urlConnection: HttpsURLConnection = (uri.openConnection() as HttpsURLConnection).apply {
            connectTimeout = 1000                   // set по капотом
            //var r = urlConnection.readTimeout     // get по капотом
            readTimeout = 1000                      // set по капотом
        }


            val headers = urlConnection.headerFields
            //открываем поток
            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream)) //открыл коннект
            //val result = (buffer.toString())
            val weatherDTO:WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
            onServerResponseListener.onResponse(weatherDTO)

        }.start()

    }
}