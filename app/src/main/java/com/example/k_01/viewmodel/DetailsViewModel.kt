package com.example.k_01.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_01.repository.*

class DetailsViewModel (
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),

    private val repositoryAdd: DetailsRepositoryAdd = DetailsRepositoryRoomImpl()

    ):ViewModel(){

    private var repositoryOne: DetailsRepositoryOne = DetailsRepositoryOneRetrofit2Impl()
        fun getLiveData()= liveData


        fun getWeather(city: City){
            liveData.postValue(DetailsState.Loading)
            repositoryOne = if(isInternet()){
                DetailsRepositoryOneRetrofit2Impl()
            }else {
                DetailsRepositoryRoomImpl()
            }

            repositoryOne.getWeatherDetails(city, object :Callback{

                //получение ответа
                override fun onResponse(weather: Weather) {
                    // загрузка нового значения
                    liveData.postValue(DetailsState.Success(weather))
                    //добавление данных в БД
                    repositoryAdd.addWeather(weather)
                }

                override fun onFail() {
                    //liveData.postValue(DetailsState.Error(weather))
                }

            })
        }

    private fun isInternet(): Boolean {
        return false
    }
    //получение ответа

    interface Callback{
        fun onResponse(weather: Weather)
        fun onFail()
    }

}