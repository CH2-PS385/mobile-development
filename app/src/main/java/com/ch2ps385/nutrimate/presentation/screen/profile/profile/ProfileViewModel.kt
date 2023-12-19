package com.ch2ps385.nutrimate.presentation.screen.profile.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.responses.DataGetUser
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository:UserRepository) : ViewModel() {

    private val _stateGetDataPreference: MutableStateFlow<Resource<DataGetUser>> = MutableStateFlow(
        Resource.Loading())
    val stateGetDataPreference : StateFlow<Resource<DataGetUser>> get() = _stateGetDataPreference

    fun getDataUserPref(email : String){
        viewModelScope.launch {
            _stateGetDataPreference.value = Resource.Loading()
            _stateGetDataPreference.value = Resource.Success(repository.getDataUser(email))
        }
    }
}