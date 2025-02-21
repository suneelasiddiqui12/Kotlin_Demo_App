package com.example.simpledemoapp.ui.view
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

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

    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("productDetail/{productId}/{productName}/{productPrice}/{productDescription}/{productColor}/{productAvailability}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val productName = backStackEntry.arguments?.getString("productName")
            val productPrice = backStackEntry.arguments?.getString("productPrice")
            val productDescription = backStackEntry.arguments?.getString("productDescription")
            val productColor = backStackEntry.arguments?.getString("productColor")
            val productAvailability = backStackEntry.arguments?.getString("productAvailability")
            val productRating = backStackEntry.arguments?.getString("productRating")
            val productImageUrl= backStackEntry.arguments?.getString("productImageUrl")

            ProductDetailScreen(navController, productId, productName, productPrice, productDescription, productColor, productAvailability, productRating, productImageUrl)
        }
    }
}


