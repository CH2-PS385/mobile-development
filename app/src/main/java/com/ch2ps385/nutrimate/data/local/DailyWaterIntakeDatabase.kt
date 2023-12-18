package com.ch2ps385.nutrimate.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DailyWaterIntake::class], version = 1, exportSchema = false)
abstract class DailyWaterIntakeDatabase: RoomDatabase() {

    abstract fun dailyWaterIntakeDao(): DailyWaterIntakeDao

    companion object {

        @Volatile
        private var instance: DailyWaterIntakeDatabase? = null

        fun getInstance(context: Context): DailyWaterIntakeDatabase {
            return synchronized(this){
                instance ?: Room.databaseBuilder(context, DailyWaterIntakeDatabase::class.java, "courses.db")
                    .build()
            }
        }

    }
}