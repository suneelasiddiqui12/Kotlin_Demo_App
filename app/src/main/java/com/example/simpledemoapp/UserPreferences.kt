package com.example.simpledemoapp

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.security.MessageDigest
import android.util.Base64
import android.util.Log

import kotlinx.coroutines.flow.Flow


private val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context) {

    private object PreferencesKeys {
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
    }

    suspend fun saveUser(name: String, email: String, password: String) {
        val hashedPassword = hashPassword(password)  // Hash password before storing
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.NAME] = name
            preferences[PreferencesKeys.EMAIL] = email
            preferences[PreferencesKeys.PASSWORD] = hashedPassword
        }
    }

    fun getUser(): Flow<Triple<String?, String?, String?>> {
        return context.dataStore.data.map { preferences ->
            Triple(
                preferences[PreferencesKeys.NAME],
                preferences[PreferencesKeys.EMAIL],
                preferences[PreferencesKeys.PASSWORD]
            )
        }
    }

//    suspend fun getUser(): Triple<String?, String?, String?> {
//        val prefs = context.dataStore.data.map { preferences ->
//            Triple(
//                preferences[PreferencesKeys.NAME],
//                preferences[PreferencesKeys.EMAIL],
//                preferences[PreferencesKeys.PASSWORD]
//            )
//        }.first()
//        return prefs
//    }

    suspend fun clearUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.NAME)
            preferences.remove(PreferencesKeys.EMAIL)
            preferences.remove(PreferencesKeys.PASSWORD)
        }
    }

    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(bytes)
        return Base64.encodeToString(hashedBytes, Base64.NO_WRAP) // NO_WRAP avoids line breaks
    }
}

