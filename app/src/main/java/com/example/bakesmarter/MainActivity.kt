package com.example.bakesmarter

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bakesmarter.Product.ProductUiModel
import com.example.bakesmarter.AddIngredientScreen.AddIngredientScreen
import android.content.res.Configuration
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import com.example.bakesmarter.CreateProductScreen.CreateRecipeViewModel
import com.example.bakesmarter.CreateProductScreen.CreateRecipeViewModelFactory
import java.util.Locale
import com.example.bakesmarter.LanguageApp.AppLanguage
import com.example.bakesmarter.LanguageApp.SettingsViewModel
import com.example.bakesmarter.LanguageApp.AppPreferences
import com.example.bakesmarter.LanguageApp.ThemeMode
import com.example.bakesmarter.LanguageApp.appSettingsDataStore
import com.example.bakesmarter.MyIngredientScreen.ui.MyIngredientsScreen
import com.example.bakesmarter.Product.ui.MyProductsScreen
import com.example.bakesmarter.SettingScreen.ui.SettingsScreen
import com.example.bakesmarter.SettingScreen.ui.SettingsViewModelFactory
import com.example.bakesmarter.components.ButtonNavigation.Screen
import com.example.bakesmarter.pageProductitem.ui.ProductItemScreen
import com.example.bakesmarter.ui.theme.BakeSmarterTheme
import com.example.bakesmarter.MyIngredientScreen.IngredientViewModel
import com.example.bakesmarter.MyIngredientScreen.IngredientViewModelFactory
import com.example.bakesmarter.data.repository.IngredientRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import com.example.bakesmarter.Product.ProductViewModel
import com.example.bakesmarter.Product.ProductViewModelFactory
import com.example.bakesmarter.data.local.AppDatabase
import com.example.bakesmarter.data.repository.CreateRecipeRepository
import com.example.bakesmarter.data.repository.ProductRepository
import com.example.bakesmarter.data.repository.ProductItemRepository
import com.example.bakesmarter.pageProductitem.ProductItemViewModel
import com.example.bakesmarter.pageProductitem.ProductItemViewModelFactory
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.bakesmarter.CreateProductScreen.CreateRecipeScreen


class MainActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {

        val language = runBlocking {

            newBase
                .appSettingsDataStore
                .data
                .first()[AppPreferences.LANGUAGE_KEY]

        } ?: "en"

        val locale = Locale(language)

        Locale.setDefault(locale)

        val configuration = Configuration(newBase.resources.configuration)

        configuration.setLocale(locale)

        val context = newBase.createConfigurationContext(configuration)

        super.attachBaseContext(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AppRoot()
        }
    }
}


// ==========================================
// Application Root
// ==========================================

@Composable
fun AppRoot() {

    val context = LocalContext.current

    // ==========================================
    // Settings ViewModel
    // ==========================================

    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(context)
    )


    val database = AppDatabase.getInstance(context)

    val productRepository = ProductRepository(
        database.productDao()
    )

    val productViewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(
            productRepository
        )
    )


    productViewModel.insertInitialProducts()



    val ingredientRepository = IngredientRepository(
        database.ingredientDao()
    )

    val ingredientViewModel: IngredientViewModel = viewModel(
        factory = IngredientViewModelFactory(
            ingredientRepository
        )
    )

    ingredientViewModel.insertInitialIngredients()


    val createRecipeRepository =
        CreateRecipeRepository(
            productDao = database.productDao(),
            recipeIngredientDao = database.recipeIngredientDao()
        )


    val createRecipeViewModel: CreateRecipeViewModel =
        viewModel(
            factory = CreateRecipeViewModelFactory(
                createRecipeRepository
            )
        )


    val productItemRepository = ProductItemRepository(
        productDao = database.productDao(),
        recipeIngredientDao = database.recipeIngredientDao()
    )

    // ==========================================
    // Read saved Theme
    // ==========================================

    val theme by settingsViewModel.themeFlow.collectAsState(
        initial = ThemeMode.SYSTEM
    )



    // ==========================================
    // Read saved Language
    // ==========================================

    val language by settingsViewModel.languageFlow
        .collectAsState(
            initial = AppLanguage.EN
        )


    // ==========================================
    // Determine Dark Mode
    // ==========================================



    val isDarkTheme = when (theme) {
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }


    // ==========================================
    // Determine Layout Direction
    // ==========================================

    val layoutDirection = when (language) {

        AppLanguage.FA -> LayoutDirection.Rtl

        AppLanguage.EN -> LayoutDirection.Ltr
    }


    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection
    ) {
        BakeSmarterTheme(
            darkTheme = isDarkTheme,
            dynamicColor = false
        ) {
            MainNavHost(
                settingsViewModel = settingsViewModel,
                productViewModel = productViewModel,
                ingredientViewModel = ingredientViewModel,
                isDarkTheme = isDarkTheme,
                createRecipeViewModel = createRecipeViewModel,
                productItemRepository = productItemRepository

            )
        }
    }


    // ==========================================
    // Apply Theme
    // ==========================================

    BakeSmarterTheme(
        darkTheme = isDarkTheme,
        dynamicColor = false
    ) {
        MainNavHost(
            isDarkTheme = isDarkTheme,
            settingsViewModel = settingsViewModel,
            productViewModel = productViewModel,
            ingredientViewModel = ingredientViewModel,
            createRecipeViewModel = createRecipeViewModel,
            productItemRepository = productItemRepository
            // سایر پارامترهای فعلی
        )
    }
}


