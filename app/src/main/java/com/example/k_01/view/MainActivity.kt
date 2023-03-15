package com.example.k_01.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.k_01.R
import com.example.k_01.repository.CitiesRepositoryRetrofit2Impl
import com.example.k_01.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // если 1-ый  запуск

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
        //CitiesRepositoryRetrofit2Impl().getCityList()
    }

}