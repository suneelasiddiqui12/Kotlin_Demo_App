package com.example.simpledemoapp.ui.view
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Brush
import com.yourapp.ui.components.CustomAppBar

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
    productRating: String?
) {
    // Gradient Background
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFE0F7FA), Color(0xFFB3E5FC)) // Light Cyan to Light Blue
    )

    Scaffold(
        topBar = {
            CustomAppBar(navController = navController, title = "Product Details")

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
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Static Product Image Placeholder
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    // Replace this Box with an Image composable if you have an image resource
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Static Image", color = Color.White, fontSize = 20.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Product Info Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
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

                        ProductInfo(label = "💵 Price:", value = "💵${productPrice ?: "N/A"}")
                        ProductInfo(label = "🔢 Product ID:", value = productId ?: "N/A")
                        ProductInfo(label = "📝 Product Description:", value = productDescription ?: "N/A")
                        ProductInfo(label = "🎨 Product Color:", value = productColor ?: "N/A")
                        ProductInfo(label = "✅ Product Availability:", value = productAvailability ?: "N/A")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Static Ratings & Reviews Section
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Ratings & Reviews",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Display 4 filled stars and 1 unfilled star
                            repeat(5) { index ->
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Star",
                                    tint = if (index < 4) Color(0xFFFFD700) else Color.Gray,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "4.0", style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "💵${productRating ?: "N/A"}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Static Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* Handle add to cart */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        Text(text = "Add to Cart")
                    }
                    Button(
                        onClick = { /* Handle buy now */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        Text(text = "Buy Now")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Static Related Products Section
                Text(
                    text = "Related Products",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Three sample static related product cards
                    repeat(3) { index ->
                        Card(
                            modifier = Modifier
                                .width(120.dp)
                                .height(150.dp),
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .background(Color.LightGray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Img", color = Color.White)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Product ${index + 1}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Reusable Product Info Row (same as in your original code)
@Composable
fun ProductInfo(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}
