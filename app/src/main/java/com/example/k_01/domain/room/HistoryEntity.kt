package com.example.k_01.domain.room

import android.service.controls.templates.TemperatureControlTemplate
import androidx.room.Entity
import androidx.room.PrimaryKey

//создана таблица

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val city: String,
    val temperature: Int,
    val feelsLike: Int,
    val icon:String
)
