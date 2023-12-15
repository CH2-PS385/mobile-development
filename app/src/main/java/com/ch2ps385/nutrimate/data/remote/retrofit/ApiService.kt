package com.ch2ps385.nutrimate.data.remote.retrofit

import com.ch2ps385.nutrimate.data.remote.responses.Data
import com.ch2ps385.nutrimate.data.remote.responses.MenuDetailResponse
import com.ch2ps385.nutrimate.data.remote.responses.MenuResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("getallfood")
    suspend fun getAllMenu(
    ) : MenuResponse

    @GET("getfoodbyid")
    suspend fun getMenuById(@Query("id") id: String): MenuDetailResponse


}