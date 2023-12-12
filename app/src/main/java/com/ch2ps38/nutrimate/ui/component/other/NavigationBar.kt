package com.ch2ps38.nutrimate.ui.component.other

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ch2ps38.nutrimate.ui.navigation.NavigationItem
import com.ch2ps38.nutrimate.ui.navigation.Screen
import com.ch2ps38.nutrimate.ui.theme.solidWhite

@Composable
private fun BottomBar(
    modifier : Modifier = Modifier,
){
    NavigationBar(
        modifier = modifier
    ) {
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
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
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
        )
    }
}