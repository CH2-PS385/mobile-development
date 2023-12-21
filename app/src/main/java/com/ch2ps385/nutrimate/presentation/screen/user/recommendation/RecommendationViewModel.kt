package com.ch2ps385.nutrimate.presentation.screen.user.recommendation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.model.AddMealPlanner
import com.ch2ps385.nutrimate.data.remote.model.GetMealPlanner
import com.ch2ps385.nutrimate.data.remote.responses.AddMealPlannerResponse
import com.ch2ps385.nutrimate.data.remote.responses.GetMealPlannerResponse
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecommendationViewModel(private val repository: UserRepository): ViewModel() {

    private val _stateRecommendationMeal: MutableStateFlow<Resource<AddMealPlannerResponse>> =
        MutableStateFlow(Resource.Initial())

    val stateRecommendationMeal: StateFlow<Resource<AddMealPlannerResponse>> get() = _stateRecommendationMeal
    fun addMealPlanner(addMealPlanner: AddMealPlanner){
        viewModelScope.launch {
            _stateRecommendationMeal.value = Resource.Loading()
            try {
                Log.d("RecommendationViewModel", "Before calling repository.addMealPlanner")
                val addUserPreferences = repository.addMealPlanner(addMealPlanner)
                Log.d("RecommendationViewModel", "After calling repository.addMealPlanner")
                _stateRecommendationMeal.value = Resource.Success(addUserPreferences.data!!)
            } catch ( e : Exception){
                _stateRecommendationMeal.value = Resource.Error("[ View Model ] addUserPreferences: ${e.message}")
            }
        }
    }

    private val _stateGetDataMealPlanner : MutableStateFlow<Resource<GetMealPlannerResponse>> = MutableStateFlow(Resource.Loading())
    val stateGetDataMealPlanner: StateFlow<Resource<GetMealPlannerResponse>>
        get() = _stateGetDataMealPlanner

    fun getMealPlanner(getMealPlanner: GetMealPlanner){
        viewModelScope.launch {
            _stateGetDataMealPlanner.value = Resource.Loading()
            try {
                val mealPlanner = repository.getDataMealPlanner(getMealPlanner)
                _stateGetDataMealPlanner.value = Resource.Success(mealPlanner.data!!)
            } catch (e : Exception){
                _stateGetDataMealPlanner.value = Resource.Error("Failed to fetch data: ${e.message}")
            }
        }
    }

    private val _isDialogShown = mutableStateOf(false)
    val isDialogShown: State<Boolean> get() = _isDialogShown

    private val _isDialogLoadingShown = mutableStateOf(false)

    val isDialogLoadingShown : State<Boolean> get() = _isDialogLoadingShown

    fun onDismissLoadingDialog(){
        _isDialogLoadingShown.value = false
    }
    fun onGenerateClick() {
        _isDialogShown.value = true
        _isDialogLoadingShown.value = true
    }

    fun onDismissDialog() {
        _isDialogShown.value = false
    }
}