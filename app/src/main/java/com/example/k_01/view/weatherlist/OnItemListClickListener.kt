package com.example.k_01.view.weatherlist

import com.example.k_01.repository.Weather

interface OnItemListClickListener {
    fun onItemClick(weather:Weather)

}