package com.ch2ps385.nutrimate.presentation.screen.user.reminder

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.model.AddWaterIntake
import com.ch2ps385.nutrimate.data.remote.model.GetWaterIntake
import com.ch2ps385.nutrimate.data.remote.responses.AddWaterIntakeResponse
import com.ch2ps385.nutrimate.data.remote.responses.GetWaterIntakeResponse
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReminderViewModel(private val repository: UserRepository): ViewModel() {

    private val _stateAddWaterIntake: MutableStateFlow<Resource<AddWaterIntakeResponse>> =
        MutableStateFlow(Resource.Initial())
    val stateAddWaterIntake: StateFlow<Resource<AddWaterIntakeResponse>> get() = _stateAddWaterIntake

    private val _stateGetWaterIntake: MutableStateFlow<Resource<GetWaterIntakeResponse>> =
        MutableStateFlow(Resource.Loading())
    val stateGetWaterIntake: StateFlow<Resource<GetWaterIntakeResponse>> get() = _stateGetWaterIntake

    private val _isDialogShown = mutableStateOf(false)
    val isDialogShown: State<Boolean> get() = _isDialogShown

    fun onAddClick() {
        _isDialogShown.value = true
    }

    fun onDismissDialog() {
        _isDialogShown.value = false
    }
    fun addWaterIntake(addWaterIntake: AddWaterIntake){
        viewModelScope.launch {
            try {
                _stateAddWaterIntake.value = Resource.Loading()
                Log.d("RecommendationViewModel", "Before calling repository.addWaterIntake")
                val addWaterIntake = repository.addWaterIntake(addWaterIntake)
                Log.d("RecommendationViewModel", "After calling repository.addWaterIntake")
                _stateAddWaterIntake.value = Resource.Success(addWaterIntake.data!!)
            } catch ( e : Exception){
                _stateAddWaterIntake.value = Resource.Error("[ View Model ] addWaterIntake: ${e.message}")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getWaterIntake(getWaterIntake: GetWaterIntake){
        viewModelScope.launch {
            _stateGetWaterIntake.value = Resource.Loading()
            try {
                val waterIntake = repository.getWaterIntake(getWaterIntake)
                _stateGetWaterIntake.value = Resource.Success(waterIntake.data!!)
            } catch (e : Exception){
                _stateGetWaterIntake.value = Resource.Error("Failed to fetch data: ${e.message}")
            }
        }
    }

}