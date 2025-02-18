package com.example.simpledemoapp.ui.viewmodel

import Product
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpledemoapp.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

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
