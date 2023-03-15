package com.example.k_01.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.k_01.R
import com.example.k_01.repository.CitiesRepositoryRetrofit2Impl
import com.example.k_01.utils.KEY_SP_FILE_NAME_1
import com.example.k_01.utils.KEY_SP_FILE_NAME_1_KEY_IS_RUSSIAN
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

        val sp = getSharedPreferences(KEY_SP_FILE_NAME_1, Context.MODE_PRIVATE)

        val editor = sp.edit()
        editor.putBoolean(KEY_SP_FILE_NAME_1_KEY_IS_RUSSIAN, true)
        editor.apply()

        val defaultValueIsRussian = true
        sp.getBoolean(KEY_SP_FILE_NAME_1_KEY_IS_RUSSIAN, defaultValueIsRussian)
    }

}