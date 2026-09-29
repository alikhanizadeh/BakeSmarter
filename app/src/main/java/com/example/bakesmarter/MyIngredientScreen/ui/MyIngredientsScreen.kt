package com.example.bakesmarter.MyIngredientScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bakesmarter.MyIngredientScreen.IngredientItem
import com.example.bakesmarter.MyIngredientScreen.IngredientViewModel
import com.example.bakesmarter.MyIngredientScreen.MyIngredientsTopBar
import com.example.bakesmarter.ui.theme.BackgroundDark
import com.example.bakesmarter.ui.theme.BackgroundLight
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun MyIngredientsScreen(
    isDark: Boolean,
    ingredientViewModel: IngredientViewModel
) {



    val ingredients by ingredientViewModel.ingredients.collectAsState()


    Box(
        modifier = Modifier.fillMaxSize()
            .padding(top = 30.dp)
    ) {

        Scaffold(
            topBar = {
                MyIngredientsTopBar(
                    isDark = isDark,
                    onMenuClick = {}
                )
            }
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isDark) BackgroundDark else BackgroundLight)
                    .padding(padding),
                contentPadding = PaddingValues(bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(ingredients) { item ->
                    Box(
                        Modifier
                            .padding(horizontal = 7.dp)
                            .shadow(5.dp)
                    ) {
                        IngredientItem(
                            icon = item.iconResId,
                            name = item.name,
                            price = item.price,
                            unit = item.unit ,
                            isDark = isDark,
                            onMore = {
                                ingredientViewModel.deleteIngredient(item)
                            }
                        )
                    }
                }
            }
        }
    }
}


