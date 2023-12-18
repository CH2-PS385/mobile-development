package com.ch2ps385.nutrimate.presentation.screen.user.preferences

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Constants
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.model.AddUserByEmail
import com.ch2ps385.nutrimate.data.remote.responses.AddUserByEmailResponse
import com.ch2ps385.nutrimate.data.remote.responses.Data
import com.ch2ps385.nutrimate.data.remote.responses.DataGetUser
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserPreferencesViewModel(private val repository : UserRepository):ViewModel() {


    private val _stateAddUser: MutableStateFlow<Resource<AddUserByEmailResponse>> =
        MutableStateFlow(Resource.Loading())

    val stateAddUser: StateFlow<Resource<AddUserByEmailResponse>> get() = _stateAddUser

    private val _stateGetUser: MutableStateFlow<Resource<DataGetUser>> =
        MutableStateFlow(Resource.Loading())

    val stateGetUser: StateFlow<Resource<DataGetUser>> get() = _stateGetUser

//    fun submitUserData(email: String, name: String) {
//        viewModelScope.launch {
//            _state.value = Resource.Loading()
//            try {
//                val addUserResponse = repository.addUserByEmail(AddUserByEmail(email, name))
//                _state.value = Resource.Success(addUserResponse)
//            } catch (e: Exception) {
//                _state.value = Resource.Error("An unknown error occurred")
//            }
//        }
//    }
    fun getUserByEmail(email : String){
        viewModelScope.launch {
            _stateGetUser.value = Resource.Loading()
            _stateGetUser.value = Resource.Success(repository.getDataUser(email))
        }
    }

    fun addUserByEmail(addUserByEmail: AddUserByEmail) {
        viewModelScope.launch {
            try {
                _stateAddUser.value = Resource.Loading()

                // Log before making the API call
                Log.d("UserPreferencesViewModel", "Adding user by email: $addUserByEmail")

                val result = repository.addUserByEmail(addUserByEmail)

                // Log the API call result
                Log.d("UserPreferencesViewModel", "API Response: $result")

                if (result is Resource.Success) {
                    val addUserResponse = result.data!!
                    _stateAddUser.value = Resource.Success(addUserResponse)
                } else if (result is Resource.Error) {
                    _stateAddUser.value = Resource.Error(result.message!!)
                    // Log the error message
                    Log.e("UserPreferencesViewModel", "Error adding user by email: ${result.message}")
                }
            } catch (e: Exception) {
                _stateAddUser.value = Resource.Error("An unknown error occurred")
                // Log the exception
                Log.e("UserPreferencesViewModel", "Exception adding user by email", e)
            }
        }
    }


    val height =  mutableStateOf("")
    fun setHeight(newHeight: String) {
        height.value = newHeight
    }

    val weight = mutableStateOf("")
    fun setWeight(newWeight : String){
        weight.value = newWeight
    }

    val age = mutableStateOf("")
    fun setAge(newAge : String){
        age.value = newAge
    }

    val gender = mutableStateOf(Constants.Gender.Male)
    fun setGender(newGender : Constants.Gender){
        gender.value = newGender
    }

    val foodPreferences = mutableStateOf(List(15) { false })


    fun setFoodPreference(index: Int, isChecked: Boolean) {
        foodPreferences.value = foodPreferences.value.toMutableList().also {
            it[index] = isChecked
        }
    }




    // Add other functions as needed for your use case

    // Example of a function to save preferences
    fun savePreferences() {
        // Implement your logic to save preferences here
        // You can access the state variables using the corresponding properties
    }
}

//enum class Gender {
//    Male,
//    Female
//}