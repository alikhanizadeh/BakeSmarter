package com.example.bakesmarter.pageProductitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.pageProductitem.IngredientUiModel
import com.example.bakesmarter.ui.theme.BackgroundDark
import com.example.bakesmarter.ui.theme.BackgroundLight
import com.example.bakesmarter.ui.theme.CardDark
import com.example.bakesmarter.ui.theme.CardLight
import com.example.bakesmarter.ui.theme.NeutralDark
import com.example.bakesmarter.ui.theme.NeutralLight
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight

// تابع نمایش مواد استفاده شده در هر محصول به صورت کادر

@Composable
fun IngredientRow(
    ingredient: IngredientUiModel,
    isDark: Boolean
) {

    val centerTextColor = if (isDark) TextDark else TextLight
    val cardBg = if (isDark) CardDark else CardLight
    val Neutral = if (isDark) NeutralDark else NeutralLight

    Card(colors = CardDefaults.cardColors(cardBg)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = cardBg,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = ingredient.iconRes),
                        contentDescription = null,
                        tint = ingredient.iconTint,
                        modifier = Modifier.size(17.dp)
                    )
                }
                Spacer(Modifier.widthIn(12.dp))
                Column {
                    Text(ingredient.name, fontSize = 20.sp , fontWeight = FontWeight.Bold , color = centerTextColor)
                    Text(ingredient.amount, fontSize = 17.sp, color = Neutral)
                }
            }
            Text(ingredient.cost, fontWeight = FontWeight.Bold , color = centerTextColor)
        }
    }
}
