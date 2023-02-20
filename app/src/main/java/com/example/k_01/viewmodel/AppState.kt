package com.example.k_01.viewmodel

sealed class AppState {
    //data class Loading(val progress: Int): AppState()
    object Loading: AppState()
    data class Success(val weatherData: Any): AppState(){
        fun test(){}
    }
    data class Error(val error: Throwable): AppState()
}

