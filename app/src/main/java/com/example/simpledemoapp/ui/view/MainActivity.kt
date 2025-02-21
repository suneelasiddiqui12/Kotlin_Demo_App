//package com.example.simpledemoapp.ui.view
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.*
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AppNavigator()
//        }
//    }
//}
//
//@Composable
//fun AppNavigator() {
//    val navController = rememberNavController()
//
//    NavHost(navController, startDestination = "login") {
//        composable("login") { LoginScreen(navController) }
//        composable("home") { HomeScreen(navController) }
//        composable("productDetail/{productId}/{productName}/{productPrice}/{productDescription}/{productColor}/{productAvailability}") { backStackEntry ->
//            val productId = backStackEntry.arguments?.getString("productId")
//            val productName = backStackEntry.arguments?.getString("productName")
//            val productPrice = backStackEntry.arguments?.getString("productPrice")
//            val productDescription = backStackEntry.arguments?.getString("productDescription")
//            val productColor = backStackEntry.arguments?.getString("productColor")
//            val productAvailability = backStackEntry.arguments?.getString("productAvailability")
//            val productRating = backStackEntry.arguments?.getString("productRating")
//            val productImageUrl= backStackEntry.arguments?.getString("productImageUrl")
//
//            ProductDetailScreen(navController, productId, productName, productPrice, productDescription, productColor, productAvailability, productRating, productImageUrl)
//        }
//    }
//}
//
//
package com.example.simpledemoapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.simpledemoapp.ui.navigation.BottomNavigationBar
import com.example.simpledemoapp.ui.screens.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    // Observe the current back stack entry to determine the current route.
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Only show the bottom navigation bar if not on the "login" screen.
            if (currentRoute != "login") {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                // In your LoginScreen, after a successful login, navigate to "home".
                LoginScreen(navController)
            }
            composable("home") { HomeScreen(navController) }
            composable("favorites") { LoginScreen(navController) }
            composable("profile") { ProfileScreen(navController) }
            composable("productDetail/{productId}/{productName}/{productPrice}/{productDescription}/{productColor}/{productAvailability}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")
                val productName = backStackEntry.arguments?.getString("productName")
                val productPrice = backStackEntry.arguments?.getString("productPrice")
                val productDescription = backStackEntry.arguments?.getString("productDescription")
                val productColor = backStackEntry.arguments?.getString("productColor")
                val productAvailability = backStackEntry.arguments?.getString("productAvailability")
                val productRating = backStackEntry.arguments?.getString("productRating")
                val productImageUrl = backStackEntry.arguments?.getString("productImageUrl")

                ProductDetailScreen(
                    navController,
                    productId,
                    productName,
                    productPrice,
                    productDescription,
                    productColor,
                    productAvailability,
                    productRating,
                    productImageUrl
                )
            }
        }
    }
}

