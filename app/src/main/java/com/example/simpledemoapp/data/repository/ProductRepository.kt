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
            "In-Stock",
            "\"New review: Great product! Highly recommended.\"",
            "https://iphonewalay.pk/cdn/shop/files/apple-iphone-15-pro-pakistan-priceoye-w16uh-500x500_800x.webp?v=1709925539"
            ),
        Product(
            2,
            "MacBook Pro 16",
            "19994",
            "Adjustable built-in kickstand, especially designed for ergonomic typing angles. Not only to facilitate work, but to offer you a better visual experience.",
            "Pink, Red, Yellow",
            "In-Stock",
            "\"New review: Worst product ever\"",
            "https://images-cdn.ubuy.ae/633aa5377c62ab34c07fe77a-2021-apple-macbook-pro-16-inch-apple.jpg"
            ),
        Product(
            3,
            "Samsung Galaxy S23",
            "89933",
            "Get ready for a Gallery full of epic night shots everyone will want. Nightography's enhanced AI keeps details clear, so low light photos and videos will be bright and colorful from dusk to dawn and back again.",
            "Blue, Red, Yellow",
            "Out-of-Stock",
            "\"New review: Great for workplaces\"",
            "https://images-cdn.ubuy.ae/633aa5377c62ab34c07fe77a-2021-apple-macbook-pro-16-inch-apple.jpg"
            ),
        Product(
            4,
            "Sony WH-1000XM5",
            "39559",
            "With two processors controlling eight microphones, Auto NC Optimizer for automatically optimising noise cancelling based on your wearing conditions",
            "Black, Red, Yellow, Green",
            "Out-of-Stock",
            "\"New review: Kindly restock\"",
            "https://islamabadelectronics.com/wp-content/uploads/2024/07/103364_original_local_1200x1050_v3_converted-570x499.webp"
            )
    )
}
