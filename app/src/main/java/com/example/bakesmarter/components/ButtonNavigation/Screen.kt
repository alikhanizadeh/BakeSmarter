package com.example.bakesmarter.components.ButtonNavigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Ingredient : Screen("Ingredient")
    object Setting : Screen("profile")
}
