package com.example.bakesmarter.data.local.ingredient

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,

    // قیمت پایه ماده اولیه
    // مثال: 200 یعنی 200$ برای یک واحد
    val price: Double,

    // واحد قیمت
    // مثال: "L" ، "kg" ، "pcs"
    val unit: String,

    val iconResId: Int
)