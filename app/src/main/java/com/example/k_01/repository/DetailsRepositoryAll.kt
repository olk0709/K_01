package com.example.k_01.repository

import com.example.k_01.viewmodel.DetailsViewModel
import com.example.k_01.viewmodel.HistoryViewModel


interface DetailsRepositoryAll {
    fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll)
}