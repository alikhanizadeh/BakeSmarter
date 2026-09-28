package com.example.bakesmarter.pageProductitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakesmarter.data.local.product.ProductEntity
import com.example.bakesmarter.data.local.recipe.RecipeIngredientDetail
import com.example.bakesmarter.data.repository.ProductItemRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class ProductItemUiState(
    val product: ProductEntity? = null,
    val ingredients: List<RecipeIngredientDetail> = emptyList()
)

class ProductItemViewModel(
    repository: ProductItemRepository,
    productId: Long
) : ViewModel() {

    val uiState: StateFlow<ProductItemUiState> =
        combine(
            repository.getProduct(productId),
            repository.getIngredientDetails(productId)
        ) { product, ingredients ->

            ProductItemUiState(
                product = product,
                ingredients = ingredients
            )

        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductItemUiState()
        )
}