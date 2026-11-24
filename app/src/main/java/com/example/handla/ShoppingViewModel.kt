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
    fun toggleItemBought(
        id: Int
    ){
        val updatedItems = uiState.items.map {
            item ->
            if (item.id == id){
                item.copy(isBought = !item.isBought)
            } else {
                item
            }
        }
        uiState = uiState.copy(
            items = updatedItems
        )
    }

    fun deleteItem(id: Int) {
        val updatedItems = uiState.items.filter { item ->
            item.id != id
        }
        uiState = uiState.copy(
            items = updatedItems
        )
    }

    fun editItem(
        id: Int,
        newName: String,
        newCategory: String
    ) {
        if (newName.isBlank()) return
        val updatedItems = uiState.items.map {
            item ->
            if (item.id == id){
                item.copy(name = newName, category = newCategory)
            } else {
                item
            }
        }
        uiState = uiState.copy(
            items = updatedItems
        )
    }
}