// ==========================================
// Navigation
// ==========================================

@Composable
fun MainNavHost(
    isDarkTheme: Boolean,
    settingsViewModel: SettingsViewModel,
    productViewModel: ProductViewModel,
    ingredientViewModel: IngredientViewModel,
    createRecipeViewModel : CreateRecipeViewModel,
    productItemRepository: ProductItemRepository
) {

    val context = LocalContext.current
    val navController = rememberNavController()
    val products by productViewModel.products.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        // ==========================================
        // Welcome
        // ==========================================

        composable("welcome") {

            WelcomeScreen(
                isDark = isDarkTheme,

                onGetStartedClick = {

                    navController.navigate(
                        Screen.Home.route
                    ) {
                        popUpTo(
                            "welcome"
                        ) {
                            inclusive = true
                        }
                    }
                },

                onSignInClick = {}
            )
        }


        // ==========================================
        // Home / My Products
        // ==========================================

        composable(Screen.Home.route) {

            val uiProducts = products.map { product ->
                ProductUiModel(
                    id = product.id,
                    name = product.name,
                    lastUpdated = product.lastUpdated,
                    imageUrl = product.imageResId,
                    cost = "$%.2f".format(product.cost),
                    price = "$%.2f".format(product.price),
//                    margin = "%.0f%%".format(product.margin)
                )
            }

            MyProductsScreen(
                isDark = isDarkTheme,
                navController = navController,
                products = uiProducts,
                onProductClick = { product ->
                    navController.navigate("ProductItem/${product.id}")
                },
                onAddClick = {
                    navController.navigate("CreateRecipe")
                },
                onSearchClick = {},
                onSortClick = {}
            )
        }


        // ==========================================
        // Product Detail
        // ==========================================

        composable("ProductItem/{productId}") { backStackEntry ->

            val productId = backStackEntry.arguments
                ?.getString("productId")
                ?.toLongOrNull()
                ?: return@composable

            val productItemViewModel: ProductItemViewModel = viewModel(
                factory = ProductItemViewModelFactory(
                    repository = productItemRepository,
                    productId = productId
                )
            )

            ProductItemScreen(
                isDark = isDarkTheme,
                productItemViewModel = productItemViewModel,
                onBack = {
                    navController.popBackStack()
                },
                onEditIngredients = { id ->
                    navController.navigate("CreateRecipe?productId=$id")
                }
            )
        }


        // ==========================================
        // Create Product
        // ==========================================

        // ==========================================
// Create Product
// ==========================================

        composable(
            route = "CreateRecipe?productId={productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->

            val productId = backStackEntry.arguments
                ?.getLong("productId")
                ?.takeIf { it != -1L }

            CreateRecipeScreen(
                isDark = isDarkTheme,
                ingredientViewModel = ingredientViewModel,
                createRecipeViewModel = createRecipeViewModel,
                productId = productId,

                onBack = {
                    navController.popBackStack()
                },

                onSave = {
                    navController.popBackStack()
                }
            )
        }


        // ==========================================
        // Ingredients
        // ==========================================

        composable(Screen.Ingredient.route) {
            MyIngredientsScreen(
                isDark = isDarkTheme,
                navController = navController,
                ingredientViewModel = ingredientViewModel
            )
        }


        // ==========================================
        // Add Ingredient
        // ==========================================

        composable("AddIngredient") {
            AddIngredientScreen(
                isDark = isDarkTheme,
                ingredientViewModel = ingredientViewModel,
                onBack = {
                    navController.popBackStack()
                },
                onSave = {
                    navController.popBackStack()
                }
            )
        }


        // ==========================================
        // Settings
        // ==========================================

        composable(Screen.Setting.route) {

            SettingsScreen(
                isDark = isDarkTheme,

                navController = navController,

                onBack = {

                    navController.popBackStack()
                },

                settingsViewModel = settingsViewModel,

                onLanguageChanged = {
//                    (context as? MainActivity)?.recreate()
                }
            )
        }
    }
}