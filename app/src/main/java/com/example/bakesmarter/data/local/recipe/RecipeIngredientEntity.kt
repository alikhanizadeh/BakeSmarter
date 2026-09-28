package com.example.bakesmarter.data.local.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_ingredients")
data class RecipeIngredientEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // محصولی که این ماده اولیه متعلق به آن است
    val productId: Long,

    // ماده اولیه
    val ingredientId: Long,

    // مقدار مصرف شده
    // مثال: 2 لیتر شیر
    val quantity: Double
)