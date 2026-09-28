package com.example.bakesmarter.pageProductitem

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CostTabs() {
    val tabs = listOf("Ingredient Costs", "Other Costs", "Profit Margin")
    var selected by remember { mutableIntStateOf(0) }

    TabRow(selectedTabIndex = selected) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selected == index,
                onClick = { selected = index },
                text = { Text(title, fontWeight = FontWeight.Bold) }
            )
        }
    }
}
