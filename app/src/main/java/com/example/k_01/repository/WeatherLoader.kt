package com.example.k_01.repository

import android.os.Handler
import android.os.Looper
import android.preference.PreferenceActivity.Header
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {

    fun loadWeather(lat:Double, lon:Double){

        // получил адрес
        val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"

        // создал коннект
        val uri = URL(urlText)
        val urlConnection: HttpsURLConnection = (uri.openConnection() as HttpsURLConnection).apply {
            connectTimeout = 1000                   // set по капотом
            //var r = urlConnection.readTimeout     // get по капотом
            readTimeout = 1000                      // set по капотом
            addRequestProperty("X-Yandex-API-Key","ceae3d76-b634-4bfd-8ef5-25a327758ae9")
        }

        Thread{
            try {



                val headers = urlConnection.headerFields
                val responseCode = urlConnection.responseCode
                //открываем поток
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream)) //открыл коннект
                //val result = (buffer.toString())
                val weatherDTO:WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post { onServerResponseListener.onResponse(weatherDTO) }

            }catch (e:JsonSyntaxException){
                // что-то пошло не так. Можно отправить на сервер ошибку)
            }
            finally {
                urlConnection.disconnect()
            }




        }.start()

    }
}