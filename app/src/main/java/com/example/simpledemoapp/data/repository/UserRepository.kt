package com.example.simpledemoapp.repository

import android.content.Context
import com.example.simpledemoapp.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(context: Context) {
    private val userPreferences = UserPreferences(context)

    suspend fun saveUser(name: String, email: String, password: String) {
        userPreferences.saveUser(name, email, password)
    }

    fun getUser(): Flow<Triple<String?, String?, String?>> {
        return userPreferences.getUser()
    }

    suspend fun clearUser() {
        userPreferences.clearUser()
    }
}
