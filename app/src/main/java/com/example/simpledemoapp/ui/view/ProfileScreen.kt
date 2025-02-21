package com.example.simpledemoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.simpledemoapp.ui.viewmodel.ProfileViewModel
import com.example.simpledemoapp.ui.viewmodel.User
import com.example.simpledemoapp.ui.view.UserInfo

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
) {
    // Use the same gradient background as your HomeScreen
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFE0F7FA), Color(0xFFB3E5FC))
    )

    // Collect user info; provide an initial User if necessary
    val user = viewModel.user.collectAsState(
        initial = User("John Doe", "john.doe@example.com", "********")
    ).value

    Scaffold { paddingValues ->
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
                // Profile Card
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
                            text = "Profile",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 28.sp,
                            color = Color(0xFF6A11CB)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Display user details using the same UserInfo composable from HomeScreen
                        UserInfo(label = "Name:", value = user.name)
                        UserInfo(label = "Email:", value = user.email)

                        Spacer(modifier = Modifier.height(16.dp))

                        // "Edit Profile" Button (optional navigation)
                        Button(
                            onClick = {
                                // Navigate to an edit profile screen if available
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF145DA0))
                        ) {
                            Text(text = "Edit Profile", fontSize = 18.sp, color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // "Logout" Button
                        Button(
                            onClick = {
                                // For logout, navigate back to the login screen.
                                navController.navigate("login") {
                                    popUpTo("home") { inclusive = true }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text(text = "Logout", fontSize = 18.sp, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
