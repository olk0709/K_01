package com.example.k_01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_01.repository.DetailsRepositoryRoomImpl
import com.example.k_01.repository.RepositoryImpl
import com.example.k_01.repository.Weather

class HistoryViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: DetailsRepositoryRoomImpl = DetailsRepositoryRoomImpl()
    ):
    ViewModel() {

    fun getData(): LiveData<AppState>{
        //Наша livedata
        return liveData
    }

    fun getAll(){
        repository.getAllWeatherDetails(object :CallbackForAll{
            override fun onResponse(listWeather: List<Weather>) {
                liveData.postValue(AppState.Success(listWeather))
            }

            override fun onFail() {
                TODO("Not yet implemented")
            }


        })
    }

    interface CallbackForAll{
        fun onResponse(listWeather: List<Weather>)
        fun onFail()
    }

}