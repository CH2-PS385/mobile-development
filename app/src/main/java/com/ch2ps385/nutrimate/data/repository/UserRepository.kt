package com.ch2ps385.nutrimate.data.repository

import android.content.ContentValues
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.model.AddAllergies
import com.ch2ps385.nutrimate.data.remote.model.AddMealPlanner
import com.ch2ps385.nutrimate.data.remote.model.AddUserByEmail
import com.ch2ps385.nutrimate.data.remote.model.AddUserPreferences
import com.ch2ps385.nutrimate.data.remote.model.AddWaterIntake
import com.ch2ps385.nutrimate.data.remote.model.GetMealPlanner
import com.ch2ps385.nutrimate.data.remote.model.GetWaterIntake
import com.ch2ps385.nutrimate.data.remote.responses.AddAllergiesResponse
import com.ch2ps385.nutrimate.data.remote.responses.AddMealPlannerResponse
import com.ch2ps385.nutrimate.data.remote.responses.AddUserByEmailResponse
import com.ch2ps385.nutrimate.data.remote.responses.AddUserPreferencesResponse
import com.ch2ps385.nutrimate.data.remote.responses.AddWaterIntakeResponse
import com.ch2ps385.nutrimate.data.remote.responses.Data
import com.ch2ps385.nutrimate.data.remote.responses.DataGetUser
import com.ch2ps385.nutrimate.data.remote.responses.GetMealPlannerResponse
import com.ch2ps385.nutrimate.data.remote.responses.GetWaterIntakeResponse
import com.ch2ps385.nutrimate.data.remote.responses.MenuResponse
import com.ch2ps385.nutrimate.data.remote.responses.TodaysMenuResponse
import com.ch2ps385.nutrimate.data.remote.retrofit.ApiService
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getTodayMenu(): Resource<TodaysMenuResponse>{
        val currentDate = LocalDate.now()
        val today = currentDate.format(DateTimeFormatter.ofPattern("dd"))

        val response = try {
            api.getTodayMenu(today.toInt())
        } catch (e : Exception){
            return Resource.Error("[ Repository ] Get Today Menu:${e.message}")
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


    suspend fun addUserByEmail(addUserByEmail: AddUserByEmail): Resource<AddUserByEmailResponse> {
        try {
            val response = api.addUserByEmail(addUserByEmail)
            Log.d(ContentValues.TAG, "AddUserByEmailResponse: $response")
            return Resource.Success(response)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
    }

    suspend fun addUserPreferences(addUserPreferences: AddUserPreferences): Resource<AddUserPreferencesResponse>{
        try {
            val response = api.addUserPreferences(addUserPreferences)
            Log.d(ContentValues.TAG, "API Response Add User Preferences: $response")
            return Resource.Success(response)
        } catch (e: Exception) {
            return Resource.Error("[ Repository ] AddUserPreferencse:${e.message}")
        }
    }

    suspend fun addAllergies(addAllergies: AddAllergies) : Resource<AddAllergiesResponse>{
        try {
            val response = api.addUserAllergies(addAllergies)
            Log.d(ContentValues.TAG, "API Response Add User Allergies: $response")
            return Resource.Success(response)
        } catch (e: Exception) {
            return Resource.Error("[ Repository ] AddUserAllergies:${e.message}")
        }
    }

    // Di dalam UserRepository
    suspend fun addMealPlanner(addMealPlanner: AddMealPlanner) : Resource<AddMealPlannerResponse>{
        try {
            Log.d("UserRepository", "Before calling api.addMealPlanner")
            val response = api.addMealPlanner(addMealPlanner)
            Log.d("UserRepository", "After calling api.addMealPlanner")
            Log.d(ContentValues.TAG, "API Response: $response")
            return Resource.Success(response)
        }catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error in addMealPlanner: ${e.message}")
            return Resource.Error("[ Repository ] AddMealPlanner:${e.message}")
        }
    }

    suspend fun addWaterIntake(addWaterIntake: AddWaterIntake) : Resource<AddWaterIntakeResponse>{
        try {
            Log.d("UserRepository", "Before calling api.addMealPlanner")
            val response = api.addWaterIntake(addWaterIntake)
            Log.d("UserRepository", "After calling api.addMealPlanner")
            Log.d(ContentValues.TAG, "API Response: $response")
            return Resource.Success(response)
        }catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error in addMealPlanner: ${e.message}")
            return Resource.Error("[ Repository ] AddMealPlanner:${e.message}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getWaterIntake(getWaterIntake: GetWaterIntake): Resource<GetWaterIntakeResponse>{
        val currentDate = LocalDate.now()
        val dd: Int = currentDate.dayOfMonth
        val mm: Int = currentDate.monthValue
        val yyyy: Int = currentDate.year
        try {
            Log.d("UserRepository", "WaterIntakeData: $dd, $mm, $yyyy, ${getWaterIntake.email}")
            val response = api.getWaterIntake(
                email = getWaterIntake.email,
                dd = dd,
                mm = mm,
                yy = yyyy
            )
            Log.d("UserRepository", "After calling api.getWaterIntake")
            Log.e(ContentValues.TAG, "API Response GetWaterIntakeResponse: $response")
            return Resource.Success(response)
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error in getWaterIntake: ${e.message}")
            return Resource.Error("[ Repository ] getWaterIntake:${e.message}")
        }
    }

    suspend fun getDataMealPlanner(getMealPlanner: GetMealPlanner) : Resource<GetMealPlannerResponse>{
        try{
            Log.d("UserRepository", "Before calling api.getMealPlanner")
//            Log.d("UserRepository", "getMealPlanner : $getMealPlanner")
            val response = api.getMealPlanner(
                email = getMealPlanner.email,
                dd = getMealPlanner.dd,
                mm = getMealPlanner.mm,
                yy = getMealPlanner.yy
            )
            Log.d("UserRepository", "After calling api.getMealPlanner")
            Log.e(ContentValues.TAG, "API Response GetMealPlannerResponse: $response")
            return Resource.Success(response)
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error in getMealPlanner: ${e.message}")
            return Resource.Error("[ Repository ] getMealPlanner:${e.message}")
        }
    }

//    sus

    suspend fun getDataUser(email : String): DataGetUser{
        try {
            val response = api.getUserByEmail(email)
            Log.d(ContentValues.TAG, "API Response: $response")
            return response.data
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error fetching user by ID: ${e.message}")
            throw e
        }
    }
    suspend fun checkUserPreferencesIsFilled(email: String): Boolean {
        try {
            Log.d("AddUserPreferences", "Fetching user data for email: $email")
            val response = api.getUserByEmail(email)
            if (response.success) {
                val userData = response.data
                if (userData != null) {
                    Log.d("AddUserPreferences", "User data fetched successfully: $userData")
                    val isFilled = isUserPreferencesFilled(userData)
                    Log.d("AddUserPreferences", "User preferences filled: $isFilled")
                    return isFilled
                } else {
                    Log.e("AddUserPreferences", "User data is null")
                    return false
                }
            } else {
                Log.e("AddUserPreferences", "message: ${response.message}")
                return false
            }
        } catch (e: Exception) {
            Log.e("AddUserPreferences", "Exception during network call: ${e.message}")
            return false
        }
    }
    fun isUserPreferencesFilled(userData: DataGetUser): Boolean {
        return !userData.gender.isNullOrBlank() &&
                userData.weight != null &&
                userData.age != null &&
                userData.height != null
//                !userData.allergies.isNullOrEmpty()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}