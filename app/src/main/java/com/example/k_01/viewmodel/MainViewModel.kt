package com.example.k_01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_01.repository.RepositoryImpl

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl()
    ):
    ViewModel() {

    fun getData(): LiveData<AppState>{
        //Наша livedata
        return liveData
    }

    fun getWeatherRussia() = getWeather(true)
    fun getWeatherWorld() = getWeather(false)

    private fun getWeather(isRussian:Boolean){
      Thread{
          liveData.postValue(AppState.Loading)

          if((0..10).random()>0) {

              //val answer = repository.getWeatherFromServer()
              val answer = if(!isRussian) repository.getWorldWeatherFromLocalStorage() else repository.getRussionWeatherFromLocalStorage()
              liveData.postValue(AppState.Success(answer))
          }
          else
              liveData.postValue(AppState.Error(IllegalAccessException()))
      }.start()
    }

}