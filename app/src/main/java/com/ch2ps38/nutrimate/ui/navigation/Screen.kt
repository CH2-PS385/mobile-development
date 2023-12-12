package com.ch2ps38.nutrimate.ui.navigation

sealed class Screen(val route: String){
    object  Home : Screen ("home")
    object Menu : Screen ("menu")
    object Planner : Screen ("planner")
    object Reminder : Screen ("reminder")
    object Profile : Screen ("profile")
}
