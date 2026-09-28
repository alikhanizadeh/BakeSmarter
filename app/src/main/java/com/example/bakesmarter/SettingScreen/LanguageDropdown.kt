package com.example.bakesmarter.SettingScreen



import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bakesmarter.ui.theme.IconBgDark
import com.example.bakesmarter.ui.theme.IconBgLight
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight
import com.example.bakesmarter.ui.theme.TextMutedDark
import com.example.bakesmarter.ui.theme.TextMutedLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropdown(
    isDark: Boolean,
    selectedLanguage: String?,
    onLanguageSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val text = if (isDark) TextDark else TextLight
    val muted = if (isDark) TextMutedDark else TextMutedLight
    val iconBg = if (isDark) IconBgDark else IconBgLight


    val languages = listOf(
        "en" to "English",
        "fa" to "فارسی"
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {

        OutlinedTextField(
            value = selectedLanguage ?: "- Select -",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                disabledTextColor = text,
                disabledLabelColor = iconBg
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEach { (code, label) ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    onClick = {
                        onLanguageSelected(code)
                        expanded = false
                    }
                )
            }
        }
    }
}
