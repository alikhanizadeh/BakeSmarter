package com.example.bakesmarter.pageProductitem

import androidx.compose.ui.graphics.Color

// مدل نمایش محصول بعد از ساخته شدن

data class IngredientUiModel(
    val name: String,
    val amount: String,
    val cost: String,
    val iconRes: Int,
    val bgColor: Color,
    val iconTint: Color
)



