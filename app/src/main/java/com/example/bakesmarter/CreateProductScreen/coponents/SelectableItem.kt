

package com.example.bakesmarter.CreateProductScreen.coponents

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bakesmarter.ui.theme.Primary

@Composable
fun SelectableItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val background =
        if (selected) Primary
            .copy(alpha = 0.15f) else androidx.compose.ui.graphics.Color.Transparent
    val border =
        if (selected) Primary else androidx.compose.ui.graphics.Color.Gray

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(background)
            .border(2.dp, border, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
