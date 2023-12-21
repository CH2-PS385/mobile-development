package com.ch2ps385.nutrimate.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.ch2ps385.nutrimate.NutriMateApp
import com.ch2ps385.nutrimate.presentation.screen.auth.signin.GoogleAuthUiClient
import com.ch2ps385.nutrimate.presentation.ui.MainViewModel
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme
import com.google.android.gms.auth.api.identity.Identity


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
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
                val navController = rememberNavController()
                val startDestination = if (googleAuthUiClient.getSignedInUser() != null) {
                    Screen.Home.route
                } else {
                    Screen.WelcomeScreen.route
                }

                NutriMateApp(navController = navController, startDestination = startDestination)
            }
        }
    }
}

