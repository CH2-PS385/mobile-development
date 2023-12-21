package com.ch2ps385.nutrimate.di

import android.content.Context
import com.ch2ps385.nutrimate.data.remote.retrofit.ApiConfig
import com.ch2ps385.nutrimate.data.repository.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository{
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}