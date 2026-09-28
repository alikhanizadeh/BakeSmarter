package com.example.bakesmarter.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bakesmarter.data.local.ingredient.IngredientDao
import com.example.bakesmarter.data.local.ingredient.IngredientEntity
import com.example.bakesmarter.data.local.product.ProductDao
import com.example.bakesmarter.data.local.product.ProductEntity
import com.example.bakesmarter.data.local.recipe.RecipeIngredientDao
import com.example.bakesmarter.data.local.recipe.RecipeIngredientEntity

@Database(
    entities = [
        ProductEntity::class,
        IngredientEntity::class,
        RecipeIngredientEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun ingredientDao(): IngredientDao

    abstract fun recipeIngredientDao(): RecipeIngredientDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bakesmarter.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}