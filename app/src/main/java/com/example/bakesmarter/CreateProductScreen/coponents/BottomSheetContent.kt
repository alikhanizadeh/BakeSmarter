package com.example.bakesmarter.CreateProductScreen.coponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.R
import com.example.bakesmarter.data.local.ingredient.IngredientEntity

@Composable
fun BottomSheetContent(
    items: List<IngredientEntity>,
    selectedItems: MutableList<Long>,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // لیست اسکرول‌پذیر
        LazyColumn(
            modifier = Modifier
                .weight(1f)               // فضای باقی‌مانده رو می‌گیره
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { ingredient ->
                SelectableItem(
                    title = ingredient.name,
                    selected = selectedItems.contains(ingredient.id),
                    onClick = {
                        if (selectedItems.contains(ingredient.id)) {
                            selectedItems.remove(ingredient.id)
                        } else {
                            selectedItems.add(ingredient.id)
                        }
                    }
                )
            }
        }

        Button(
            onClick = onConfirm,
            enabled = selectedItems.isNotEmpty(),          // ← این خط
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(top = 10.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Gray.copy(alpha = 0.4f),  // اختیاری
                disabledContentColor = Color.White.copy(alpha = 0.6f)    // اختیاری
            )
        ) {
            Text(
                text = stringResource(R.string.Confirm),
                fontSize = 18.sp
            )
        }
    }
}