package com.example.handla

import android.app.AlertDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun EditItemDialog(
    item: ShoppingItem,
    onDismiss: () -> Unit,
    onSave: (Int, String, String) -> Unit
) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedCategory by remember { mutableStateOf(item.category) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text("Redigera vara")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = editedName,
                    onValueChange = { editedName = it },
                    label = { Text("Varans namn") }
                )
                FlowRow{
                    categories.forEach { category ->
                        FilterChip(
                            selected = editedCategory == category,
                            onClick = { editedCategory = category },
                            label = { Text(category) }
                        )

                    }
                }
            }

        },
        confirmButton = {
            TextButton(
                onClick = { onSave(item.id, editedName, editedCategory) },
                enabled = editedName.isNotBlank()
            ) {
                Text("Spara")
            }

        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Avbryt")
            }

        }
    )

}