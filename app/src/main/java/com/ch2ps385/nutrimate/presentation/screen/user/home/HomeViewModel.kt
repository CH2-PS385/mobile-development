package com.ch2ps385.nutrimate.presentation.screen.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.responses.DataItem
import com.ch2ps385.nutrimate.data.remote.responses.DataRecommendation
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel()  {
    private val _stateDataItem : MutableStateFlow<Resource<List<DataItem>>> = MutableStateFlow(Resource.Loading())
    val stateDataItem: StateFlow<Resource<List<DataItem>>>
        get() = _stateDataItem

    private val _stateDataRecommendation : MutableStateFlow<Resource<List<DataRecommendation>>> = MutableStateFlow(Resource.Loading())
    val stateDataRecommendation: StateFlow<Resource<List<DataRecommendation>>>
        get() = _stateDataRecommendation

    fun getAllMenu(){
        viewModelScope.launch {
            _stateDataItem.value = Resource.Loading()
            try{
                val data = repository.getAllMenu()
                _stateDataItem.value = Resource.Success(data.data!!.data)
            }catch (e : Exception){
                _stateDataItem.value = Resource.Error("Failed to fetch data: ${e.message}")
            }
        }
    }

    fun getRecommendationMeal(){
        viewModelScope.launch {
            _stateDataRecommendation.value = Resource.Loading()
            try {
                val mealPlanner = repository.getRecommendationMeal()
                _stateDataRecommendation.value = Resource.Success(mealPlanner.data!!.data)
            }   catch (e : Exception){
                _stateDataRecommendation.value = Resource.Error("Failed to fetch data: ${e.message}")
            }
        }
    }
}