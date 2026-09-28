package com.example.bakesmarter.data.local.ingredient

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredients ORDER BY id DESC")
    fun getAllIngredients(): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM ingredients WHERE id = :ingredientId LIMIT 1")
    fun getIngredientById(ingredientId: Long): Flow<IngredientEntity?>

    @Query("SELECT COUNT(*) FROM ingredients")
    suspend fun getIngredientCount(): Int

    @Insert
    suspend fun insertIngredient(ingredient: IngredientEntity): Long

    @Insert
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    @Update
    suspend fun updateIngredient(ingredient: IngredientEntity)

    @Delete
    suspend fun deleteIngredient(ingredient: IngredientEntity)
}