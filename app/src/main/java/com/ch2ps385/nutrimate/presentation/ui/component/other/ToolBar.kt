package com.ch2ps385.nutrimate.presentation.ui.component.other

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.presentation.ui.navigation.Screen
import com.ch2ps385.nutrimate.presentation.ui.theme.NutriMateTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    val routesWithoutToolbar = listOf(
        Screen.SignIn.route,
        Screen.Home.route,
        Screen.Menu.route,
        Screen.Planner.route,
        Screen.Reminder.route,
        Screen.Profile.route,
        Screen.About.route,
        Screen.UserPreferences.route,
    )

    if (currentRoute !in routesWithoutToolbar) {
        TopAppBar(
            modifier = modifier,
            navigationIcon = {
                if (navController.previousBackStackEntry != null) {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = getTitle(navController.currentBackStackEntry),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            },
        )
    }
}

@Composable
fun getTitle(backStackEntry: NavBackStackEntry?): String {
    return backStackEntry?.destination?.route?.let { route ->
        when (route) {
            Screen.MenuDetail.route -> stringResource(id = R.string.toolbar_detail_menu)
            Screen.EditUserPreferences.route -> stringResource(id = R.string.toolbar_edit_preferences)
            else -> ""
        }
    } ?: ""
}

@Preview(showBackground = true)
@Composable
fun ToolbarPreview() {
    NutriMateTheme {
        val navController = rememberNavController()
        Toolbar(navController = navController)
    }
}


//@Preview(showBackground = true)
//@Composable
//fun ToolbarPreview() {
//    NutriMateTheme {
//        Toolbar()
//    }
//}
