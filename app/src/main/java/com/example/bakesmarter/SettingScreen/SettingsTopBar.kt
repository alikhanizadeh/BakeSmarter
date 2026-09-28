package com.example.bakesmarter.SettingScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.R

private val AccentCaramel = Color(0xFFE07A2F)
private val TitleDark = Color(0xFFF5F1EC)
private val TitleLight = Color(0xFF2B2118)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(
    isDark: Boolean,
    onBack: () -> Unit
) {
    val titleColor = if (isDark) TitleDark else TitleLight

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.settings_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = titleColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}