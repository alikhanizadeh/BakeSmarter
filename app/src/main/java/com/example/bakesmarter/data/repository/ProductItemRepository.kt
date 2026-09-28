package com.example.bakesmarter.data.repository

import com.example.bakesmarter.data.local.product.ProductDao
import com.example.bakesmarter.data.local.product.ProductEntity
import com.example.bakesmarter.data.local.recipe.RecipeIngredientDao
import com.example.bakesmarter.data.local.recipe.RecipeIngredientDetail
import kotlinx.coroutines.flow.Flow

class ProductItemRepository(
    private val productDao: ProductDao,
    private val recipeIngredientDao: RecipeIngredientDao
) {

    // دریافت اطلاعات خود محصول
    fun getProduct(productId: Long): Flow<ProductEntity?> {
        return productDao.getProductById(productId)
    }

    // دریافت مواد اولیه استفاده شده در محصول
    fun getIngredientDetails(
        productId: Long
    ): Flow<List<RecipeIngredientDetail>> {
        return recipeIngredientDao.getIngredientDetailsForProduct(productId)
    }
}