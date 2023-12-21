package com.ch2ps385.nutrimate.presentation.ui.component.other

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.ui.navigation.NavigationItem
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.neutralColor1

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    val routesWithoutBottomBar = listOf(
        Screen.SignIn.route,
        Screen.UserPreferences.route,
        Screen.About.route,
        Screen.Register.route,
        Screen.MenuDetail.route,
        Screen.WelcomeScreen.route,
        Screen.EditUserPreferences.route
    )

    if (currentRoute !in routesWithoutBottomBar) {
        NavigationBar(
            modifier = modifier
        ) {
            val navigationItems = listOf(
                NavigationItem(
                    title = "Home",
                    icon = painterResource(id = R.drawable.nav_home),
                    screen = Screen.Home
                ),
                NavigationItem(
                    title = "Menu",
                    icon = painterResource(id = R.drawable.nav_list),
                    screen = Screen.Menu
                ),
                NavigationItem(
                    title = "Planner",
                    icon = painterResource(id = R.drawable.nav_planner),
                    screen = Screen.Planner
                ),
                NavigationItem(
                    title = "Reminder",
                    icon = painterResource(id = R.drawable.nav_reminder),
                    screen = Screen.Reminder
                ),
                NavigationItem(
                    title = "Profile",
                    icon = painterResource(id = R.drawable.nav_profile),
                    screen = Screen.Profile
                ),
            )
            navigationItems.map { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                fontFamily = FontFamily(Font(R.font.inter_medium)),
                                fontWeight = FontWeight(500),
                                color = neutralColor1,
                                textAlign = TextAlign.Center
                            )
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
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
}


@Preview
@Composable
fun BottomBarPreview() {
    val modifier = Modifier // You can provide any necessary modifiers here

    // Dummy implementation for NavigationItem and Screen
    val dummyNavigationItem = NavigationItem("Home", painterResource(id = R.drawable.nav_list), Screen.Home)

    Surface {
        BottomBar(
            modifier = modifier,
            navController = rememberNavController() // Make sure to import rememberNavController
        )
    }
}