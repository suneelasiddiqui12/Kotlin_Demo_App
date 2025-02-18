package com.example.simpledemoapp.ui.view

import ProductCard
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
import androidx.navigation.NavController
import com.example.simpledemoapp.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
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
                        ProductCard(product, navController)
                    }
                }

//                LazyColumn(
//                    modifier = Modifier.fillMaxHeight(0.7f)
//                ) {
//                    items(products) { product ->
//                        ProductCard(product)
//                        navController.navigate("productDetail/${product.id}/${product.name}/${product.price}")
//
//
//                    }
//                }

                Spacer(modifier = Modifier.height(30.dp))

//                Button(
//                    onClick = {
//                        if (products.isNotEmpty()) {
//                            val product = products.first() // Passing the first product as an example
//                            navController.navigate("productDetail/${product.id}/${product.name}/${product.price}")
//                        } else {
//                            scope.launch {
//                                snackbarHostState.showSnackbar("No products available!")
//                            }
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF145DA0))
//                ) {
//                    Text(text = "Next", fontSize = 18.sp, color = Color.White)
//                }


//                Button(
//                    onClick = {
//                        scope.launch {
//                            viewModel.logout()
//                            snackbarHostState.showSnackbar("Logged out successfully! 🚀")
//                            kotlinx.coroutines.delay(1000)
//                            navController.navigate("login") {
//                                popUpTo("home") { inclusive = true }
//                            }
//                        }
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF145DA0))
//                ) {
//                    Text(text = "Logout", fontSize = 18.sp, color = Color.White)
//                }
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


