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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditItemDialog(
    item: ShoppingItem,
    onDismiss: () -> Unit,
    onSave: (Int, String, String) -> Unit,
    onDelete: (Int) -> Unit
) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedCategory by remember { mutableStateOf(item.category) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var showSaveDialog by remember { mutableStateOf(false) }

    val hasChanges = editedName != item.name ||
            editedCategory != item.category


    ModalBottomSheet(
        onDismissRequest = {
            if (hasChanges) {
                showSaveDialog = true
            } else {
                onDismiss
            }
        }
    ) {
        LaunchedEffect(Unit) {
            delay(500)
            focusRequester.requestFocus()
            delay(200)
            keyboardController?.show()
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Redigera vara")

            OutlinedTextField(
                value = editedName,
                onValueChange = { editedName = it },
                label = { Text("Varans namn") },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                singleLine = true
            )

            FlowRow {
                categories.forEach { category ->
                    FilterChip(
                        selected = editedCategory == category,
                        onClick = { editedCategory = category },
                        label = { Text(category) }
                    )
                }
            }
            TextButton(
                onClick = {
                    onDelete(item.id)
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Ta bort")
            }

            TextButton(
                onClick = {
                    onSave(item.id, editedName, editedCategory)
                },
                enabled = editedName.isNotBlank()
            ) {
                Text("Spara")
            }
        }
    }
    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = { Text("Spara ändringar?") },
            text = { Text("Du har osparade ändringar") },

            confirmButton = {
                TextButton(
                    onClick = {
                        onSave(item.id, editedName, editedCategory)
                    },
                    enabled = editedName.isNotBlank()
                ) {
                    Text("Spara")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = { onDismiss() }
                ) {
                    Text("Spara inte")
                }
            }
        )
    }

}