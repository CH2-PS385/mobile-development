package com.ch2ps385.nutrimate.di

import android.content.Context
import com.ch2ps385.nutrimate.common.Constants.BASE_URL
import com.ch2ps385.nutrimate.data.remote.retrofit.ApiConfig
import com.ch2ps385.nutrimate.data.remote.retrofit.ApiService
import com.ch2ps385.nutrimate.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object Injection {
    fun provideUserRepository(context: Context): UserRepository{
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}