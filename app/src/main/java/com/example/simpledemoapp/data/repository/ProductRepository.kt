package com.example.simpledemoapp.repository

import Product


class ProductRepository {
    fun getProducts(): List<Product> = listOf(
        Product(
             1,
            "Iphone 15 Pro Max",
            "20333",
            "The iPhone 15 Pro Max is a testament to excellence, designed for photographers, creators, professionals, and enthusiasts who demand the very best. ",
            "Blue, Black, Pink",
            "In-Stock"
            ),
        Product(
            2,
            "MacBook Pro 16",
            "19994",
            "Adjustable built-in kickstand, especially designed for ergonomic typing angles. Not only to facilitate work, but to offer you a better visual experience.",
            "Pink, Red, Yellow",
            "In-Stock"
            ),
        Product(
            3,
            "Samsung Galaxy S23",
            "89933",
            "Get ready for a Gallery full of epic night shots everyone will want. Nightography's enhanced AI keeps details clear, so low light photos and videos will be bright and colorful from dusk to dawn and back again.",
            "Blue, Red, Yellow",
            "Out-of-Stock"
            ),
        Product(
            4,
            "Sony WH-1000XM5",
            "39559",
            "With two processors controlling eight microphones, Auto NC Optimizer for automatically optimising noise cancelling based on your wearing conditions",
            "Black, Red, Yellow, Green",
            "Out-of-Stock"
            )
    )
}
