package com.example.k_01.repository

import com.example.k_01.BuildConfig
import com.example.k_01.repository.dto.WeatherDTO
import com.example.k_01.utils.YANDEX_API_KEY
import com.example.k_01.utils.YANDEX_DOMAIN
import com.example.k_01.utils.YANDEX_PATH
import com.example.k_01.utils.convertDtoModel
import com.example.k_01.viewmodel.DetailsViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class DetailsRepositoryOkHttpImpl:DetailsRepository {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {

        val client = OkHttpClient()  //создал клиент
        val builder = okhttp3.Request.Builder() //создал запрос
        //добавляем заголовок
        builder.addHeader(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)  //добавлен заголовок в ключ(вся настройка)
        //добавляем адрес
        //builder.url("$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=$lat&lon=$lon")
        builder.url("$YANDEX_DOMAIN$YANDEX_PATH=${city.lat}&lon=${city.lon}")
        val request = builder.build()  //запрос
        val call = client.newCall(request)
        Thread{
            //получаем в потоке response
            val response = call.execute()
            if(response.isSuccessful){
                val serverResponse = response.body()!!.string()
                val weatherDTO: WeatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                //конвертер для клиент-сервера
                val weather = convertDtoModel(weatherDTO)
                weather.сity = city
                callback.onResponse(weather)
            }else{

            }


        }.start()
    }
}