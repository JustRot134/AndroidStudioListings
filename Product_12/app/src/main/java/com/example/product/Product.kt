package com.example.product

data class Product(
    val name: String,
    val price: Int,
    val discount: Int,
    val rating: Double,
    val inStock: Boolean,
    val emoji: String
)