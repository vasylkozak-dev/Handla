package com.example.handla

import android.R.attr.onClick
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ShoppingItemRow(
    item: ShoppingItem,
    onToggleBought: (Int) -> Unit,
    onEdit: (ShoppingItem) -> Unit
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onEdit(item) }),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Checkbox(
            checked = item.isBought,
            onCheckedChange = { onToggleBought(item.id) }
        )
        Column(
            modifier = Modifier.weight(1f)

        ) {
        Text(text = item.name, fontWeight = FontWeight.Bold)
        Text(text = item.category)
    }
    }

}