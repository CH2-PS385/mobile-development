package com.ch2ps385.nutrimate.presentation.screen.user.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.model.GetMealPlanner
import com.ch2ps385.nutrimate.data.remote.responses.DataItem
import com.ch2ps385.nutrimate.data.remote.responses.GetMealPlannerResponse
import com.ch2ps385.nutrimate.data.remote.responses.TodayMenuItem
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel()  {
    private val _stateDataItem : MutableStateFlow<Resource<List<TodayMenuItem>>> = MutableStateFlow(Resource.Loading())
    val stateDataItem: StateFlow<Resource<List<TodayMenuItem>>> get() = _stateDataItem

    private val _stateGetDataMealPlanner : MutableStateFlow<Resource<GetMealPlannerResponse>> = MutableStateFlow(Resource.Loading())
    val stateGetDataMealPlanner: StateFlow<Resource<GetMealPlannerResponse>>
        get() = _stateGetDataMealPlanner

    private val _stateGetAllMenu : MutableStateFlow<Resource<List<DataItem>>> = MutableStateFlow(Resource.Loading())
    val stateGetAllMenu: StateFlow<Resource<List<DataItem>>>
        get() = _stateGetAllMenu

    fun getAllMenu(){
        viewModelScope.launch {
            _stateGetAllMenu.value = Resource.Loading()
            try{
                val data = repository.getAllMenu()
                _stateGetAllMenu.value = Resource.Success(data.data!!.data)
            }catch (e : Exception){
                _stateGetAllMenu.value = Resource.Error("Failed to fetch data: ${e.message}")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodayMenu(){
        viewModelScope.launch {
            _stateDataItem.value = Resource.Loading()
            try{
                val data = repository.getTodayMenu()
                _stateDataItem.value = Resource.Success(data.data!!.data)
            }catch (e : Exception){
                _stateDataItem.value = Resource.Error("Failed to fetch data: ${e.message}")
            }
        }
    }

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
}