package com.example.simpledemoapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavScreen("home", Icons.Default.Home, "Home")
    object Favorites : BottomNavScreen("favorites", Icons.Default.Favorite, "Favorites")
    object Profile : BottomNavScreen("profile", Icons.Default.Person, "Profile")
}
