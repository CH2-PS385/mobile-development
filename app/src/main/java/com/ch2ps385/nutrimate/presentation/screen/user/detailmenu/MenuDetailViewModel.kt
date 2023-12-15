package com.ch2ps385.nutrimate.presentation.screen.user.detailmenu

import com.ch2ps385.nutrimate.data.remote.responses.Data
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuDetailViewModel(private val repository :UserRepository) : ViewModel() {
    private val _state: MutableStateFlow<Resource<Data>> = MutableStateFlow(Resource.Loading())

    val state : StateFlow<Resource<Data>> get() = _state

    fun getMenuById(foodId:String){
        viewModelScope.launch {
            _state.value = Resource.Loading()
            _state.value = Resource.Success(repository.getMenuById(foodId))
        }
    }
}