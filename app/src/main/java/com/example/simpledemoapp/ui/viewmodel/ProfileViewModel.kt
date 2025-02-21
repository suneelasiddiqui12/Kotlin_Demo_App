package com.example.simpledemoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class User(
    val name: String,
    val email: String,
    val password: String
)

class ProfileViewModel : ViewModel() {
    // For demonstration purposes, using a static user. Replace this with real data if needed.
    val user: Flow<User> = flowOf(User("John Doe", "john.doe@example.com", "********"))
}
