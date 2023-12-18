package com.ch2ps385.nutrimate.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_water_intake")
data class DailyWaterIntake(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "emailUser")
    val emailUser: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "waterIntake")
    val waterIntake: Int
)
