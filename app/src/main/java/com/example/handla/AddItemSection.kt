package com.example.handla

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon

var categories = listOf(
    "Mat", "Dryck", "Hushåll", "Hygien", "Övrigt"
)
@Composable
fun AddItemSection(
    uiState: ShoppingUiState,
    onNameChange: (String) -> Unit,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    onAddItem: () -> Unit
){
    var expanded by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

    OutlinedTextField(
        value = uiState.newItemName,
        onValueChange = onNameChange,
        label = { Text("Varans namn") }
    )
    Button(onClick = {expanded = true}) {
        Text(selectedCategory)
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        categories.forEach { category ->
            DropdownMenuItem(
                text = { Text(category) },
                onClick = {
                    onCategoryChange(category)
                    expanded = false
                }
            )
        }
    }
    Button(onClick = onAddItem, enabled = uiState.newItemName.isNotBlank()) {
        Text("Lägg till")
    }
    }
}