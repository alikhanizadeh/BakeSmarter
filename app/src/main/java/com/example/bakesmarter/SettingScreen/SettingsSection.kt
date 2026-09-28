package com.example.bakesmarter.SettingScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val CardDark = Color(0xFF241B14)
private val CardLight = Color(0xFFFFFFFF)
private val HeaderMutedDark = Color(0xFFB8A99A)
private val HeaderMutedLight = Color(0xFF8A7C6E)

@Composable
fun SettingsSection(
    isDark: Boolean,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    val cardColor = if (isDark) CardDark else CardLight
    val headerColor = if (isDark) HeaderMutedDark else HeaderMutedLight

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        Text(
            text = title.uppercase(),
            color = headerColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
                content = content
            )
        }
    }
}