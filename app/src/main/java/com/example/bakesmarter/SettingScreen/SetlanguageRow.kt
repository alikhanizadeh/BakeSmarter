package com.example.bakesmarter.SettingScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.LanguageApp.AppLanguage
import com.example.bakesmarter.R
import com.example.bakesmarter.ui.theme.IconBgDark
import com.example.bakesmarter.ui.theme.IconBgLight
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight
import com.example.bakesmarter.ui.theme.TextMutedDark
import com.example.bakesmarter.ui.theme.TextMutedLight



private val AccentCaramel = Color(0xFFE07A2F)


// اسم‌های نمایشی زبان‌ها را اینجا با enum واقعی AppLanguage خودتان تطبیق بدهید
private fun displayName(language: AppLanguage): String = when (language.name) {
    "EN" -> "English"
    "FA" -> "فارسی"
    else -> "English"
}

@Composable
fun SetLanguageRow(
    isDark: Boolean,
    @DrawableRes icon: Int,
    selectedLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit
) {
    val textColor =
        if (isDark) TextDark
        else TextLight

    val mutedColor =
        if (isDark) TextMutedDark
        else TextMutedLight

    val iconBg =
        if (isDark) IconBgDark
        else IconBgLight

    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(horizontal = 12.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(iconBg, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(R.string.language_label),
                    tint = AccentCaramel,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = stringResource(R.string.language_label),
                color = textColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = displayName(selectedLanguage),
                color = mutedColor,
                fontSize = 14.sp
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = mutedColor,
                modifier = Modifier.size(18.dp).padding(start = 4.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            AppLanguage.entries.forEach { language ->
                DropdownMenuItem(
                    text = { Text(displayName(language)) },
                    onClick = {
                        onLanguageSelected(language)
                        expanded = false
                    }
                )
            }
        }
    }
}



//LanguageDropdown(
//isDark = isDark,
//
//selectedLanguage = expanded,
//
//onLanguageSelected = { languageCode ->
//
//    val appLanguage = when (languageCode) {
//        "fa" -> AppLanguage.FA
//        "en" -> AppLanguage.EN
//        else -> AppLanguage.EN
//    }
//
//    onLanguageSelected(appLanguage)
//}
//
//
//)
