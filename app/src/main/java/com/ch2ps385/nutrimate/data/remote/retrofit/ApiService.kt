package com.ch2ps385.nutrimate.data.remote.retrofit

import com.ch2ps385.nutrimate.data.remote.model.AddAllergies
import com.ch2ps385.nutrimate.data.remote.model.AddMealPlanner
import com.ch2ps385.nutrimate.data.remote.model.AddUserByEmail
import com.ch2ps385.nutrimate.data.remote.model.AddUserPreferences
import com.ch2ps385.nutrimate.data.remote.model.AddWaterIntake
import com.ch2ps385.nutrimate.data.remote.responses.AddAllergiesResponse
import com.ch2ps385.nutrimate.data.remote.responses.AddMealPlannerResponse
import com.ch2ps385.nutrimate.data.remote.responses.AddUserByEmailResponse
import com.ch2ps385.nutrimate.data.remote.responses.AddUserPreferencesResponse
import com.ch2ps385.nutrimate.data.remote.responses.AddWaterIntakeResponse
import com.ch2ps385.nutrimate.data.remote.responses.GetMealPlannerResponse
import com.ch2ps385.nutrimate.data.remote.responses.GetUserlResponse
import com.ch2ps385.nutrimate.data.remote.responses.GetWaterIntakeResponse
import com.ch2ps385.nutrimate.data.remote.responses.MenuDetailResponse
import com.ch2ps385.nutrimate.data.remote.responses.MenuResponse
import com.ch2ps385.nutrimate.data.remote.responses.TodaysMenuResponse
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

    @GET("random5")
    suspend fun getTodayMenu(@Query("seed") seed: Int): TodaysMenuResponse


    @POST("adduser")
    suspend fun addUserByEmail(@Body addUserByEmail:AddUserByEmail):AddUserByEmailResponse

    @GET("getuser")
    suspend fun getUserByEmail(@Query("email") email: String): GetUserlResponse

    @POST("updateuserinfo")
    suspend fun addUserPreferences(@Body addUserPreferences: AddUserPreferences): AddUserPreferencesResponse

    @POST("updateuserallergies")
    suspend fun addUserAllergies(@Body addAllergies: AddAllergies) : AddAllergiesResponse

    @POST("setplanner")
    suspend fun addMealPlanner(@Body addMealPlanner: AddMealPlanner) : AddMealPlannerResponse


    @GET("getplanner")
    suspend fun getMealPlanner(
        @Query("email") email: String,
        @Query("dd") dd: Int,
        @Query("mm") mm: Int,
        @Query("yy") yy: Int
    ): GetMealPlannerResponse

    @POST("waterintake")
    suspend fun addWaterIntake(@Body addWaterIntake: AddWaterIntake) : AddWaterIntakeResponse

    @GET("waterintake")
    suspend fun getWaterIntake(
        @Query("email") email: String,
        @Query("dd") dd: Int,
        @Query("mm") mm: Int,
        @Query("yy") yy: Int
    ) : GetWaterIntakeResponse

}