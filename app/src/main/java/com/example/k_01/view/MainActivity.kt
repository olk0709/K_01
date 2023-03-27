package com.example.k_01.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.k_01.MyApp
import com.example.k_01.R
import com.example.k_01.databinding.FragmentWorkWithContentProviderBinding
import com.example.k_01.lesson9.WorkWithContentProviderFragment
import com.example.k_01.utils.KEY_SP_FILE_NAME_1
import com.example.k_01.utils.KEY_SP_FILE_NAME_1_KEY_IS_RUSSIAN
import com.example.k_01.view.weatherlist.HistoryWeatherListFragment
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

        MyApp.getHistoryDao().getAll()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_threads->{
               navigate(HistoryWeatherListFragment.newInstance())
            }

            R.id.action_work_with_content_provider->{
                navigate(WorkWithContentProviderFragment.newInstance())
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun navigate(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack("")
            .commit()
    }



}