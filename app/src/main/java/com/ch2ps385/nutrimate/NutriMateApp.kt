package com.ch2ps385.nutrimate

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ch2ps385.nutrimate.common.Resource
import com.ch2ps385.nutrimate.data.remote.model.AddUserByEmail
import com.ch2ps385.nutrimate.di.Injection
import com.ch2ps385.nutrimate.presentation.screen.auth.signin.GoogleAuthUiClient
import com.ch2ps385.nutrimate.presentation.screen.auth.signin.SignInScreen
import com.ch2ps385.nutrimate.presentation.screen.auth.signin.SignInViewModel
import com.ch2ps385.nutrimate.presentation.screen.onboarding.WelcomeScreen
import com.ch2ps385.nutrimate.presentation.screen.profile.about.AboutScreen
import com.ch2ps385.nutrimate.presentation.screen.profile.profile.ProfileScreen
import com.ch2ps385.nutrimate.presentation.screen.user.UserViewModelFactory
import com.ch2ps385.nutrimate.presentation.screen.user.detailmenu.MenuDetailScreen
import com.ch2ps385.nutrimate.presentation.screen.user.home.HomeScreen
import com.ch2ps385.nutrimate.presentation.screen.user.menu.MenuScreen
import com.ch2ps385.nutrimate.presentation.screen.user.preferences.EditUserPreferencesScreen
import com.ch2ps385.nutrimate.presentation.screen.user.preferences.UserPreferenceScreen
import com.ch2ps385.nutrimate.presentation.screen.user.recommendation.RecommendationScreen
import com.ch2ps385.nutrimate.presentation.screen.user.reminder.ReminderScreen
import com.ch2ps385.nutrimate.presentation.ui.component.other.BottomBar
import com.ch2ps385.nutrimate.presentation.ui.component.other.Toolbar
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.google.android.gms.auth.api.identity.Identity
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NutriMateApp(
    startDestination : String,
    modifier : Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val applicationContext = LocalContext.current
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    val coroutineScope = rememberCoroutineScope()

    val successAddState = rememberUseCaseState()
    val successConfigState = State.Success(labelText = "Yeay, logged in successfully!")
    StateDialog(
        state = successAddState,
        config = StateConfig(state = successConfigState),
    )

    val failedAddState = rememberUseCaseState()
    val  failedConfigState = State.Failure(labelText = "Opss, try again!")

    StateDialog(
        state = failedAddState,
        config = StateConfig(state = failedConfigState),
    )

    Scaffold(
        topBar = {
            Toolbar(navController = navController)
        }    ,
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.SignIn.route){
                val viewModel : SignInViewModel = viewModel(factory = UserViewModelFactory(Injection.provideUserRepository(LocalContext.current)))
                val state by viewModel.state.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = Unit) {
                    if(googleAuthUiClient.getSignedInUser() != null) {
                        navController.navigate(Screen.Home.route)
                    }
                }

                if (state.isSignInSuccessful) {
                    val userData = googleAuthUiClient.getSignedInUser()
                    viewModel.addUserByEmail(
                        AddUserByEmail(
                            email = userData?.email!!,
                            name = userData.username!!
                        )
                    )
                    Log.d(TAG, "ADD NEW ACCOUNT DONE")
                    if (userData.email != null) {
                        viewModel.stateIsUserPreferencesFilled.collectAsState().value.let { state ->
                            when (state) {
                                is Resource.Loading -> {
                                    viewModel.checkIsUserPreferencesFilled(userData?.email)
                                    Log.d(TAG, "is Loading..")
                                }
                                is Resource.Success -> {
                                    LaunchedEffect(Unit) {
                                        successAddState.show()
                                        delay(500)
                                        successAddState.finish()
                                        if (state.data == true) {
                                            Log.e(TAG, "First Going to home")
                                            navController.navigate(Screen.Home.route)
                                            viewModel.resetState()
                                        } else {
                                            navController.navigate(Screen.UserPreferences.route)
                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    failedAddState.show()
                                    LaunchedEffect(Unit) {
                                        delay(2000)
                                        failedAddState.finish()
                                    }
                                }
                                else -> {
                                }
                            }
                        }
                    }
                } else if (googleAuthUiClient.getSignedInUser() != null) {
                    val userData = googleAuthUiClient.getSignedInUser()
                    Log.d(TAG, "GET INTO IF ELSE")
                    if (userData?.email != null) {
                        viewModel.stateIsUserPreferencesFilled.collectAsState().value.let { state ->
                            when (state) {
                                is Resource.Loading -> {
                                }
                                is Resource.Success -> {
                                    LaunchedEffect(Unit) {
                                        successAddState.show()
                                        delay(500)
                                        successAddState.finish()
                                        if (state.data == true) {
                                            Log.e(TAG, "Second Going to home")
                                            navController.navigate(Screen.Home.route)
                                            viewModel.resetState()
                                        } else {
                                            navController.navigate(Screen.UserPreferences.route)
                                            viewModel.resetState()
                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    failedAddState.show()
                                    LaunchedEffect(Unit) {
                                        delay(2000)
                                        failedAddState.finish()
                                    }
                                }
                                else -> {
                                }
                            }
                        }
                    }
                }

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        Log.d(TAG, "onCreate: Result ${result.resultCode}")
                        if(result.resultCode == RESULT_OK) {
                            coroutineScope.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                viewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )

                SignInScreen(
                    onSignInClick = {
                        coroutineScope.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
                            val userData = googleAuthUiClient.getSignedInUser()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }
                    }
                )
            }
            composable(Screen.UserPreferences.route){
                UserPreferenceScreen(
                    googleAuthUiClient = googleAuthUiClient,
                    userData = googleAuthUiClient.getSignedInUser(),
                    navController = navController
                )
            }
            composable(Screen.Home.route){
                HomeScreen(
                    userData = googleAuthUiClient.getSignedInUser(),
                    navController = navController
                )
                Log.d(TAG, "Berhasil pindah ke HOme Screen!")
            }
            composable(Screen.Planner.route){
                val userData = googleAuthUiClient.getSignedInUser()
                RecommendationScreen(
                    userData = userData,
                    navController = navController,
                    navigateToDetail = { foodId ->
                        navController.navigate(Screen.MenuDetail.createRoute(foodId))
                    }
                )
            }
            composable(Screen.Menu.route){
                MenuScreen(
                    navigateToDetail = { foodId ->
                        navController.navigate(Screen.MenuDetail.createRoute(foodId))
                    }
                )
                Log.d(TAG, "Berhasil pindah ke Menu Screen!")
            }
            composable(
                Screen.MenuDetail.route,
                arguments = listOf(navArgument("foodId"){ type = NavType.LongType})
            ){
                val id = it.arguments?.getLong("foodId") ?: -1L
                MenuDetailScreen(foodId = id) {

                }
            }
            composable(
                Screen.Profile.route
            ){
                val userData = googleAuthUiClient.getSignedInUser()
                ProfileScreen(userData = userData, navController = navController)
            }
            composable(
                Screen.About.route
            ){
                AboutScreen()
            }
            composable(
                Screen.Reminder.route
            ){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val userData = googleAuthUiClient.getSignedInUser()
                    ReminderScreen(userData = userData)
                }
            }
            composable(
                Screen.EditUserPreferences.route
            ){
                EditUserPreferencesScreen(
                    userData = googleAuthUiClient.getSignedInUser(),
                    navController = navController
                )
            }
            composable(
                Screen.WelcomeScreen.route
            ){
                WelcomeScreen(navController = navController)
            }


        }

    }

}