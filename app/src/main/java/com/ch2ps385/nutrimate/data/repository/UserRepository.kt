package com.ch2ps385.nutrimate.data.repository

import android.content.ContentValues
import android.util.Log
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.responses.Data
import com.ch2ps385.nutrimate.data.remote.responses.MenuDetailResponse
import com.ch2ps385.nutrimate.data.remote.responses.MenuResponse
import com.ch2ps385.nutrimate.data.remote.retrofit.ApiService

class UserRepository private constructor(private val api : ApiService) {

    suspend fun getAllMenu(): Resource<MenuResponse> {
        val response = try{
            api.getAllMenu()
        } catch (e : Exception){
            return Resource.Error("An unknown error occured")
        }
        Log.d(ContentValues.TAG, "API Response: $response")
        return Resource.Success(response)
    }

    suspend fun getMenuById(foodId : String): Data{
        try {
            val response = api.getMenuById(foodId)
            Log.d(ContentValues.TAG, "API Response: $response")
            return response.data
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error fetching menu by ID: ${e.message}")
            throw e
        }
    }

//    suspend fun searchMenu(query: String): Resource<MenuResponse> {
//        val response = try {
//            api.searchMenu(query)
//        } catch (e: Exception) {
//            return Resource.Error("An unknown error occurred during search")
//        }
//        return Resource.Success(response)
//    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}