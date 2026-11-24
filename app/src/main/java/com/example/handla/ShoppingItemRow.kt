package com.example.handla

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ShoppingItemRow(
    item: ShoppingItem
){
    Column() {
        Text(text = item.name, fontWeight = FontWeight.Bold)
        Text(text = item.category)
    }

}