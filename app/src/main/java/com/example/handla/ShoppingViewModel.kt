package com.example.handla

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class ShoppingUiState(
    val items: List<ShoppingItem> = emptyList(),
    val newItemName: String = "",
    val selectedCategory: String = "Mat"
)

class ShoppingViewModel : ViewModel(){
    private var nextId = 1
    var uiState by mutableStateOf(ShoppingUiState())
        private set

    fun updateItemName(name: String){
        uiState = uiState.copy(newItemName = name)
    }

    fun updateCategory(category: String){
        uiState = uiState.copy(selectedCategory = category)
    }

    fun addItem(){
        if (uiState.newItemName.isBlank()) return

        val newItem = ShoppingItem(
            name = uiState.newItemName.trim(),
            id = nextId++,
            category = uiState.selectedCategory
        )
        uiState = uiState.copy(
            items = listOf(newItem) + uiState.items,
            newItemName = ""
        )
    }
}