package com.example.bakesmarter.components

import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.bakesmarter.R

@Composable
fun CustomBottomNavigation() {
    val items = listOf("Home", "Messenger", "Search", "Setting")
    var selectedItem by remember { mutableStateOf("Home") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        color = Color.White,
        shape = RoundedCornerShape(20.dp), // گوشه‌های گرد
        shadowElevation = 6.dp // سایه‌ی سبک مثل تصویر
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val iconRes = when (item) {
                    "Home" -> painterResource(id = R.drawable.paid_icon)
                    "Messenger" -> painterResource(id = R.drawable.paid_icon)
                    "Search" -> painterResource(id = R.drawable.paid_icon)
                    "Setting" -> painterResource(id = R.drawable.paid_icon)
                    else -> painterResource(id = R.drawable.paid_icon)
                }

                val isSelected = selectedItem == item
                val color = if (isSelected) Color(0xFF0D6EFD) else Color.Gray

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { selectedItem = item }
                ) {
                    Icon(
                        painter = iconRes,
                        contentDescription = item,
                        tint = color,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item,
                        color = color,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingNavigation() {
    CustomBottomNavigation()
}
