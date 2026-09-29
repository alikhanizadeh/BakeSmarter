package com.example.bakesmarter.Product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakesmarter.R
import com.example.bakesmarter.data.local.product.ProductEntity
import com.example.bakesmarter.data.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val products: StateFlow<List<ProductEntity>> =
        repository.getAllProducts().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    // داخل ProductViewModel
    val uiProducts: StateFlow<List<ProductUiModel>> = products.map { list ->
        list.map { product ->
            ProductUiModel(
                id = product.id,
                name = product.name,
                lastUpdated = product.lastUpdated,
                imageUrl = product.imageResId,
                cost = "$%.2f".format(product.cost),
                price = "$%.2f".format(product.price)
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun getProduct(productId: Long): StateFlow<ProductEntity?> {
        return repository.getProductById(productId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )
    }

    fun insertProduct(product: ProductEntity) {
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    fun updateProduct(product: ProductEntity) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }

    fun deleteProduct(product: ProductEntity) {
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }

    fun insertInitialProducts() {
        viewModelScope.launch {

            // اگر دیتابیس قبلاً محصول دارد، دوباره محصولات اولیه را اضافه نکن
            if (repository.getProductCount() > 0) return@launch
        }
    }
}