package com.example.bakesmarter.components.ButtonNavigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import com.example.bakesmarter.ui.theme.TextMutedDark
import com.example.bakesmarter.ui.theme.TextMutedLight

@Composable
fun BottomNavItemMeasured(
    index: Int,
    selected: Boolean,
    label: String,
    icon: ImageVector,
    itemCenters: MutableMap<Int, Float>,
    isDark: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (selected) 1.1f else 1f,
        label = "scale"
    )

    // آیکونِ غیرفعال از همون توکن‌های متنِ خاموش بقیهٔ اپ استفاده می‌کنه
    // تا روی پس‌زمینهٔ تیره/روشن کنتراست درستی داشته باشه
    val unselectedTint = if (isDark) TextMutedLight else TextMutedDark

    Column(
        modifier = Modifier
            .width(64.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .onGloballyPositioned { layoutCoordinates ->
                val centerX =
                    layoutCoordinates.positionInParent().x +
                            layoutCoordinates.size.width / 2f

                itemCenters[index] = centerX
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) Color.White else unselectedTint,
            modifier = Modifier.size(20.dp)
        )
    }
}