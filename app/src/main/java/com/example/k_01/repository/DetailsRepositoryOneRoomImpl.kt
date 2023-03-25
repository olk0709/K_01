package com.example.k_01.repository

import com.example.k_01.MyApp
import com.example.k_01.utils.convertHistoryEntityToWeather
import com.example.k_01.utils.convertWeatherToEntity
import com.example.k_01.viewmodel.DetailsViewModel
import com.example.k_01.viewmodel.HistoryViewModel

class DetailsRepositoryRoomImpl:DetailsRepositoryOne, DetailsRepositoryAll, DetailsRepositoryAdd {
    override fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll) {
        callback.onResponse(convertHistoryEntityToWeather(MyApp.getHistoryDao().getAll()))
    }

    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val list = convertHistoryEntityToWeather(MyApp.getHistoryDao().getHistoryForCity(city.name))
        if(list.isEmpty()){
            callback.onFail() // нечего отображать
        }else{
            callback.onResponse(list.last())
        }

    }

    override fun addWeather(weather: Weather) {
        MyApp.getHistoryDao().insert(convertWeatherToEntity(weather))
    }

}