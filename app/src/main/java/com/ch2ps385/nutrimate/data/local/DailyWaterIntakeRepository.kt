package com.ch2ps385.nutrimate.data.local

import android.content.Context

class DailyWaterIntakeRepository(private val dao: DailyWaterIntakeDao) {

    suspend fun insertWaterIntake(newWaterIntake: DailyWaterIntake){
        return dao.insertWaterIntake(newWaterIntake)
    }

//    fun getDailyWaterIntake(): DailyWaterIntake{
//        return dao.getTotalIntake()
//    }


    companion object {
        @Volatile
        private var instance: DailyWaterIntakeRepository? = null
        private const val PAGE_SIZE = 10

        fun getInstance(context: Context): DailyWaterIntakeRepository? {
            return instance ?: synchronized(DailyWaterIntakeRepository::class.java) {
                if (instance == null) {
                    val database = DailyWaterIntakeDatabase.getInstance(context)
                    instance = DailyWaterIntakeRepository(database.dailyWaterIntakeDao())
                }
                return instance
            }
        }
    }
}