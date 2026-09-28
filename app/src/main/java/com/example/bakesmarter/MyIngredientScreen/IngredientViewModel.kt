package com.example.bakesmarter.MyIngredientScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakesmarter.R
import com.example.bakesmarter.data.local.ingredient.IngredientEntity
import com.example.bakesmarter.data.repository.IngredientRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val repository: IngredientRepository
) : ViewModel() {

    val ingredients: StateFlow<List<IngredientEntity>> =
        repository.getAllIngredients().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun insertIngredient(ingredient: IngredientEntity) {
        viewModelScope.launch {
            repository.insertIngredient(ingredient)
        }
    }

    fun updateIngredient(ingredient: IngredientEntity) {
        viewModelScope.launch {
            repository.updateIngredient(ingredient)
        }
    }

    fun deleteIngredient(ingredient: IngredientEntity) {
        viewModelScope.launch {
            repository.deleteIngredient(ingredient)
        }
    }

    fun insertInitialIngredients() {
        viewModelScope.launch {
            if (repository.getIngredientCount() > 0) {
                return@launch
            }

            val initialIngredients = listOf(
                IngredientEntity(
                    name = "Milk",
                    price = 200.0,
                    unit = "L",
                    iconResId = R.drawable.water_icon
                ),

                IngredientEntity(
                    name = "All-Purpose Flour",
                    price = 100.0,
                    unit = "kg",
                    iconResId = R.drawable.grain
                ),

                IngredientEntity(
                    name = "Granulated Sugar",
                    price = 2.20,
                    unit = "kg",
                    iconResId = R.drawable.cake_icon
                ),

                IngredientEntity(
                    name = "Unsalted Butter",
                    price = 8.0,
                    unit = "kg",
                    iconResId = R.drawable.water_icon
                ),

                IngredientEntity(
                    name = "Large Eggs",
                    price = 4.50,
                    unit = "dozen",
                    iconResId = R.drawable.egg_icon
                )
            )

            repository.insertIngredients(initialIngredients)
        }
    }
}