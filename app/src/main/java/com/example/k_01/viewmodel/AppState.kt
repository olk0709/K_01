package com.example.k_01.viewmodel

import com.example.k_01.repository.Weather

sealed class AppState {
    //data class Loading(val progress: Int): AppState()
    object Loading: AppState()
    data class Success(val weatherData: List<Weather>): AppState(){
        fun test(){}
    }
    data class Error(val error: Throwable): AppState()
}

