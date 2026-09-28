package com.example.bakesmarter.CreateProductScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bakesmarter.data.repository.CreateRecipeRepository

class CreateRecipeViewModelFactory(
    private val repository: CreateRecipeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                CreateRecipeViewModel::class.java
            )
        ) {

            @Suppress("UNCHECKED_CAST")
            return CreateRecipeViewModel(
                repository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}