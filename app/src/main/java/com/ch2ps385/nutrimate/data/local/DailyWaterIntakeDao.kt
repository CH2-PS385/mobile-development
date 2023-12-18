package com.ch2ps385.nutrimate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DailyWaterIntakeDao {

    @Query("SELECT SUM(waterIntake) FROM daily_water_intake WHERE emailUser = :email AND date = :date")
    suspend fun getTotalIntake(email: String, date: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaterIntake(waterIntake: DailyWaterIntake)
}