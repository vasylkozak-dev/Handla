package com.example.handla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.handla.ui.theme.HandlaTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HandlaTheme {
                ShoppingScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HandlaTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(
    viewModel: ShoppingViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    var editingItem by remember {
        mutableStateOf<ShoppingItem?>(null)
    }

    Scaffold (
        topBar = {
           TopAppBar(
                title = {
                    Text("Handla")
                },
               actions = {
                   ShoppingStats(
                       items = uiState.items
                   )
               }
            )
        },
        bottomBar = {
            AddItemSection(
                uiState = uiState,
                onNameChange = { viewModel.updateItemName(it) },
                selectedCategory = uiState.selectedCategory,
                onCategoryChange = { viewModel.updateCategory(it) },
                onAddItem = { viewModel.addItem()}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {


    if (uiState.items.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Listan är tom", fontSize = 20.sp)
        }
    } else {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
        ) {
            items(
                items = uiState.items,
                key = { it.id }
            ) { item ->
                ShoppingItemRow(
                    item = item,
                    onToggleBought = { viewModel.toggleItemBought(it) },
                    onEdit = { editingItem = it }
                )

            }
        }
    }

        }
        editingItem?.let { item ->
            EditItemDialog(
                item = item,
                onDismiss = { editingItem = null },
                onSave = { id, newName, newCategory ->
                    viewModel.editItem(id, newName, newCategory)
                    editingItem = null
                },
                        onDelete = { id ->
                    viewModel.deleteItem(id)
                    editingItem = null
                }
            )
        }
    }
}