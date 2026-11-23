package com.example.handla

data class ShoppingItem(
    val name: String,
    val id: Int,
    val category: String,
    val isBought: Boolean = false
)