package com.ch2ps385.nutrimate.presentation.ui.component.other

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ch2ps385.nutrimate.presentation.ui.navigation.NavigationItem
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier : Modifier = Modifier,
){
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Menu",
                icon = Icons.Default.Menu,
                screen = Screen.Menu
            ),
            NavigationItem(
                title = "Planner",
                icon = Icons.Default.DateRange,
                screen = Screen.Planner
            ),
            NavigationItem(
                title = "Reminder",
                icon = Icons.Default.Info,
                screen = Screen.Reminder
            ),
            NavigationItem(
                title = "Profile",
                icon = Icons.Default.Person,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                          navController.navigate(item.screen.route){
                              popUpTo(navController.graph.findStartDestination().id){
                                  saveState = true
                              }
                              restoreState = true
                              launchSingleTop = true
                          }
                },

            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    val modifier = Modifier // You can provide any necessary modifiers here

    // Dummy implementation for NavigationItem and Screen
    val dummyNavigationItem = NavigationItem("Home", Icons.Default.Home, Screen.Home)

    Surface {
        BottomBar(
            modifier = modifier,
            navController = rememberNavController() // Make sure to import rememberNavController
        )
    }
}