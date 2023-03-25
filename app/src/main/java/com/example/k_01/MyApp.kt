package com.example.k_01

import android.app.Application
import androidx.room.Room
import com.example.k_01.domain.room.HistoryDao
import com.example.k_01.domain.room.MyDB

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object{
        private var db:MyDB?=null
        private var appContext:MyApp?=null
        fun getHistoryDao(): HistoryDao{
            if (db == null){
                if(appContext !=null){
                    db = Room.databaseBuilder(appContext!!, MyDB::class.java, "text")
                        .allowMainThreadQueries()
                        .build()
                }else{
                    throw IllegalStateException("!пустой appContext")
                }
            }
            return db!!.historyDao()
        }
    }

}