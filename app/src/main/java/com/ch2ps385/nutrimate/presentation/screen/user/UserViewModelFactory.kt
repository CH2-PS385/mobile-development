package com.ch2ps385.nutrimate.presentation.screen.user

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ch2ps385.nutrimate.data.repository.UserRepository
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.user.detailmenu.MenuDetailViewModel
import com.ch2ps385.nutrimate.presentation.screen.user.menu.MenuViewModel

class UserViewModelFactory (private val userRepository: UserRepository):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MenuViewModel::class.java) -> MenuViewModel(userRepository) as T
            modelClass.isAssignableFrom(MenuDetailViewModel::class.java) -> MenuDetailViewModel(userRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: UserViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context) : UserViewModelFactory =
            instance ?: synchronized(this){
                instance ?: UserViewModelFactory(Injection.provideUserRepository(context))
            }.also{instance = it}
    }
}