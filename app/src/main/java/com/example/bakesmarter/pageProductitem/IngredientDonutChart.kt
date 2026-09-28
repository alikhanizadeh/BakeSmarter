package com.example.bakesmarter.pageProductitem

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.R
import com.example.bakesmarter.ui.theme.CardDark
import com.example.bakesmarter.ui.theme.CardLight
import com.example.bakesmarter.ui.theme.NeutralDark
import com.example.bakesmarter.ui.theme.NeutralLight
import com.example.bakesmarter.ui.theme.Primary
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight
import com.example.bakesmarter.data.local.recipe.RecipeIngredientDetail

@Composable
fun IngredientDonutChart(
    isDark: Boolean,
    ingredients: List<RecipeIngredientDetail>
) {
    val centerTextColor = if (isDark) TextDark else TextLight
    val neutral = if (isDark) NeutralDark else NeutralLight
    val cardBg = if (isDark) CardDark else CardLight

    // هزینهٔ هر ماده = quantity * price، و سهم هر ماده از کل نسبت به همین محاسبه می‌شود
    val ingredientCosts = ingredients.map { it.price * it.quantity }
    val totalCost = ingredientCosts.sum()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = AppCardStyle.shape,
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppCardStyle.contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(R.string.Ingredient_Cost_Contribution),
                    fontWeight = FontWeight.Medium,
                    color = centerTextColor
                )
            }

            // رسم نمودار دایره‌ای با Canvas — رنگ اول از پالت اصلی برند (Primary)
            // گرفته شده تا با بقیهٔ صفحه (آیکون‌ها و ...) هماهنگ باشد
            Box(
                Modifier.padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(170.dp)) {
                    // رنگ‌ها به تعداد ingredient ها چرخشی استفاده می‌شوند
                    val palette = listOf(
                        Primary,
                        Primary.copy(alpha = 0.7f),
                        neutral,
                        Primary.copy(alpha = 0.4f)
                    )
                    var startAngle = -90f

                    if (totalCost > 0) {
                        ingredientCosts.forEachIndexed { index, cost ->
                            val sweep = (cost / totalCost * 360.0).toFloat()
                            drawArc(
                                color = palette[index % palette.size],
                                startAngle = startAngle,
                                sweepAngle = sweep,
                                useCenter = false,
                                style = Stroke(width = 36f, cap = StrokeCap.Round)
                            )
                            startAngle += sweep
                        }
                    } else {
                        // بدون هیچ ماده‌ای یا با هزینهٔ صفر: فقط یک حلقهٔ خاکستری خالی
                        drawArc(
                            color = neutral,
                            startAngle = 0f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(width = 36f, cap = StrokeCap.Round)
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(R.string.donut_total_cost), fontSize = 12.sp, color = neutral)
                    Text(
                        "$%.2f".format(totalCost),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = centerTextColor
                    )
                }
            }
        }
    }
}