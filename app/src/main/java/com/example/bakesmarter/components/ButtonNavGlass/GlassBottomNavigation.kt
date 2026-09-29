package com.example.bakesmarter.components.ButtonNavGlass


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bakesmarter.WelcomeScreen
import com.example.bakesmarter.components.ButtonNavigation.Screen
import com.example.bakesmarter.ui.theme.BakeSmarterTheme
import kotlin.math.roundToInt

@Composable
fun GlassBottomNavigation(
    isDark: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    // ذخیره شاخص فعال در rememberSaveable تا با تغییر صفحه ریست نشود
    var activeIndex by rememberSaveable { mutableIntStateOf(0) }

    // به‌روزرسانی activeIndex فقط زمان تغییر واقعی مسیر
    LaunchedEffect(currentRoute) {
        when (currentRoute) {
            Screen.Home.route -> activeIndex = 0
            Screen.Ingredient.route -> activeIndex = 1
            Screen.Setting.route -> activeIndex = 2
        }
    }

    // انیمیشن شاخص که با تعویض صفحه مقدار قبلی خود را حفظ کرده است
    val animatedIndex by animateFloatAsState(
        targetValue = activeIndex.toFloat(),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "animatedIndex"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White.copy(alpha = 0.12f))
                .border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(50))
                .shadow(24.dp, RoundedCornerShape(50), spotColor = Color.Black.copy(0.35f))
        ) {
            // استفاده از Layout برای محاسبه دقیق X بدون نیاز به BoxWithConstraints
            Layout(
                content = {
                    // آیتم 0: نشانگر متحرک
                    Box(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .width(72.dp)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF3B82F6).copy(alpha = 0.22f))
                    )

                    // آیتم‌های اصلی
                    GlassNavItem(
                        selected = activeIndex == 0,
                        label = if (currentRoute == Screen.Home.route) "Add" else "Home",
                        icon = if (currentRoute == Screen.Home.route) Icons.Default.AddCircle else Icons.Default.Home,
                        isDark = isDark
                    ) {
                        if (currentRoute == Screen.Home.route) {
                            navController.navigate("CreateRecipe")
                        } else {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }

                    GlassNavItem(
                        selected = activeIndex == 1,
                        label = "Explore",
                        icon = if (currentRoute == Screen.Ingredient.route) Icons.Default.AddCircle else Icons.Default.Build,
                        isDark = isDark
                    ) {
                        if (currentRoute == Screen.Ingredient.route) {
                            navController.navigate("AddIngredient")
                        } else {
                            navController.navigate(Screen.Ingredient.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }

                    GlassNavItem(
                        selected = activeIndex == 2,
                        label = "Profile",
                        icon = Icons.Default.Person,
                        isDark = isDark
                    ) {
                        navController.navigate(Screen.Setting.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            ) { measurables, constraints ->
                val indicatorMeasurable = measurables[0]
                val itemMeasurables = measurables.drop(1)

                val itemCount = itemMeasurables.size
                val itemWidth = constraints.maxWidth / itemCount

                // اندازه گیری آیتم ها
                val itemConstraints = constraints.copy(minWidth = itemWidth, maxWidth = itemWidth)
                val itemPlaceables = itemMeasurables.map { it.measure(itemConstraints) }

                val indicatorWidth = 72.dp.roundToPx()
                val indicatorPlaceable = indicatorMeasurable.measure(
                    constraints.copy(minWidth = indicatorWidth, maxWidth = indicatorWidth)
                )

                layout(constraints.maxWidth, constraints.maxHeight) {
                    // جای‌گذاری دایره متحرک بر اساس animatedIndex
                    val indicatorX = (animatedIndex * itemWidth) + (itemWidth - indicatorWidth) / 2f
                    indicatorPlaceable.placeRelative(indicatorX.roundToInt(), 0)

                    // جای‌گذاری آیتم‌های اصلی
                    itemPlaceables.forEachIndexed { index, placeable ->
                        val x = index * itemWidth
                        val y = (constraints.maxHeight - placeable.height) / 2 // 👈 تراز کردن عمودی در مرکز
                        placeable.placeRelative(x, y)
                    }
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    GlassBottomNavigation(false ,
        navController = navController ,
        Modifier)
}
