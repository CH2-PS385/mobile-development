package com.ch2ps385.nutrimate.ui.navigation

sealed class Screen(val route: String){
    object  Home : Screen ("home")
    object Menu : Screen ("menu")
    object Planner : Screen ("planner")
    object Reminder : Screen ("reminder")
    object Profile : Screen ("profile")
    object UserPreferences : Screen ("userpreferences")
    object SignIn : Screen ("signin")
    object Register : Screen ("register")


}
