package com.example.k_01.domain.room

import androidx.room.*

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
