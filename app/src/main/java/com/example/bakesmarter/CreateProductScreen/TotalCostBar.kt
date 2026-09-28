package com.example.bakesmarter.CreateProductScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight
import com.example.bakesmarter.ui.theme.SurfaceDark
import com.example.bakesmarter.ui.theme.SurfaceLight
import com.example.bakesmarter.ui.theme.TextSecondaryDark
import com.example.bakesmarter.ui.theme.TextSecondaryLight
import com.example.bakesmarter.R


@Composable
fun TotalCostBar(
    total: String,
    isDark: Boolean
) {
    val background = if (isDark) SurfaceDark else SurfaceLight
    val text = if (isDark) TextDark else TextLight
    val secondary = if (isDark) TextSecondaryDark else TextSecondaryLight

    Surface(shadowElevation = 4.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(background)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.CreateRecipe_Cost),
                color = secondary,
                fontSize = 14.sp
            )
            Text(
                text = "$total$",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = text
            )
        }
    }
}
