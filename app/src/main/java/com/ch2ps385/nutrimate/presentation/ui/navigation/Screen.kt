package com.ch2ps385.nutrimate.presentation.ui.navigation

sealed class Screen(val route: String){
    object  Home : Screen("home")
    object Menu : Screen("menu")
    object Planner : Screen("planner")
    object Reminder : Screen("reminder")
    object Profile : Screen("profile")
    object UserPreferences : Screen("userpreferences")
    object SignIn : Screen("signin")
    object Register : Screen("register")

    object MenuDetail : Screen("menu/{foodId}"){
        fun createRoute(foodId : Long) = "menu/$foodId"
    }

    object About : Screen("about")

    object EditUserPreferences: Screen("edituserpreferences")

    object WelcomeScreen: Screen("welcome")




}
