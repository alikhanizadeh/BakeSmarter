package com.example.bakesmarter.SettingScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bakesmarter.ui.theme.IconBgDark
import com.example.bakesmarter.ui.theme.IconBgLight
import com.example.bakesmarter.ui.theme.TextDark
import com.example.bakesmarter.ui.theme.TextLight
import com.example.bakesmarter.ui.theme.TextMutedDark
import com.example.bakesmarter.ui.theme.TextMutedLight

@Composable
fun SettingsRow(
    isDark: Boolean,
    icon: Int,
    title: String,
    value: String? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    val text = if (isDark) TextDark else TextLight
    val muted = if (isDark) TextMutedDark else TextMutedLight
    val iconBg = if (isDark) IconBgDark else IconBgLight

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(icon) , null, tint = text)
        }

        Spacer(Modifier.width(16.dp))

        Text(
            title,
            modifier = Modifier.weight(1f),
            color = text
        )

        if (value != null) {
            Text(value, color = muted, fontWeight = FontWeight.Medium)
            Spacer(Modifier.width(6.dp))
        }

        trailing ?: Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = null,
            tint = text
        )
    }
}
