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

    fun getWeather(){
      Thread{
          liveData.postValue(AppState.Loading)

          if((0..10).random()>5) {
              val answer = repository.getWeatherFromServer()
              liveData.postValue(AppState.Success(answer))
          }
          else
              liveData.postValue(AppState.Error(IllegalAccessException()))
      }.start()
    }

}