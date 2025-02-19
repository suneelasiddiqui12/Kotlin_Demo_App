package com.example.simpledemoapp.ui.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.Brush

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: String?,
    productName: String?,
    productPrice: String?,
    productDescription: String?,
    productColor: String?,
    productAvailability: String?,

) {
    // Gradient Background
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFE0F7FA), Color(0xFFB3E5FC)) // Light Cyan to Light Blue
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Product Details", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF145DA0))
            )
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Product Info Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "📦 ${productName ?: "Unknown Product"}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 24.sp,
                            color = Color(0xFF6A11CB)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        ProductInfo(label = "💰 Price:", value = "💲${productPrice ?: "N/A"}")
                        ProductInfo(label = "🆔 Product ID:", value = productId ?: "N/A")
                        ProductInfo(label = "🆔 Product Description:", value = productDescription ?: "N/A")
                        ProductInfo(label = "🆔 Product Color:", value = productColor ?: "N/A")
                        ProductInfo(label = "🆔 Product Availability:", value = productAvailability ?: "N/A")
                    }
                }
            }
        }
    }
}

// Reusable Product Info Row (same as UserInfo in HomeScreen)
@Composable
fun ProductInfo(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.headlineSmall, fontSize = 20.sp, color = Color.Black)
    }
}
