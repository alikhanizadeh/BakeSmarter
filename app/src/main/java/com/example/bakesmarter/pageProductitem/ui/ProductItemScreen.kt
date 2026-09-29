package com.example.bakesmarter.pageProductitem.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bakesmarter.pageProductitem.BottomActionBar
import com.example.bakesmarter.pageProductitem.CostTopBar
import com.example.bakesmarter.pageProductitem.IngredientDonutChart
import com.example.bakesmarter.pageProductitem.IngredientRow
import com.example.bakesmarter.pageProductitem.IngredientUiModel
import com.example.bakesmarter.pageProductitem.ProductItemViewModel
import com.example.bakesmarter.pageProductitem.StatsSection
import com.example.bakesmarter.ui.theme.BackgroundDark
import com.example.bakesmarter.ui.theme.BackgroundLight
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.example.bakesmarter.ui.theme.CardDark
import com.example.bakesmarter.ui.theme.CardLight
import com.example.bakesmarter.ui.theme.Primary


@Composable
fun ProductItemScreen(
    isDark: Boolean,
    productItemViewModel: ProductItemViewModel,
    onBack: () -> Unit,
    onEditIngredients: (productId: Long) -> Unit
) {
    val background = if (isDark) BackgroundDark else BackgroundLight

    val uiState by productItemViewModel.uiState.collectAsState()
    val product = uiState.product

    // تا زمانی که محصول از دیتابیس لود نشده، صفحه خالی نشون نده
    if (product == null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val ingredients = uiState.ingredients.map { item ->
        IngredientUiModel(
            name = item.name,
            amount = "${item.quantity} ${item.unit}",
            cost = "$%.2f".format(item.price * item.quantity),
            iconRes = item.iconResId,
            bgColor = if (isDark) CardDark else CardLight,
            iconTint = Primary
        )
    }

    Box(modifier = Modifier.fillMaxSize()
        .padding(top = 30.dp)) {

        Scaffold(
            containerColor = background,
            topBar = {
                CostTopBar(
                    title = product.name,
                    onBack = onBack,
                    isDark = isDark
                )
            },
            bottomBar = {
                BottomActionBar(
                    isDark = isDark,
                    onEditClick = {
                        onEditIngredients(product.id)
                    }
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

                // نمایش مقدار هزینه و مقدار سود
                item {
                    StatsSection(
                        isDark = isDark,
                        cost = product.cost,
                        price = product.price
                    )
                }

                // نمایش سهم هزینه مواد استفاده‌شده با نمودار
                item {
                    IngredientDonutChart(
                        isDark = isDark,
                        ingredients = uiState.ingredients
                    )
                }

                // مواد استفاده‌شده در محصول مورد نظر
                items(ingredients) {
                    IngredientRow(it, isDark)
                }

                item { androidx.compose.foundation.layout.Spacer(Modifier.height(96.dp)) }
            }
        }
    }
}
