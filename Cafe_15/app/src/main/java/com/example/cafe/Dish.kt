package com.example.cafe

const val EXTRA_NAME = "name"
const val EXTRA_PRICE = "price"
const val EXTRA_DESCRIPTION = "description"
const val EXTRA_EMOJI = "emoji"

data class Dish(
    val name: String,
    val price: Int,
    val description: String,
    val emoji: String
)