package com.ch2ps385.nutrimate.presentation.screen.profile.editpreferences

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Constants
import com.ch2ps385.nutrimate.common.Constants.foodItems
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

class EditUserPreferencesViewModel(private val repository: UserRepository): ViewModel() {

    private val _stateUserPreferences: MutableStateFlow<Resource<AddUserPreferencesResponse>> =
        MutableStateFlow(Resource.Initial())

    val stateUserPreferences: StateFlow<Resource<AddUserPreferencesResponse>> get() = _stateUserPreferences

    private val _stateAllergies : MutableStateFlow<Resource<AddAllergiesResponse>> = MutableStateFlow(
        Resource.Loading())

    val stateAllergies : StateFlow<Resource<AddAllergiesResponse>> get() = _stateAllergies

    private val _isDialogLoadingShown = mutableStateOf(false)

    val isDialogLoadingShown : State<Boolean> get() = _isDialogLoadingShown

    fun onDismissLoadingDialog(){
        _isDialogLoadingShown.value = false
    }
    // Mengganti nilai properti isDialogShown
    fun onSavePreferencesClick() {
        _isDialogLoadingShown.value = true
        _isDialogShown.value = true
    }

    private val _isDialogShown = mutableStateOf(false)
    val isDialogShown: State<Boolean> get() = _isDialogShown

    fun onDismissDialog() {
        _isDialogShown.value = false
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

    private val _stateGetDataPreference: MutableStateFlow<Resource<DataGetUser>> = MutableStateFlow(
        Resource.Loading())
    val stateGetDataPreference : StateFlow<Resource<DataGetUser>> get() = _stateGetDataPreference

    fun getDataUserPref(email : String){
        viewModelScope.launch {
            _stateGetDataPreference.value = Resource.Loading()
            _stateGetDataPreference.value = Resource.Success(repository.getDataUser(email))
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
//    fun setGender(newGender : Constants.Gender){
//        gender.value = newGender
//    }

    fun setGender(newGender: String?) {
        gender.value = when (newGender) {
            "m" -> Constants.Gender.Male
            "f" -> Constants.Gender.Female
            else -> Constants.Gender.Male // Provide a default value or handle the case where it's neither "m" nor "f"
        }
    }

    val foodPreferences = mutableStateOf(List(15) { false })


    fun setFoodPreference(index: Int, isChecked: Boolean, email:String) {
        foodPreferences.value = foodPreferences.value.toMutableList().also {
            it[index] = isChecked
        }
        viewModelScope.launch {
            saveFoodPreferences(email)
        }
    }

    suspend fun saveFoodPreferences(email: String) {
        try {
            val allergies = foodItems.filterIndexed { index, _ ->
                foodPreferences.value[index]
            }
            val addAllergies = repository.addAllergies(AddAllergies(email, allergies))
            _stateAllergies.value = Resource.Success(addAllergies.data!!)
        } catch (e: Exception) {
            _stateAllergies.value = Resource.Error("[ View Model ] saveFoodPreferences: ${e.message}")
        }
    }

    fun setFoodPreferenceGet(newAllergies: List<String>?) {
        newAllergies?.let { allergies ->
            foodPreferences.value = foodPreferences.value.toMutableList().also {
                for (allergy in allergies) {
                    val index = Constants.foodItems.indexOf(allergy)
                    if (index != -1) {
                        it[index] = true
                    }
                }
            }
        }
    }




}