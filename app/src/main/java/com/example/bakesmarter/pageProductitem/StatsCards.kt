package com.example.bakesmarter.pageProductitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.R
import com.example.bakesmarter.ui.theme.CardDark
import com.example.bakesmarter.ui.theme.CardLight
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight

// کارت‌های مقدار سود و هزینهٔ واقعی محصول (از ProductEntity.cost و ProductEntity.price)
@Composable
fun StatsSection(
    isDark: Boolean,
    cost: Double,
    price: Double,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        StatCard(stringResource(R.string.donut_total_cost), "$%.2f".format(cost), isDark, Modifier.fillMaxWidth())
        StatCard(stringResource(R.string.selling_price_placeholder), "$%.2f".format(price), isDark, Modifier.fillMaxWidth())
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    isDark: Boolean,
    modifier: Modifier = Modifier
) {
    val cardBg = if (isDark) CardDark else CardLight
    val textColor = if (isDark) TextDark else TextLight

    Card(
        modifier = modifier,
        shape = AppCardStyle.shape,
        colors = CardDefaults.cardColors(containerColor = cardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(AppCardStyle.contentPadding)) {
            Text(title, fontWeight = FontWeight.Medium, color = textColor)
            Text(value, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = textColor)
        }
    }
}




object AppCardStyle {
    val shape: Shape = RoundedCornerShape(20.dp)
    val contentPadding = 16.dp
}