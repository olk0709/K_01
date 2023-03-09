package com.example.k_01.view.details

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.k_01.BuildConfig
import com.example.k_01.repository.dto.WeatherDTO
import com.example.k_01.utils.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailsService(val name: String = ""): IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@", "work MainService")
        intent?.let {
            val lat = it.getStringArrayExtra(KEY_BUNDLE_LAT)
            val lon = it.getStringArrayExtra(KEY_BUNDLE_LON)
            Log.d("@@@", "work DetailsService $lat $lon")


            // получил адрес
            val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"

            // создал коннект
            val uri = URL(urlText)
            val urlConnection: HttpsURLConnection = (uri.openConnection() as HttpsURLConnection).apply {
                connectTimeout = 1000                   // set по капотом
                //var r = urlConnection.readTimeout     // get по капотом
                readTimeout = 1000                      // set по капотом
                //addRequestProperty("X-Yandex-API-Key","ceae3d76-b634-4bfd-8ef5-25a327758ae9")
                addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY)
            }
                //try {
                val headers = urlConnection.headerFields
                val responseCode = urlConnection.responseCode
                val responseMessage = urlConnection.responseMessage

                if(responseCode>=500){
                    //что-то пошло не так на стороне сервера
                }else if(responseCode>=400){
                    //что-то пошло не так на стороне клиента
                }else if(responseCode in 200..299) {
                }
            //открываем поток
            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream)) //открыл коннект
            val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)

            val message = Intent(KEY_WAVE)
            message.putExtra(
                KEY_BUNDLE_SERVICE_BROADCAST_WEATHER,
                weatherDTO
            )
            sendBroadcast(message)
        }

    }
}