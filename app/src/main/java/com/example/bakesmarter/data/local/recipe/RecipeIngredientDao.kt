package com.example.bakesmarter.data.local.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


data class RecipeIngredientDetail(
    val recipeId: Long,
    val ingredientId: Long,
    val quantity: Double,
    val name: String,
    val price: Double,
    val unit: String,
    val iconResId: Int
)



@Dao
interface RecipeIngredientDao {

    @Query(
        "SELECT * FROM recipe_ingredients " +
                "WHERE productId = :productId"
    )
    fun getIngredientsForProduct(
        productId: Long
    ): Flow<List<RecipeIngredientEntity>>


    @Query(
        """
    SELECT
        ri.id AS recipeId,
        ri.ingredientId,
        ri.quantity,
        i.name,
        i.price,
        i.unit,
        i.iconResId
    FROM recipe_ingredients ri
    INNER JOIN ingredients i
        ON ri.ingredientId = i.id
    WHERE ri.productId = :productId
    ORDER BY ri.id ASC
    """
    )
    fun getIngredientDetailsForProduct(
        productId: Long
    ): Flow<List<RecipeIngredientDetail>>

    @Insert
    suspend fun insertRecipeIngredient(
        recipeIngredient: RecipeIngredientEntity
    ): Long

    @Insert
    suspend fun insertRecipeIngredients(
        recipeIngredients: List<RecipeIngredientEntity>
    )

    @Update
    suspend fun updateRecipeIngredient(
        recipeIngredient: RecipeIngredientEntity
    )

    @Delete
    suspend fun deleteRecipeIngredient(
        recipeIngredient: RecipeIngredientEntity
    )

    @Query(
        "DELETE FROM recipe_ingredients " +
                "WHERE productId = :productId"
    )
    suspend fun deleteIngredientsForProduct(
        productId: Long
    )
}