package com.example.k_01.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_01.repository.*

class DetailsViewModel (
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private val repository: DetailsRepository = DetailsRepositoryRetrofit2Impl()
    ):ViewModel(){

        fun getLiveData()= liveData


        fun getWeather(city: City){
            liveData.postValue(DetailsState.Loading)
            repository.getWeatherDetails(city, object :Callback{
                //получение ответа
                override fun onResponse(weather: Weather) {
                    // загрузка нового значения
                    liveData.postValue(DetailsState.Success(weather))
                }

                override fun onFail() {
                    //liveData.postValue(DetailsState.Error(weather))
                }

            })
        }
    //получение ответа
    fun interface Callback{
        fun onResponse(weather: Weather)
        fun onFail()
    }

}