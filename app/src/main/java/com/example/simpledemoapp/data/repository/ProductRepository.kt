package com.example.simpledemoapp.repository

import Product

class ProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "iPhone 15 Pro", "999"),
            Product(2, "MacBook Pro 14", "1999"),
            Product(3, "Samsung Galaxy S23", "899"),
            Product(4, "Sony WH-1000XM5", "399")
        )
    }
}
