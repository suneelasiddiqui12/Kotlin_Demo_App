package com.example.simpledemoapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    // Gradient background
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFF4A90E2), Color(0xFF145DA0))
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = if (data.visuals.message.contains("success", true)) Color(0xFF4CAF50) else Color.Red,
                    contentColor = Color.White
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBackground)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF145DA0),
                    fontSize = 28.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(value = name, label = "Name", isError = nameError) { name = it }
                if (nameError) ErrorText("Name cannot be empty")

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(value = email, label = "Email", isError = emailError, keyboardType = KeyboardType.Email) { email = it }
                if (emailError) ErrorText("Enter a valid email")

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    value = password,
                    label = "Password",
                    isError = passwordError,
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation()
                ) { password = it }
                if (passwordError) ErrorText("Password cannot be empty")

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        nameError = name.isEmpty()
                        emailError = email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        passwordError = password.isEmpty()

                        if (nameError || emailError || passwordError) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Please fill all fields correctly.")
                            }
                        } else {
                            scope.launch {
                                viewModel.saveCredentials(name, email, password)
                                Log.d("LoginScreen", "Registered - Name: $name, Email: $email")

                                snackbarHostState.showSnackbar("Login successful! 🎉")

                                // Navigate after showing the success message
                                kotlinx.coroutines.delay(1000) // Short delay before navigation
                                navController.navigate("home")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF145DA0))
                ) {
                    Text(text = "Login", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
}

// Reusable Custom TextField
@Composable
fun CustomTextField(
    value: String,
    label: String,
    isError: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xFF145DA0),
            focusedBorderColor = Color(0xFF4A90E2),
            cursorColor = Color(0xFF145DA0),
            unfocusedLabelColor = Color(0xFF145DA0),
            focusedLabelColor = Color(0xFF4A90E2),
        )
    )
}

// Reusable Error Text
@Composable
fun ErrorText(message: String) {
    Text(message, color = Color.Red, fontSize = 14.sp)
}
