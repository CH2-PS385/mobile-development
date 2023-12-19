package com.ch2ps385.nutrimate.presentation.screen.auth.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.SignInResult
import com.ch2ps385.nutrimate.data.remote.model.AddUserByEmail
import com.ch2ps385.nutrimate.data.remote.responses.AddUserByEmailResponse
import com.ch2ps385.nutrimate.data.remote.responses.User
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(private val repository : UserRepository): ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

//
    private val _stateAddUser: MutableStateFlow<Resource<AddUserByEmailResponse>> =
        MutableStateFlow(Resource.Loading())

    val stateAddUser: StateFlow<Resource<AddUserByEmailResponse>> get() = _stateAddUser

    private val _stateIsUserPreferencesFilled: MutableStateFlow<Resource<Boolean>> = MutableStateFlow(Resource.Loading())
    val stateIsUserPreferencesFilled : StateFlow<Resource<Boolean>> get() = _stateIsUserPreferencesFilled

    fun onSignInResult(result: SignInResult){
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState(){
        _state.update { SignInState() }
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

    fun checkIsUserPreferencesFilled(email: String) {
        viewModelScope.launch {
            try {
                val isFilled = repository.checkUserPreferencesIsFilled(email)
                Log.d("UserPreferencesViewModel", "[ isFilled: $isFilled ]")
                _stateIsUserPreferencesFilled.value = Resource.Success(isFilled)
                Log.d("UserPreferencesViewModel", "[ _isFilled: ${_stateIsUserPreferencesFilled.value} ]")
            } catch (e: Exception) {
                _stateIsUserPreferencesFilled.value = Resource.Error("An unknown error occurred")
                // Handle errors, e.g., show an error message
                Log.e("UserPreferencesViewModel", "Exception check user preferences", e)
            }
        }
    }
}