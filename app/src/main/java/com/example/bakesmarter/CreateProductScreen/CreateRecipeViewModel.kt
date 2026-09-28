package com.example.bakesmarter.CreateProductScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakesmarter.data.local.ingredient.IngredientEntity
import com.example.bakesmarter.data.local.product.ProductEntity
import com.example.bakesmarter.data.local.recipe.RecipeIngredientEntity
import com.example.bakesmarter.data.repository.CreateRecipeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreateRecipeViewModel(
    private val repository: CreateRecipeRepository
) : ViewModel() {

    /**
     * برای حالت ویرایش: محصول و مواد اولیه‌اش را یک‌بار می‌خواند
     * و از طریق onLoaded به Screen برمی‌گرداند تا فرم پر شود.
     * اگر محصولی با این id پیدا نشد (مثلاً حذف شده)، onLoaded صدا زده نمی‌شود.
     */
    fun loadProductForEdit(
        productId: Long,
        onLoaded: (ProductEntity, List<RecipeIngredientUiModel>) -> Unit
    ) {
        viewModelScope.launch {
            val product = repository.getProduct(productId).first() ?: return@launch
            val details = repository.getIngredientDetails(productId).first()

            val ingredientsUi = details.map { detail ->
                RecipeIngredientUiModel(
                    ingredient = IngredientEntity(
                        id = detail.ingredientId,
                        name = detail.name,
                        price = detail.price,
                        unit = detail.unit,
                        iconResId = detail.iconResId
                    ),
                    quantity = detail.quantity
                )
            }

            onLoaded(product, ingredientsUi)
        }
    }

    /**
     * ساخت محصول جدید (productId == null) یا ویرایش محصول موجود
     * (productId != null). برای ویرایش، مواد اولیهٔ قبلی حذف و
     * لیست فعلی فرم جایگزینش می‌شود.
     */
    fun saveProduct(
        productId: Long?,
        name: String,
        cost: Double,
        price: Double,
        imageResId: Int,
        ingredients: List<RecipeIngredientUiModel>,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {

            val lastUpdated = SimpleDateFormat(
                "yyyy-MM-dd HH:mm",
                Locale.getDefault()
            ).format(Date())

            val product = ProductEntity(
                id = productId ?: 0,
                name = name,
                lastUpdated = lastUpdated,
                imageResId = imageResId,
                cost = cost,
                price = price
            )

            val resolvedProductId: Long = if (productId == null) {
                repository.insertProduct(product)
            } else {
                repository.updateProduct(product)
                repository.deleteIngredientsForProduct(productId)
                productId
            }

            val recipeIngredients = ingredients.map { item ->
                RecipeIngredientEntity(
                    productId = resolvedProductId,
                    ingredientId = item.ingredient.id,
                    quantity = item.quantity
                )
            }

            if (recipeIngredients.isNotEmpty()) {
                repository.insertRecipeIngredients(recipeIngredients)
            }

            onSuccess()
        }
    }
}