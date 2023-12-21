package com.ch2ps385.nutrimate.presentation.screen.user.preferences

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Constants
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.model.AddAllergies
import com.ch2ps385.nutrimate.data.remote.model.AddUserPreferences
import com.ch2ps385.nutrimate.data.remote.responses.AddAllergiesResponse
import com.ch2ps385.nutrimate.data.remote.responses.AddUserPreferencesResponse
import com.ch2ps385.nutrimate.data.remote.responses.DataGetUser
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserPreferencesViewModel(private val repository : UserRepository):ViewModel() {


    private val _stateUserPreferences: MutableStateFlow<Resource<AddUserPreferencesResponse>> =
        MutableStateFlow(Resource.Initial())

    val stateUserPreferences: StateFlow<Resource<AddUserPreferencesResponse>> get() = _stateUserPreferences

    private val _stateAllergies : MutableStateFlow<Resource<AddAllergiesResponse>> = MutableStateFlow(Resource.Loading())

    val stateAllergies : StateFlow<Resource<AddAllergiesResponse>> get() = _stateAllergies

    private val _stateGetUser: MutableStateFlow<Resource<DataGetUser>> =
        MutableStateFlow(Resource.Loading())

    val stateGetUser: StateFlow<Resource<DataGetUser>> get() = _stateGetUser

    private val _isDialogLoadingShown = mutableStateOf(false)

    val isDialogLoadingShown : State<Boolean> get() = _isDialogLoadingShown

    fun onDismissLoadingDialog(){
        _isDialogLoadingShown.value = false
    }
    fun onSavePreferencesClick() {
        _isDialogLoadingShown.value = true
    }


    fun addUserPreferences(addUserPreferences: AddUserPreferences){
        viewModelScope.launch { 
            _stateUserPreferences.value = Resource.Loading()
            try {
                val addUserPreferences = repository.addUserPreferences(addUserPreferences)
                _stateUserPreferences.value = Resource.Success(addUserPreferences.data!!)
            } catch ( e : Exception){
                _stateUserPreferences.value = Resource.Error("[ View Model ] addUserPreferences: ${e.message}")
            }
        }
    }

    fun addAllergies(addAllergies: AddAllergies){
        viewModelScope.launch {
            _stateAllergies.value = Resource.Loading()
            try {
                val addAllergies = repository.addAllergies(addAllergies)
                _stateAllergies.value = Resource.Success(addAllergies.data!!)
            } catch ( e : Exception){
                _stateAllergies.value = Resource.Error("[ View Model ] addAllergies: ${e.message}")
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


    fun areFieldsFilled(): Boolean {
        return height.value.isNotEmpty() && weight.value.isNotEmpty() && age.value.isNotEmpty()
    }

    fun validateFields(): Boolean {
        return if (!areFieldsFilled()) {
            // Handle the case where fields are not filled, for example, show a message to the user
            false
        } else {
            // Add additional validation logic if needed
            true
        }
    }



}
