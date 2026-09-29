package com.example.bakesmarter.components.ButtonNavigation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlin.math.roundToInt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.bakesmarter.ui.theme.CardDark
import com.example.bakesmarter.ui.theme.CardLight

@Composable
fun BottomNav(
    isDark: Boolean,
    navController: NavController,
    modifier: Modifier
) {
    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route

    val itemCenters = remember { mutableStateMapOf<Int, Float>() }

    val activeIndex = when (currentRoute) {
        Screen.Home.route -> 0
        Screen.Ingredient.route -> 1
        Screen.Setting.route -> 2
        else -> 0
    }

    val circleSize = 50.dp
    val density = LocalDensity.current
    val circleSizePx = with(density) { circleSize.toPx() }

    // پس‌زمینهٔ بلرِ پیل: از همون توکن‌های CardDark/CardLight که بقیهٔ
    // اپ استفاده می‌کنه، فقط با آلفا برای افکت شیشه‌ای
    val pillBackground = (if (isDark) CardLight else CardDark).copy(alpha = 0.6f)

    // موقعیت افقی دایرهٔ متحرک به پیکسل مطلق (مستقل از LTR/RTL)
    val circleOffsetPx = remember { Animatable(0f) }
    var hasSnappedOnce by remember { mutableStateOf(false) }

    LaunchedEffect(activeIndex, itemCenters[activeIndex]) {
        val measuredCenter = itemCenters[activeIndex] ?: return@LaunchedEffect
        val targetOffset = measuredCenter - circleSizePx / 2

        if (!hasSnappedOnce) {
            // اولین اندازه‌گیری: بدون انیمیشن مستقیم برو سر جاش
            // (وگرنه موقع باز شدن صفحه یه لغزش عجیب از گوشه دیده می‌شه)
            circleOffsetPx.snapTo(targetOffset)
            hasSnappedOnce = true
        } else {
            circleOffsetPx.animateTo(
                targetValue = targetOffset,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            Box(
                modifier = Modifier
                    .width(280.dp)
                    .height(60.dp)
            ) {

                // لایه بلر پس‌زمینه — رنگش حالا با تم هماهنگه
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(40.dp))
                        .background(pillBackground)
                        .blur(20.dp)
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(40.dp))
                ) {

                    // دایره بنفش متحرک (رنگ برند، در هر دو تم ثابت می‌مونه)
                    Box(
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    x = circleOffsetPx.value.roundToInt(),
                                    y = with(density) { 5.dp.roundToPx() }
                                )
                            }
                            .padding(start = 20.dp)
                            .size(circleSize)
                            .clip(CircleShape)
                            .background(Color(0xFF7C3AED))
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // دکمه ۱ (Home / Add)
                        BottomNavItemMeasured(
                            index = 0,
                            selected = activeIndex == 0,
                            label = if (currentRoute == Screen.Home.route) "Add" else "Home",
                            icon = if (currentRoute == Screen.Home.route)
                                Icons.Default.AddCircle else Icons.Default.Home,
                            itemCenters = itemCenters,
                            isDark = isDark
                        ) {
                            if (currentRoute == Screen.Home.route) {
                                navController.navigate("CreateRecipe")
                            } else {
                                navController.navigate(Screen.Home.route)
                            }
                        }

                        // دکمه ۲ (Explore / AddIngredient)
                        BottomNavItemMeasured(
                            index = 1,
                            selected = activeIndex == 1,
                            label = "Explore",
                            icon = if (currentRoute == Screen.Ingredient.route)
                                Icons.Default.AddCircle else Icons.Default.Build,
                            itemCenters = itemCenters,
                            isDark = isDark
                        ) {
                            if (currentRoute == Screen.Ingredient.route) {
                                navController.navigate("AddIngredient")
                            } else {
                                navController.navigate(Screen.Ingredient.route)
                            }
                        }

                        // دکمه ۳ (Profile)
                        BottomNavItemMeasured(
                            index = 2,
                            selected = activeIndex == 2,
                            label = "Profile",
                            icon = Icons.Default.Person,
                            itemCenters = itemCenters,
                            isDark = isDark
                        ) {
                            navController.navigate(Screen.Setting.route)
                        }
                    }
                }
            }
        }
    }
}


//
//@Preview(showBackground = true)
//@Composable
//fun GreetingNavigation() {
//    BottomNav(
//        true,
//        rememberNavController(),
//        modifier = Modifier
//        .padding(bottom = 20.dp)
//    )
//}

