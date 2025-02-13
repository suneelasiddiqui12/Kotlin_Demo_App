//package com.example.simpledemoapp
//
//import android.app.Application
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.navigation.NavController
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.collectLatest
//
//@Composable
//fun HomeScreen(
//    navController: NavController,
//    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
//) {
//    val user by viewModel.user.collectAsState()
//    val scope = rememberCoroutineScope()
//    val snackbarHostState = remember { SnackbarHostState() }
//
//    // Log retrieved user details
//    LaunchedEffect(user) {
//        Log.d("HomeScreen", "Retrieved User - Name: ${user.first}, Email: ${user.second}, Password: ${user.third}")
//    }
//
//    // Gradient Background
//    val gradientBackground = Brush.verticalGradient(
//        colors = listOf(Color(0xFF2193b0), Color(0xFF6dd5ed))
//    )
//
//    Scaffold(
//        snackbarHost = {
//            SnackbarHost(snackbarHostState) { data ->
//                Snackbar(
//                    snackbarData = data,
//                    containerColor = Color(0xFF4CAF50),
//                    contentColor = Color.White
//                )
//            }
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(gradientBackground)
//                .padding(paddingValues),
//            contentAlignment = Alignment.Center
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(24.dp)
//                    .background(Color.White, shape = RoundedCornerShape(16.dp))
//                    .padding(24.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Welcome, ${user.first ?: "User"}! 👋",
//                    style = MaterialTheme.typography.headlineMedium,
//                    fontSize = 28.sp,
//                    color = Color(0xFF2193b0)
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                UserInfo(label = "📧 Email:", value = user.second ?: "Unknown")
//                UserInfo(label = "🔑 Password:", value = user.third?.replace(Regex("."), "*") ?: "********")
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                Button(
//                    onClick = {
//                        scope.launch {
//                            viewModel.logout()
//                            snackbarHostState.showSnackbar("Logged out successfully! 🚀")
//                            kotlinx.coroutines.delay(1000) // Smooth logout transition
//                            navController.navigate("login") {
//                                popUpTo("home") { inclusive = true }
//                            }
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2193b0))
//                ) {
//                    Text(text = "Logout", fontSize = 18.sp, color = Color.White)
//                }
//            }
//        }
//    }
//}
//
//// Reusable User Info Row
//@Composable
//fun UserInfo(label: String, value: String) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 6.dp)
//    ) {
//        Text(text = label, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
//        Text(text = value, style = MaterialTheme.typography.headlineSmall, fontSize = 20.sp, color = Color.Black)
//    }
//}
//
//class HomeViewModel(application: Application) : AndroidViewModel(application) {
//    private val userPreferences = UserPreferences(application)
//
//    private val _user = MutableStateFlow(Triple<String?, String?, String?>(null, null, null))
//    val user = _user.asStateFlow()
//
//    init {
//        viewModelScope.launch {
//            userPreferences.getUser().collectLatest { userData ->
//                _user.value = userData
//            }
//        }
//    }
//
//    fun logout() {
//        viewModelScope.launch {
//            userPreferences.clearUser()
//        }
//    }
//}


package com.example.simpledemoapp

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val user by viewModel.user.collectAsState()
    val products by viewModel.products.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(user) {
        Log.d("HomeScreen", "Retrieved User - Name: ${user.first}, Email: ${user.second}, Password: ${user.third}")
    }

    // Gradient Background
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFE0F7FA), Color(0xFFB3E5FC)) // Light Cyan to Light Blue
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBackground)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // User Info Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Welcome, ${user.first ?: "User"}! 🎉",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 24.sp,
                            color = Color(0xFF6A11CB)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        UserInfo(label = "📧 Email:", value = user.second ?: "Unknown")
                        UserInfo(label = "🔑 Password:", value = user.third?.replace(Regex("."), "*") ?: "********")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Product List Section
                Text(
                    text = "🛍️ Available Products",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 22.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(8.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.7f)
                ) {
                    items(products) { product ->
                        ProductCard(product)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        scope.launch {
                            viewModel.logout()
                            snackbarHostState.showSnackbar("Logged out successfully! 🚀")
                            kotlinx.coroutines.delay(1000)
                            navController.navigate("login") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF145DA0))
                ) {
                    Text(text = "Logout", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
}

// Reusable User Info Row
@Composable
fun UserInfo(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.headlineSmall, fontSize = 20.sp, color = Color.Black)
    }
}

// Product Data Class
data class Product(val id: Int, val name: String, val price: String)

// Product Card UI
@Composable
fun ProductCard(product: Product) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = product.name, fontSize = 20.sp, color = Color.Black)
                Text(text = "💲${product.price}", fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    private val _user = MutableStateFlow(Triple<String?, String?, String?>(null, null, null))
    val user = _user.asStateFlow()

    private val _products = MutableStateFlow(
        listOf(
            Product(1, "iPhone 15 Pro", "999"),
            Product(2, "MacBook Pro 14", "1999"),
            Product(3, "Samsung Galaxy S23", "899"),
            Product(4, "Sony WH-1000XM5", "399")
        )
    )
    val products = _products.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.getUser().collectLatest { userData ->
                _user.value = userData
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.clearUser()
        }
    }
}
