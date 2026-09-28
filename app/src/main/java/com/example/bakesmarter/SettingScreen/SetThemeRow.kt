package com.example.bakesmarter.SettingScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.LanguageApp.ThemeMode
import com.example.bakesmarter.R

private val AccentCaramel = Color(0xFFE07A2F)
private val TextDark = Color(0xFFF5F1EC)
private val TextLight = Color(0xFF2B2118)
private val MutedDark = Color(0xFFB8A99A)
private val MutedLight = Color(0xFF8A7C6E)
private val IconBgDark = Color(0xFF3A2C20)
private val IconBgLight = Color(0xFFF1E6DA)
private val TrackDark = Color(0xFF1B140F)
private val TrackLight = Color(0xFFF1E6DA)

@Composable
fun SetThemeRow(
    isDark: Boolean,
    @DrawableRes icon: Int,
    selectedTheme: ThemeMode,
    onThemeSelected: (ThemeMode) -> Unit
) {
    val textColor = if (isDark) TextDark else TextLight
    val mutedColor = if (isDark) MutedDark else MutedLight
    val iconBg = if (isDark) IconBgDark else IconBgLight
    val trackColor = if (isDark) TrackDark else TrackLight

    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 14.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(iconBg, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "Theme",
                    tint = AccentCaramel,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.theme_label),
                color = textColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(trackColor)
                .padding(4.dp)
        ) {
            ThemeOption(
                label = stringResource(R.string.theme_light),
                icon = Icons.Default.LocationOn,
                selected = selectedTheme == ThemeMode.LIGHT,
                textColor = textColor,
                mutedColor = mutedColor,
                modifier = Modifier.weight(1f)
            ) { onThemeSelected(ThemeMode.LIGHT) }

            ThemeOption(
                label = stringResource(R.string.theme_dark),
                icon = Icons.Default.Add,
                selected = selectedTheme == ThemeMode.DARK,
                textColor = textColor,
                mutedColor = mutedColor,
                modifier = Modifier.weight(1f)
            ) { onThemeSelected(ThemeMode.DARK) }

            ThemeOption(
                label = stringResource(R.string.theme_system),
                icon = Icons.Default.Settings,
                selected = selectedTheme == ThemeMode.SYSTEM,
                textColor = textColor,
                mutedColor = mutedColor,
                modifier = Modifier.weight(1f)
            ) { onThemeSelected(ThemeMode.SYSTEM) }
        }
    }
}

@Composable
private fun ThemeOption(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    textColor: Color,
    mutedColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bg = if (selected) AccentCaramel else Color.Transparent

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) Color.White else mutedColor,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) Color.White else textColor
        )
    }
}