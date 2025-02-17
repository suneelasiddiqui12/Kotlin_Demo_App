package com.example.simpledemoapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.security.MessageDigest
import android.util.Base64
import com.example.simpledemoapp.UserPreferences

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    fun saveCredentials(name: String, email: String, password: String) {
        val hashedPassword = hashPassword(password)
        viewModelScope.launch {
            userPreferences.saveUser(name, email, hashedPassword)
        }
    }

    private fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(bytes)
        return Base64.encodeToString(hashedBytes, Base64.NO_WRAP) // Works on API 24+
    }
}
