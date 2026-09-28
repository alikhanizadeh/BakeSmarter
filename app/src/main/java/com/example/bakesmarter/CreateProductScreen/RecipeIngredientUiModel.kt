package com.example.bakesmarter.CreateProductScreen

import com.example.bakesmarter.data.local.ingredient.IngredientEntity

// ماده اولیه‌ای که کاربر برای ساخت محصول انتخاب کرده است.
//
// این مدل مخصوص UI است و هنوز در دیتابیس ذخیره نشده.
// quantity مقدار مصرف ماده اولیه در این محصول است.
data class RecipeIngredientUiModel(
    val ingredient: IngredientEntity,
    val quantity: Double
) {

    // هزینه این ماده در محصول
    val totalCost: Double
        get() = ingredient.price * quantity
}