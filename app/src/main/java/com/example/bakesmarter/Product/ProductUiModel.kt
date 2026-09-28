package com.example.bakesmarter.Product

// مدل موقت UI برای نمایش محصول
// بعداً ProductEntity مربوط به Room از روی این ساختار ساخته می‌شود.
data class ProductUiModel(
    val id: Long,
    val name: String,
    val lastUpdated: String,
    val imageUrl: Int,
    val cost: String,
    val price: String,
//    val margin: String
)