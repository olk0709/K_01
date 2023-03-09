package com.example.k_01.repository

import android.os.Handler
import android.os.Looper
import com.example.k_01.BuildConfig
import com.example.k_01.repository.dto.WeatherDTO
import com.example.k_01.utils.YANDEX_API_KEY
import com.example.k_01.utils.YANDEX_DOMAIN
import com.example.k_01.utils.YANDEX_PATH
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {

    fun loadWeather(lat:Double, lon:Double){

        // получил адрес
        //val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
        val urlText = "$YANDEX_DOMAIN${YANDEX_PATH}=$lat&lon=$lon"

        // создал коннект
        val uri = URL(urlText)
        Thread{
        val urlConnection: HttpsURLConnection = (uri.openConnection() as HttpsURLConnection).apply {
            connectTimeout = 1000                   // set по капотом
            //var r = urlConnection.readTimeout     // get по капотом
            readTimeout = 1000                      // set по капотом
            //addRequestProperty("X-Yandex-API-Key","ceae3d76-b634-4bfd-8ef5-25a327758ae9")
            addRequestProperty(YANDEX_API_KEY,BuildConfig.WEATHER_API_KEY)
        }

            try {
                Thread.sleep(500)
                val headers = urlConnection.headerFields
                val responseCode = urlConnection.responseCode
                val responseMessage = urlConnection.responseMessage

                val serverside = 500..599
                val clientside = 400..499
                val responseOk = 200..299

                when (responseCode) {
                    in serverside -> {
                        //что-то пошло не так на стороне сервера
                    }
                    in clientside -> {
                        //что-то пошло не так на стороне клиента
                    }
                    in responseOk -> {

                        //открываем поток
                        val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream)) //открыл коннект
                        //val result = (buffer.toString())
                        val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                        Handler(Looper.getMainLooper()).post {
                            onServerResponseListener.onResponse(weatherDTO)
                        }
                    }
                }




            }

            catch (e:JsonSyntaxException){

            }finally {
                urlConnection.disconnect()
            }
        }.start()

    }
}