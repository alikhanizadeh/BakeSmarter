package com.example.bakesmarter.pageProductitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bakesmarter.data.repository.ProductItemRepository

class ProductItemViewModelFactory(
    private val repository: ProductItemRepository,
    private val productId: Long
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(ProductItemViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return ProductItemViewModel(
                repository = repository,
                productId = productId
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}