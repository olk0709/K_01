package com.example.k_01.viewmodel

import com.example.k_01.repository.Weather

sealed class DetailsState {
    //data class Loading(val progress: Int): AppState()
    object Loading: DetailsState()
    data class Success(val weather: Weather): DetailsState()
    data class Error(val error: Throwable): DetailsState()
}

