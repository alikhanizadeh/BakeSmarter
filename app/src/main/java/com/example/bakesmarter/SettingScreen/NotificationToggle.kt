package com.example.bakesmarter.SettingScreen


import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.bakesmarter.ui.theme.Primary


@Composable
fun NotificationToggle(
    isEnabled: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        checked = isEnabled,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = Primary
        )
    )
}
