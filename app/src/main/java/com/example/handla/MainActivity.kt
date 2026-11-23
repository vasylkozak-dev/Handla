package com.example.handla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun ShoppingScreen(
    viewModel: ShoppingViewModel = viewModel()
) {
    val uiState = viewModel.uiState

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

    AddItemSection(
        uiState = uiState,
        onNameChange = { viewModel.updateItemName(it) },
        selectedCategory = uiState.selectedCategory,
        onCategoryChange = { viewModel.updateCategory(it) },
        onAddItem = { viewModel.addItem()}
    )
        }
    }
}