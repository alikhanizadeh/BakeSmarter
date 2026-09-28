package com.example.bakesmarter.Product.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bakesmarter.Product.FullScreenSearch
import com.example.bakesmarter.Product.ProductUiModel
import com.example.bakesmarter.R
import com.example.bakesmarter.Product.ProductCard
import com.example.bakesmarter.components.ButtonNavigation.BottomNav
import com.example.bakesmarter.ui.theme.BackgroundDark
import com.example.bakesmarter.ui.theme.BackgroundLight
import com.example.bakesmarter.ui.theme.BakeSmarterTheme
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight


//  اولین صفحه بعد از ورود به برنامه (نشون دهنده غذا های حساب شده)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProductsScreen(
    isDark: Boolean,
    navController: NavController,
    products: List<ProductUiModel>,
    onProductClick: (ProductUiModel) -> Unit,
    onAddClick: () -> Unit,
    onSearchClick: () -> Unit,
    onSortClick: () -> Unit
) {
    val background = if (isDark) BackgroundDark else BackgroundLight
    val textColor = if (isDark) TextDark else TextLight
    var showFullSearch by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        /* 🔹 Scaffold اصلی */
        Scaffold(
            containerColor = background,
            topBar = {
                if (!showFullSearch){
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(R.string.My_Products),
                                fontWeight = FontWeight.Bold
                            )
                        },
                        actions = {
                            IconButton(onClick = { showFullSearch = true }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = background.copy(alpha = 0.9f),
                            titleContentColor = textColor
                        )
                    )
                }
            }
        ) { padding ->


            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // سرچ تمام صفحه
                AnimatedVisibility(
                    visible = showFullSearch,
                    enter = fadeIn(tween(300)) + slideInVertically(tween(300)) { -it },
                    exit = fadeOut(tween(300)) + slideOutVertically(tween(300)) { -it }
                ) {
                    FullScreenSearch(
                        onDismiss = { showFullSearch = false },
                        onSearch = { /* بعداً */ }
                    )
                }


                /* 🔹 لیست محصولات */
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(bottom = 140.dp), // فضای BottomNav
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(products) { product ->
                        ProductCard(
                            product = product,
                            isDark = isDark,
                            onClick = { onProductClick(product) }
                        )
                    }
                }
            }



        }


            /* 🔹 Bottom Navigation شناور */
            BottomNav(
                isDark = isDark,
                navController = navController,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
            )

    }
}


@Preview(showBackground = true)
@Composable
fun Greeting() {
    val navController = rememberNavController()
    BakeSmarterTheme {
        MyProductsScreen(
            true,
            navController = navController,
            sampleProducts,
            {},
            {},
            {},
            {}
        )
    }
}


// لیست ایتم ها برای تست کد

// داده‌های موقت برای تست UI
// در مرحله Room این بخش به طور کامل حذف خواهد شد.
val sampleProducts = listOf(

    ProductUiModel(
        id = 1L,
        name = "Chocolate Croissant",
        lastUpdated = "3",
        imageUrl = R.drawable.imagewelcome,
        cost = "$1.25",
        price = "$4.50",
//        margin = "72%"
    ),

    ProductUiModel(
        id = 2L,
        name = "Strawberry Tart",
        lastUpdated = "2026-09-24 13:03",
        imageUrl = R.drawable.imagecake1,
        cost = "$2.10",
        price = "$6.00",
//        margin = "65%"
    ),

    ProductUiModel(
        id = 3L,
        name = "Assorted Macarons",
        lastUpdated = "1",
        imageUrl = R.drawable.imagecake2,
        cost = "$0.45",
        price = "$2.50",
//        margin = "82%"
    )
)





