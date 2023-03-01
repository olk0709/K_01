package com.example.k_01.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.k_01.R
import com.example.k_01.databinding.ActivityMainWebviewBinding
import com.example.k_01.view.weatherlist.WeatherListFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivityWebView : AppCompatActivity() {
    lateinit var binding: ActivityMainWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* если 1-ый  запуск
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, WeatherListFragment.newInstance()).commit()
        } */
        binding.ok.setOnClickListener {
            // получил адрес
            val urlText = binding.etTextUrl.text.toString()

            // создал коннект
            val uri = URL(urlText)
            val urlConnection:HttpsURLConnection = (uri.openConnection() as HttpsURLConnection).apply {
                connectTimeout = 1000                   // set по капотом
                //var r = urlConnection.readTimeout     // get по капотом
                readTimeout = 1000                      // set по капотом
            }

            Thread{
                val headers = urlConnection.headerFields
                //открываем поток
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream)) //открыл коннект
                val result = getLinesAsOneBigString(buffer)
                /*runOnUiThread{  // 1ый способ
                    binding.webview.loadData(result, "text/html; utf-8","utf-8") //Загрузка полученных данных
                }*/
                Handler(Looper.getMainLooper()).post{  // 2ой способ
                    binding.webview.loadDataWithBaseURL(null,result,"text/html; utf-8","utf-8",null) //Загрузка полученных данных
                }

            }.start()


        }
    }

    fun getLinesAsOneBigString(bufferedReader:BufferedReader):String{
        return bufferedReader.lines().collect(Collectors.joining("\n"))
    }
}