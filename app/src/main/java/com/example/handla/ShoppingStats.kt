package com.example.handla

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ShoppingStats(
    items: List<ShoppingItem>
) {
    val totalCount = items.size

    val boughtCount = items.count { item -> item.isBought }

    Text(text = "$boughtCount av $totalCount köpta")

}