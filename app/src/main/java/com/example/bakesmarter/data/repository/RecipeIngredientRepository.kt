package com.example.bakesmarter.data.repository

import com.example.bakesmarter.data.local.recipe.RecipeIngredientDao
import com.example.bakesmarter.data.local.recipe.RecipeIngredientEntity
import kotlinx.coroutines.flow.Flow

class RecipeIngredientRepository(
    private val dao: RecipeIngredientDao
) {

    fun getIngredientsForProduct(
        productId: Long
    ): Flow<List<RecipeIngredientEntity>> =
        dao.getIngredientsForProduct(productId)

    suspend fun insertRecipeIngredient(
        recipeIngredient: RecipeIngredientEntity
    ): Long =
        dao.insertRecipeIngredient(recipeIngredient)

    suspend fun insertRecipeIngredients(
        recipeIngredients: List<RecipeIngredientEntity>
    ) =
        dao.insertRecipeIngredients(recipeIngredients)

    suspend fun updateRecipeIngredient(
        recipeIngredient: RecipeIngredientEntity
    ) =
        dao.updateRecipeIngredient(recipeIngredient)

    suspend fun deleteRecipeIngredient(
        recipeIngredient: RecipeIngredientEntity
    ) =
        dao.deleteRecipeIngredient(recipeIngredient)

    suspend fun deleteIngredientsForProduct(
        productId: Long
    ) =
        dao.deleteIngredientsForProduct(productId)
}