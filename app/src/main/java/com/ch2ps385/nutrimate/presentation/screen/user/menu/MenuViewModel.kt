package com.ch2ps385.nutrimate.presentation.screen.user.menu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.responses.Data
import com.ch2ps385.nutrimate.data.remote.responses.DataItem
import com.ch2ps385.nutrimate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel(private val repository: UserRepository) : ViewModel() {

    private val _state : MutableStateFlow<Resource<List<DataItem>>> = MutableStateFlow(Resource.Loading())
    val state: StateFlow<Resource<List<DataItem>>>
        get() = _state

    fun getAllMenu(){
        viewModelScope.launch {
            _state.value = Resource.Loading()
            try{
                val data = repository.getAllMenu()
                _state.value = Resource.Success(data.data!!.data)
            }catch (e : Exception){
                _state.value = Resource.Error("Failed to fetch data: ${e.message}")
            }
        }
    }

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query

//    fun search(newQuery : String){
//        _query.value = newQuery
//        getAllMenu()
//    }

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            _state.value = Resource.Loading()
            try {
                val allMenu = repository.getAllMenu().data?.data ?: emptyList()
                val filteredMenu = if (newQuery.isNotEmpty()) {
                    allMenu.filter { menu ->
                        // Sesuaikan dengan kriteria pencarian, misalnya nama makanan
                        menu.namaMakananClean?.contains(newQuery, true) == true
                    }
                } else {
                    allMenu
                }
                _state.value = Resource.Success(filteredMenu)
            } catch (e: Exception) {
                _state.value = Resource.Error("Failed to fetch data: ${e.message}")
            }
        }
    }



}