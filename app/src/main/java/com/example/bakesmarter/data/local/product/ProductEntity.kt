package com.example.bakesmarter.data.local.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,

    val lastUpdated: String,

    val imageResId: Int,

    // هزینه تولید محصول
    val cost: Double,

    // قیمت فروش محصول
    val price: Double,

//    // درصد سود
//    val margin: Double
)