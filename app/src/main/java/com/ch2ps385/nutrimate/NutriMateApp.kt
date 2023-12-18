package com.ch2ps385.nutrimate

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
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
import com.ch2ps385.nutrimate.data.remote.UserData
import com.ch2ps385.nutrimate.data.remote.model.AddUserByEmail
import com.ch2ps385.nutrimate.presentation.screen.auth.signin.GoogleAuthUiClient
import com.ch2ps385.nutrimate.presentation.screen.auth.signin.SignInScreen
import com.ch2ps385.nutrimate.presentation.screen.auth.signin.SignInViewModel
import com.ch2ps385.nutrimate.presentation.screen.profile.about.AboutScreen
import com.ch2ps385.nutrimate.presentation.screen.profile.profile.ProfileScreen
import com.ch2ps385.nutrimate.presentation.screen.profile.profiledetail.ProfileDetailScreen
import com.ch2ps385.nutrimate.presentation.screen.user.detailmenu.DetailContent
import com.ch2ps385.nutrimate.presentation.screen.user.detailmenu.MenuDetailScreen
import com.ch2ps385.nutrimate.presentation.screen.user.home.HomeScreen
import com.ch2ps385.nutrimate.presentation.screen.user.menu.MenuScreen
import com.ch2ps385.nutrimate.presentation.screen.user.preferences.UserPreferenceScreen
import com.ch2ps385.nutrimate.presentation.screen.user.preferences.UserPreferencesViewModel
import com.ch2ps385.nutrimate.presentation.screen.user.recommendation.RecommendationScreen
import com.ch2ps385.nutrimate.presentation.screen.user.reminder.ReminderScreen
import com.ch2ps385.nutrimate.presentation.ui.component.other.BottomBar
import com.ch2ps385.nutrimate.presentation.ui.component.other.Toolbar
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch

@Composable
fun NutriMateApp(
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

    Scaffold(
        topBar = {
//            if (currentRoute != Screen.SignIn.route && currentRoute != Screen.Home.route && currentRoute != Screen.Menu.route && currentRoute != Screen.Planner.route && currentRoute != Screen.Reminder.route && currentRoute != Screen.Profile.route) {
//                Toolbar(onBackClick = {
//                    navController.navigateUp()
//                })
//            }
            Toolbar(navController = navController)
        }    ,
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.SignIn.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.SignIn.route){
                val viewModel = viewModel<SignInViewModel>()
//                val viewModelUserPreferences = viewModel<UserPreferencesViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                LaunchedEffect(key1 = Unit) {
                    if(googleAuthUiClient.getSignedInUser() != null) {
                        navController.navigate("userpreferences")
//                        viewModel.addUserByEmail(
//                            AddUserByEmail(
//                                email = userData?.email!!,
//                                name = userData?.username!!
//                            )
//                        )
                    }
                }

                // activity result after user selecting the gmail user profile
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


                // Updating user/UI after sign in successful
                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if(state.isSignInSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Sign in successful",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate("userpreferences")
                        viewModel.resetState()
                    }
                }

//                LaunchedEffect(key1 = state.isSignInSuccessful) {
//                    if(state.isSignInSuccessful) {
//                        Toast.makeText(
//                            applicationContext,
//                            "Sign in successful",
//                            Toast.LENGTH_LONG
//                        ).show()
//                        val userData = googleAuthUiClient.getSignedInUser()
//                        viewModelUserPreferences.addUserByEmail(
//                            AddUserByEmail(
//                                email = userData?.email!!,
//                                name = userData.username!!
//                            )
//                        )
////                        viewModelUserPreferences.getUserByEmail(email = userData?.email)
//////                        navController.navigate("userpreferences")
//////                        viewModel.resetState()
//                    }
//                }

                //Initial screen with Sign in button
                SignInScreen(
//                    state = state,
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
                    userData = googleAuthUiClient.getSignedInUser(),
//                    onSignOut = {
//                        coroutineScope.launch {
//                            googleAuthUiClient.signOut()
//                            Toast.makeText(
//                                applicationContext,
//                                "Signed out",
//                                Toast.LENGTH_LONG
//                            ).show()
//                            navController.popBackStack()
//                        }
//                    },
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
                RecommendationScreen()
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
                Screen.ProfileDetail.route
            ){
                val userData = googleAuthUiClient.getSignedInUser()
                ProfileDetailScreen(userData = userData, navController = navController)
            }
            composable(
                Screen.Reminder.route
            ){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ReminderScreen()
                }
            }


        }

    }

}