package com.example.compose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Calendar : Screen("calendar")
    object Notifications : Screen("notifications")
    object Profile : Screen("profile")
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            // Replace with HomeScreen() when fully migrated
            // HomeScreen(navController) 
        }
        composable(Screen.Calendar.route) {
            // CalendarScreen(navController)
        }
        composable(Screen.Notifications.route) {
            // NotificationScreen(navController)
        }
        composable(Screen.Profile.route) {
            // ProfileScreen(navController)
        }
    }
}
