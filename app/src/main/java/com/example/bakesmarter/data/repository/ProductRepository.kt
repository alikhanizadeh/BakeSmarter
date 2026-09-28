package com.example.bakesmarter.data.repository

import com.example.bakesmarter.data.local.product.ProductDao
import com.example.bakesmarter.data.local.product.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val productDao: ProductDao
) {

    // دریافت تمام محصولات
    fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }

    // دریافت یک محصول بر اساس ID
    fun getProductById(productId: Long): Flow<ProductEntity?> {
        return productDao.getProductById(productId)
    }

    suspend fun getProductCount(): Int =
        productDao.getProductCount()

    suspend fun insertProducts(products: List<ProductEntity>) =
        productDao.insertProducts(products)

    // اضافه کردن محصول جدید
    suspend fun insertProduct(product: ProductEntity): Long {
        return productDao.insertProduct(product)
    }

    // ویرایش محصول
    suspend fun updateProduct(product: ProductEntity) {
        productDao.updateProduct(product)
    }

    // حذف محصول
    suspend fun deleteProduct(product: ProductEntity) {
        productDao.deleteProduct(product)
    }
}