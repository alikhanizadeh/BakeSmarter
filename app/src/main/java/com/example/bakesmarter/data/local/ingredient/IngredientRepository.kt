package com.example.bakesmarter.data.repository

import com.example.bakesmarter.data.local.ingredient.IngredientDao
import com.example.bakesmarter.data.local.ingredient.IngredientEntity
import kotlinx.coroutines.flow.Flow

class IngredientRepository(
    private val ingredientDao: IngredientDao
) {

    fun getAllIngredients(): Flow<List<IngredientEntity>> =
        ingredientDao.getAllIngredients()

    fun getIngredientById(ingredientId: Long): Flow<IngredientEntity?> =
        ingredientDao.getIngredientById(ingredientId)

    suspend fun getIngredientCount(): Int =
        ingredientDao.getIngredientCount()

    suspend fun insertIngredient(
        ingredient: IngredientEntity
    ): Long =
        ingredientDao.insertIngredient(ingredient)

    suspend fun insertIngredients(
        ingredients: List<IngredientEntity>
    ) =
        ingredientDao.insertIngredients(ingredients)

    suspend fun updateIngredient(
        ingredient: IngredientEntity
    ) =
        ingredientDao.updateIngredient(ingredient)

    suspend fun deleteIngredient(
        ingredient: IngredientEntity
    ) =
        ingredientDao.deleteIngredient(ingredient)
}