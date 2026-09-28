package com.example.bakesmarter.data.repository

import com.example.bakesmarter.data.local.product.ProductDao
import com.example.bakesmarter.data.local.product.ProductEntity
import com.example.bakesmarter.data.local.recipe.RecipeIngredientDao
import com.example.bakesmarter.data.local.recipe.RecipeIngredientDetail
import com.example.bakesmarter.data.local.recipe.RecipeIngredientEntity
import kotlinx.coroutines.flow.Flow

// نکته: قبلاً این کلاس اشتباهی "aCreateRecipeRepository" نام‌گذاری شده بود
// (یک a اضافه اول اسم) در حالی که همه‌جا با نام CreateRecipeRepository
// صدا زده می‌شد — همین باعث خطای کامپایل می‌شد. اسم درست شد.
class CreateRecipeRepository(
    private val productDao: ProductDao,
    private val recipeIngredientDao: RecipeIngredientDao
) {

    // ابتدا محصول را ذخیره می‌کند و ID واقعی محصول را برمی‌گرداند.
    suspend fun insertProduct(product: ProductEntity): Long {
        return productDao.insertProduct(product)
    }

    // محصول موجود را به‌روزرسانی می‌کند (برای حالت ویرایش)
    suspend fun updateProduct(product: ProductEntity) {
        productDao.updateProduct(product)
    }

    // خواندن یک محصول برای پر کردن فرم ویرایش
    fun getProduct(productId: Long): Flow<ProductEntity?> {
        return productDao.getProductById(productId)
    }

    // خواندن مواد اولیهٔ یک محصول (برای پر کردن فرم ویرایش)
    fun getIngredientDetails(productId: Long): Flow<List<RecipeIngredientDetail>> {
        return recipeIngredientDao.getIngredientDetailsForProduct(productId)
    }

    // مواد اولیه مربوط به محصول را ذخیره می‌کند.
    suspend fun insertRecipeIngredients(
        recipeIngredients: List<RecipeIngredientEntity>
    ) {
        recipeIngredientDao.insertRecipeIngredients(recipeIngredients)
    }

    // برای ویرایش: مواد اولیهٔ قبلی محصول حذف می‌شود تا لیست جدید جایگزینش شود
    suspend fun deleteIngredientsForProduct(productId: Long) {
        recipeIngredientDao.deleteIngredientsForProduct(productId)
    }
}