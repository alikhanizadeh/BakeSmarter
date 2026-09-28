package com.example.bakesmarter.Product

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.R
import com.example.bakesmarter.chip.InfoChip
import com.example.bakesmarter.chip.MarginChip
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight
import com.example.bakesmarter.ui.theme.CardDark
import com.example.bakesmarter.ui.theme.CardLight



// ساخت کارت ها یا همون ایتم هایی که داخل MyProductsScreen نمایش داده میشود

@Composable
fun ProductCard(
    product: ProductUiModel,
    isDark: Boolean,
    onClick: () -> Unit
) {
    val cardBackground = if (isDark) CardDark else CardLight
    val textColor = if (isDark) TextDark else TextLight

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = cardBackground
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = product.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )

                    Text(
                        text = "${stringResource(R.string.last_updated)} : ${product.lastUpdated}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        color = textColor
                    )

                }

                Image(
                    painter = painterResource(product.imageUrl),
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoChip(
                    icon = R.drawable.paid_icon,
                    text = "Cost: ${product.cost}",
                    isDark = isDark
                )
                InfoChip(
                    icon = R.drawable.sell_icon,
                    text = "Price: ${product.price}",
                    isDark = isDark
                )
            }

//            Row(
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                MarginChip(
////                    text = "Margin: ${product.margin}"
//                )
//            }
        }
    }
}
