package com.ch2ps385.nutrimate.data.remote.retrofit

import com.ch2ps385.nutrimate.data.remote.model.AddUserByEmail
import com.ch2ps385.nutrimate.data.remote.model.RecommendationRequest
import com.ch2ps385.nutrimate.data.remote.responses.AddUserByEmailResponse
import com.ch2ps385.nutrimate.data.remote.responses.Data
import com.ch2ps385.nutrimate.data.remote.responses.GetUserlResponse
import com.ch2ps385.nutrimate.data.remote.responses.MenuDetailResponse
import com.ch2ps385.nutrimate.data.remote.responses.MenuResponse
import com.ch2ps385.nutrimate.data.remote.responses.RecommendationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService{

    @GET("getallfood")
    suspend fun getAllMenu(
    ) : MenuResponse

    @GET("getfoodbyid")
    suspend fun getMenuById(@Query("id") id: String): MenuDetailResponse

    @POST("recommend")
    suspend fun getRecommendation(@Body recommendationRequest: RecommendationRequest): RecommendationResponse

    @POST("adduser")
    suspend fun addUserByEmail(@Body addUserByEmail:AddUserByEmail):AddUserByEmailResponse

    @GET("v1/getuser")
    suspend fun getUserByEmail(@Query("email") email: String): GetUserlResponse

}