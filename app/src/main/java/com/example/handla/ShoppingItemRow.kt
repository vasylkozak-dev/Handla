package com.example.handla

import android.R
import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
        Text(text = item.name, fontWeight = FontWeight.Bold)
        Text(
            text = item.category,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = CircleShape
                )
                .padding(horizontal = 12.dp, vertical = 4.dp),
        )
        }
    }
    }