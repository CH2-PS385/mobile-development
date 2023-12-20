package com.ch2ps385.nutrimate.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.ch2ps385.nutrimate.NutriMateApp
import com.ch2ps385.nutrimate.presentation.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                !viewModel.isReady.value
            }
        }
        setContent {
            NutriMateTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    NutriMateApp()
//                }
                val navController = rememberNavController()
                NutriMateApp(navController=navController)
            }
        }
    }
}

