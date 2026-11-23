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
    var uiState by mutableStateOf(ShoppingUiState())
        private set

    fun updateItemName(name: String){
        uiState = uiState.copy(newItemName = name)
    }

    fun updateCategory(category: String){
        uiState = uiState.copy(selectedCategory = category)
    }
}