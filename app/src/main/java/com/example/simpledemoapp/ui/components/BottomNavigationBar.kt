package com.example.simpledemoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Favorites,
        BottomNavScreen.Profile
    )
    NavigationBar {
        // Observe the nav back stack to update the selected item.
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        // For the Home item, pop up to "home" to clear the product detail screen
                        if (screen.route == BottomNavScreen.Home.route) {
                            navController.navigate(screen.route) {
                                popUpTo(screen.route) {
                                    // This removes any intermediate destinations like productDetail
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        } else {
                            // For other bottom nav items, use similar logic if needed.
                            navController.navigate(screen.route) {
                                popUpTo(screen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }
    }
}